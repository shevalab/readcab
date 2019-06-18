package com.shevalab.readcab;

import java.util.*;

public class UpdateWithRequisitesDto extends WsusUpdateDto {
    private List<Requisite> preRequisites;
    private List<Requisite> bundledByRequisites;
    private List<Requisite> supersededByRequisites;
    private List<String> payloadFiles;
    private Set<String> languages;
    private String revision;

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
        String product = getRequisite(RequisiteType.PRODUCT);
        return product == null ? super.getProduct() : Collections.singletonList(product);
    }

    @Override
    public List<String> getProductFamily() {
        String productFamily = getRequisite(RequisiteType.PRODUCT_FAMILY);
        return productFamily == null ? super.getProductFamily() : Collections.singletonList(productFamily);
    }

    private String getRequisite(RequisiteType requisiteType) {
        Optional<Requisite> requisite = getPreRequisites().stream()
                .filter(r -> r.getType().equals(requisiteType))
                .findFirst();
        if(requisite.isPresent()) {
            return requisite.get().getContent();
        }
        return null;
    }
}
