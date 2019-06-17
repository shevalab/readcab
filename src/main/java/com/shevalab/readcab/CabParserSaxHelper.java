package com.shevalab.readcab;

import com.shevalab.readcab.printing.IndentData;
import com.shevalab.readcab.printing.PrintingState;
import com.shevalab.readcab.states.*;
import com.shevalab.utils.xml.BaseState;
import com.shevalab.utils.xml.StatefulSaxHandler;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

@Component
public class CabParserSaxHelper {

    private static final String BASE_PATH = "/home/vadim/tmp/WSUS-less/offline-scan/extracted/";
    private RevisionToFile index = null;

    public RevisionToFile parseIndexFile() throws ParserConfigurationException, SAXException, IOException {
        RevisionToFile indexMap = new RevisionToFile();
        BaseState state = new BaseState()
                .child(new BaseState("Index")
                        .child(new BaseState("CabList")
                                .child(new CabIndexState("Cab")))
                ).setData(indexMap);

        parseFile(new File(BASE_PATH, "index.xml"), state);

        return indexMap;
    }

    public void parseCabFile() throws ParserConfigurationException, SAXException, IOException {

        if(index == null) {
            index = parseIndexFile();
        }

        CabPackagesData cabPackagesData = new CabPackagesData(this);
        BaseState state = createPackageStates(cabPackagesData);

        parseFile(new File(BASE_PATH, "package/package.xml"), state);
        int x = 1;
    }

    public void parseFile(File path, BaseState state) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(path, new StatefulSaxHandler().setRootParserState(state));
    }

    private BaseState createPackageStates(CabPackagesData cabPackageData) {
        return new BaseState()
                .child(new BaseState("OfflineSyncPackage")
                        .child(new BaseState("Updates")
                                .child(new UpdateState("Update")
                                        .child(new BaseState("Categories")
                                                .child(new CategoryState("Category"))
                                        )
                                        .child(new BaseState("Prerequisites")
                                                .child(new PrerequisiteState("UpdateId"))
                                                .child(new BaseState("Or")
                                                        .child(new PrerequisiteState("UpdateId"))
                                                )
                                        )
                                        .child(new BaseState("BundledBy")
                                                .child(new BundledByState("Revision"))
                                        )
                                        .child(new BaseState("SupersededBy")
                                                .child(new SupersededByState("Revision"))
                                        )
                                        .child(new BaseState("PayloadFiles")
                                                .child(new PayloadFileState("File"))
                                        )
                                        .child(new BaseState("EulaFiles")
                                                .child(new BaseState("File")
                                                        .child(new BaseState("Language"))
                                                )
                                        )
                                        .child(new BaseState("Languages")
                                                .child(new LanguageState("Language"))
                                        )
                                )
                        )
                        .child(new BaseState("FileLocations")
                                .child(new FileLocationState("FileLocation"))
                        )
                        .setData(cabPackageData) // must be after setting children to propagate the data object to them.
                );
    }

    public BaseState createPrintingStates() {
        return new BaseState(null)
                .setAllowMissingChild(true)
                .setStubSupplier(() -> new PrintingState())
                .setData(new IndentData());
    }

    public RevisionToFile getIndex() {
        return index;
    }

    public static String getBasePath() {
        return BASE_PATH;
    }
}
