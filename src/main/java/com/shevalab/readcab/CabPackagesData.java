package com.shevalab.readcab;

import java.util.*;

public class CabPackagesData {
    private final CabParserSaxHelper parserHelper;
    private Map<String, Requisite> updateRequisiteMap = new HashMap<>();
    private Collection<UpdateWithRequisitesDto> updates = new LinkedList<>();
    private Collection<PayloadFile> payloadFiles = new LinkedList<>();
    private boolean englishProperties = false;
    private UpdateWithRequisitesDto currentUpdate = null;
    private PayloadFile currentFile = null;
    private Requisite currentCategoryInfo;

    public CabPackagesData(CabParserSaxHelper cabParserSaxHelper) {
        this.parserHelper = cabParserSaxHelper;
    }

    public Map<String, Requisite> getUpdateRequisiteMap() {
        return updateRequisiteMap;
    }

    public Collection<UpdateWithRequisitesDto> getUpdates() {
        return updates;
    }

    public Collection<PayloadFile> getPayloadFiles() {
        return payloadFiles;
    }

    public UpdateWithRequisitesDto getCurrentUpdate() {
        return currentUpdate;
    }

    public void setCurrentUpdate(UpdateWithRequisitesDto currentUpdate) {
        this.currentUpdate = currentUpdate;
    }

    public PayloadFile getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(PayloadFile currentFile) {
        this.currentFile = currentFile;
    }

    public Requisite getOrCreateRequisite(String id, RequisiteType requisiteType) {
        return getUpdateRequisiteMap().computeIfAbsent(id, key -> new Requisite().setType(requisiteType));
    }

    public Requisite getOrCreateRequisite(String id) {
        return getUpdateRequisiteMap().computeIfAbsent(id, key -> new Requisite());
    }

    public CabParserSaxHelper getParserHelper() {
        return parserHelper;
    }

    public boolean isEnglishProperties() {
        return englishProperties;
    }

    public CabPackagesData setEnglishProperties(boolean englishProperties) {
        this.englishProperties = englishProperties;
        return this;
    }

    public Requisite getCurrentCategoryInfo() {
        return currentCategoryInfo;
    }

    public CabPackagesData setCurrentCategoryInfo(Requisite currentCategoryInfo) {
        this.currentCategoryInfo = currentCategoryInfo;
        return this;
    }
}
