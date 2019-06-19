package com.shevalab.readcab;

import java.util.*;
import java.util.stream.Collectors;

public class UpdateWithRequisitesDto extends WsusUpdateDto {
    private List<Requisite> preRequisites;
    private List<Requisite> bundledByRequisites;
    private List<Requisite> supersededByRequisites;
    private List<String> payloadFiles;
    private Set<String> languages;
    private String revision;

    private List<Requisite> mergedDistincePreRequisites; // cache merged distinct requisites

    public List<Requisite> getPreRequisites() {
        if(preRequisites == null) {
            preRequisites = new LinkedList<>();
        }
        return preRequisites;
    }

    public List<Requisite> getBundledByRequisites() {
        if(bundledByRequisites == null) {
            bundledByRequisites = new LinkedList<>();
        }
        return bundledByRequisites;
    }

    public List<Requisite> getSupersededByRequisites() {
        if(supersededByRequisites == null) {
            supersededByRequisites = new LinkedList<>();
        }
        return supersededByRequisites;
    }

    public UpdateWithRequisitesDto addPayloadFile(String file) {
        if(payloadFiles == null) payloadFiles = new LinkedList<>();
        payloadFiles.add(file);
        return this;
    }

    public List<String> getPayloadFiles() {
        return payloadFiles;
    }

    public Set<String> getLanguages() {
        if(languages == null) {
            languages = new TreeSet<>();
        }
        return languages;
    }

    public String getRevision() {
        return revision;
    }

    public UpdateWithRequisitesDto setRevision(String revision) {
        this.revision = revision;
        return this;
    }

    @Override
    public String getCategory() {
        String category = getRequisite(RequisiteType.CLASSIFICATION);
        return category == null ? super.getCategory() : category;
    }

    @Override
    public List<String> getProduct() {
        List<String> product = getRequisites(RequisiteType.PRODUCT);
        return product.isEmpty() ? super.getProduct() : product;
    }

    @Override
    public List<String> getProductFamily() {
        List<String> productFamily = getRequisites(RequisiteType.PRODUCT_FAMILY);
        return productFamily.isEmpty() ? super.getProductFamily() : productFamily;
    }

    private String getRequisite(RequisiteType requisiteType) {
        Optional<Requisite> requisite = getMergedDistinctPreRequisites().stream()
                .filter(r -> r.getType().equals(requisiteType))
                .findFirst();
        if(requisite.isPresent()) {
            return requisite.get().getContent();
        }
        return null;
    }

    private List<String> getRequisites(RequisiteType requisiteType) {
        return getMergedDistinctPreRequisites().stream()
                .filter(r -> r.getType().equals(requisiteType))
                .map(Requisite::getContent)
                .collect(Collectors.toList());
    }

    private List<Requisite> getMergedDistinctPreRequisites() {
        if(mergedDistincePreRequisites == null) mergedDistincePreRequisites = getPreRequisites().stream()
                .map(r -> r.thisWithDependenciesRecursive())
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        return mergedDistincePreRequisites;
    }
}
