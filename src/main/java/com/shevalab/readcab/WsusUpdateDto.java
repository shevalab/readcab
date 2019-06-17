package com.shevalab.readcab;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class WsusUpdateDto {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("LegacyName")
    private String legacyName;
    @JsonProperty("UpdateId")
    private String updateId;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("Severity")
    private UpdateSeverity severity;
    @JsonProperty("KBArticles")
    private List<String> kBArticles;
    @JsonProperty("Product")
    private List<String> product;
    @JsonProperty("ProductFamily")
    private List<String> productFamily;
    @JsonProperty("CreationTime")
    private Date creationTime;
    @JsonProperty("ArrivalTime")
    private Date arrivalTime;
    @JsonProperty("OS")
    private List os;
    @JsonProperty("RequiresLicenseAcceptance")
    private Boolean requiresLicenseAcceptance;
    @JsonProperty("SecurityBulletins")
    private List securityBulletins;
    @JsonProperty("HasLicenseAgreement")
    private Boolean hasLicenseAgreement;
    @JsonProperty("AdditionalInformationUrls")
    private List<String> additionalInformationUrls;
    @JsonProperty("InstallationIsSupported")
    private Boolean installationIsSupported;
    @JsonProperty("InstallationRebootBehavior")
    private RebootBehavior installationRebootBehavior;
    @JsonProperty("InstallationImpact")
    private Impact installationImpact;
    @JsonProperty("InstallationCanRequestInput")
    private Boolean installationCanRequestInput;
    @JsonProperty("RemovalIsSupported")
    private Boolean removalIsSupported;
    @JsonProperty("RemovalCanRequestInput")
    private Boolean removalCanRequestInput;
    @JsonProperty("RemovalRebootBehavior")
    private RebootBehavior removalRebootBehavior;
    @JsonProperty("RemovalImpact")
    private Impact removalImpact;
    @JsonProperty("SupersededBy")
    private List<String> supersededBy;
    @JsonProperty("DependsOn")
    private List<String> dependsOn;
    @JsonProperty("IsDeclined")
    private Boolean isDeclined;
    @JsonProperty("PatchSize")
    private Long patchSize;

    public String getTitle() {
        return title;
    }

    public WsusUpdateDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLegacyName() {
        return legacyName;
    }

    public WsusUpdateDto setLegacyName(String legacyName) {
        this.legacyName = legacyName;
        return this;
    }

    public String getUpdateId() {
        return updateId;
    }

    public WsusUpdateDto setUpdateId(String updateId) {
        this.updateId = updateId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WsusUpdateDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public WsusUpdateDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public UpdateSeverity getSeverity() {
        return severity;
    }

    public WsusUpdateDto setSeverity(UpdateSeverity severity) {
        this.severity = severity;
        return this;
    }

    public List<String> getkBArticles() {
        return kBArticles;
    }

    public WsusUpdateDto setkBArticles(List<String> kBArticles) {
        this.kBArticles = kBArticles;
        return this;
    }

    public List<String> getProduct() {
        return product;
    }

    public WsusUpdateDto setProduct(List<String> product) {
        this.product = product;
        return this;
    }

    public List<String> getProductFamily() {
        return productFamily;
    }

    public WsusUpdateDto setProductFamily(List<String> productFamily) {
        this.productFamily = productFamily;
        return this;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public WsusUpdateDto setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public WsusUpdateDto setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public List getOs() {
        return os;
    }

    public WsusUpdateDto setOs(List os) {
        this.os = os;
        return this;
    }

    public Boolean getRequiresLicenseAcceptance() {
        return requiresLicenseAcceptance;
    }

    public WsusUpdateDto setRequiresLicenseAcceptance(Boolean requiresLicenseAcceptance) {
        this.requiresLicenseAcceptance = requiresLicenseAcceptance;
        return this;
    }

    public List getSecurityBulletins() {
        return securityBulletins;
    }

    public WsusUpdateDto setSecurityBulletins(List securityBulletins) {
        this.securityBulletins = securityBulletins;
        return this;
    }

    public Boolean getHasLicenseAgreement() {
        return hasLicenseAgreement;
    }

    public WsusUpdateDto setHasLicenseAgreement(Boolean hasLicenseAgreement) {
        this.hasLicenseAgreement = hasLicenseAgreement;
        return this;
    }

    public List<String> getAdditionalInformationUrls() {
        return additionalInformationUrls;
    }

    public WsusUpdateDto setAdditionalInformationUrls(List<String> additionalInformationUrls) {
        this.additionalInformationUrls = additionalInformationUrls;
        return this;
    }

    public Boolean getInstallationIsSupported() {
        return installationIsSupported;
    }

    public WsusUpdateDto setInstallationIsSupported(Boolean installationIsSupported) {
        this.installationIsSupported = installationIsSupported;
        return this;
    }

    public RebootBehavior getInstallationRebootBehavior() {
        return installationRebootBehavior;
    }

    public WsusUpdateDto setInstallationRebootBehavior(RebootBehavior installationRebootBehavior) {
        this.installationRebootBehavior = installationRebootBehavior;
        return this;
    }

    public Impact getInstallationImpact() {
        return installationImpact;
    }

    public WsusUpdateDto setInstallationImpact(Impact installationImpact) {
        this.installationImpact = installationImpact;
        return this;
    }

    public Boolean getInstallationCanRequestInput() {
        return installationCanRequestInput;
    }

    public WsusUpdateDto setInstallationCanRequestInput(Boolean installationCanRequestInput) {
        this.installationCanRequestInput = installationCanRequestInput;
        return this;
    }

    public Boolean getRemovalIsSupported() {
        return removalIsSupported;
    }

    public WsusUpdateDto setRemovalIsSupported(Boolean removalIsSupported) {
        this.removalIsSupported = removalIsSupported;
        return this;
    }

    public Boolean getRemovalCanRequestInput() {
        return removalCanRequestInput;
    }

    public WsusUpdateDto setRemovalCanRequestInput(Boolean removalCanRequestInput) {
        this.removalCanRequestInput = removalCanRequestInput;
        return this;
    }

    public RebootBehavior getRemovalRebootBehavior() {
        return removalRebootBehavior;
    }

    public WsusUpdateDto setRemovalRebootBehavior(RebootBehavior removalRebootBehavior) {
        this.removalRebootBehavior = removalRebootBehavior;
        return this;
    }

    public Impact getRemovalImpact() {
        return removalImpact;
    }

    public WsusUpdateDto setRemovalImpact(Impact removalImpact) {
        this.removalImpact = removalImpact;
        return this;
    }

    public List<String> getSupersededBy() {
        return supersededBy;
    }

    public WsusUpdateDto setSupersededBy(List<String> supersededBy) {
        this.supersededBy = supersededBy;
        return this;
    }

    public List<String> getDependsOn() {
        return dependsOn;
    }

    public WsusUpdateDto setDependsOn(List<String> dependsOn) {
        this.dependsOn = dependsOn;
        return this;
    }

    public Boolean getDeclined() {
        return isDeclined;
    }

    public WsusUpdateDto setDeclined(Boolean declined) {
        isDeclined = declined;
        return this;
    }

    public Long getPatchSize() {
        return patchSize;
    }

    public WsusUpdateDto setPatchSize(Long patchSize) {
        this.patchSize = patchSize;
        return this;
    }
}