package com.example.readcab;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Component
public class CabParserDomHelper {
    public void parseCabFile() throws IOException, SAXException, ParserConfigurationException, ParseException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // never forget this!
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("/home/vadim/tmp/cab-test/extracted/package/package.xml");




//        XPathFactory xpathfactory = XPathFactory.newInstance();
//        XPath xpath = xpathfactory.newXPath();
//
//        // 1) Get book titles written after 2001
//        XPathExpression expr = xpath.compile("//Update[@DeploymentAction=\"Evaluate\"]/node()");
//        Object result = expr.evaluate(doc, XPathConstants.NODESET);
//        NodeList nodes = (NodeList) result;
//        for (int i = 0; i < nodes.getLength(); i++) {
//            System.out.println(nodes.item(i).getNodeValue());
//        }


//        if(true) return;

        Map<String, String> prerequisites = new HashMap<>();
        Map<String, String> supersededBy = new HashMap<>();
        Map<String, String> revisionToId = new HashMap<>();
        Map<String, String> evaluateIdToRevision = new HashMap<>();

        NodeList updateNodes = doc.getElementsByTagName("Update");
        for(int i = 0; i < updateNodes.getLength(); i++) {
            boolean skip = false;
            Node updateNode = updateNodes.item(i);
            NamedNodeMap updateAttributes = updateNode.getAttributes();
            Date creationDate = DateUtils.parseDate(
                    updateAttributes.getNamedItem("CreationDate").getNodeValue().replace("Z", "+0000")
                    , new String[] {"yyyy-MM-dd'T'HH:mm:ssZ"});
            String updateId = updateAttributes.getNamedItem("UpdateId").getNodeValue();
            String revisionId = updateAttributes.getNamedItem("RevisionId").getNodeValue();

            revisionToId.put(revisionId, updateId);

            Node isBundleNode = updateAttributes.getNamedItem("IsBundle");
            if(isBundleNode != null) {
                String isBundle = isBundleNode.getNodeValue();
                if("true".equals(isBundle.toLowerCase())) {
                }
            }
            Node deploymentActionNode = updateAttributes.getNamedItem("DeploymentAction");
            if(deploymentActionNode != null) {
                String deploymentAction = deploymentActionNode.getNodeValue().toLowerCase();
                if("bundle".equals(deploymentAction)) {

                } else if("evaluate".equals(deploymentAction)) {
                    evaluateIdToRevision.put(updateId, revisionId);
                }
            }
            NodeList updateContentNodes = updateNode.getChildNodes();
            for(int j = 0; j < updateContentNodes.getLength() && !skip; j++) {
                Node updateContentNode = updateContentNodes.item(j);
                switch (updateContentNode.getNodeName().toLowerCase()) {
                    case "categories":
                        break;
                    case "prerequisites":
                        break;
                    case "payloadfiles":
                        break;
                    case "bundledby":
                        Set<String> tagAttributeValues = getTagAttributeValues(updateContentNode, "revision", "Id");
                        if(tagAttributeValues.size() == 1) {
                            String bundledById = revisionToId.get(tagAttributeValues.iterator().next());
                            if(bundledById == null) {
                                System.out.println("No bundler ID found; ID: " + updateId);
                            }
                        } else if(tagAttributeValues.size() > 1) {
                            System.out.println("More then one bundlers found; ID: " + updateId);
                        } else {
                            System.out.println("No at least one bundlers found; ID: " + updateId);
                        }
                        break;
                    case "supersededby":
                        extractSupersededBy(updateId, updateContentNode, supersededBy);
                        break;
                    case "languages":
                        Set<String> langNames = getTagAttributeValues(updateContentNode, "language", "Name");
                        if(langNames.size() > 0 && ! langNames.contains("en")) {
                            System.out.println("Skipping item, found languages but no english; ID: " + updateId);
                            skip = true;
                        }
                        break;
                    default:
                }
            }
        }
    }

    private Set<String> getTagAttributeValues(Node updateContentNode, String nodeName, String attributeName) {
        Set<String> attributeValues = new TreeSet<>();
        NodeList childNodes = updateContentNode.getChildNodes();
        for(int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if(nodeName.equals(node.getNodeName().toLowerCase())) {
                Node attributeValueNode = node.getAttributes().getNamedItem(attributeName);
                if(attributeValueNode != null) attributeValues.add(attributeValueNode.getNodeValue());
            }
        }
        return attributeValues;
    }

    private void extractSupersededBy(String updateId, Node updateContentNode, Map<String, String> supersededBy) {
        NodeList supersededByRevisionNodes = updateContentNode.getChildNodes();
        for(int i = 0; i < supersededByRevisionNodes.getLength(); i++) {
            Node revisionNode = supersededByRevisionNodes.item(i);
            if("revision".equals(revisionNode.getNodeName().toLowerCase())) {
                String revision = revisionNode.getAttributes().getNamedItem("Id").getNodeValue();
                supersededBy.put(updateId, revision);
            }
        }
    }
}
