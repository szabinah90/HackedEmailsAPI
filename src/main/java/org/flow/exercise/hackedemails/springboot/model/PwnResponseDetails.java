package org.flow.exercise.hackedemails.springboot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PwnResponseDetails {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Domain")
    private String domain;

    @JsonProperty("BreachDate")
    private Date breachDate;

    @JsonProperty("AddedDate")
    private Date addedDate;

    @JsonProperty("ModifiedDate")
    private Date modifiedDate;

    @JsonProperty("PwnCount")
    private long pwnCount;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("DataClasses")
    private String[] dataClasses;

    @JsonProperty("IsVerified")
    private boolean verified;

    @JsonProperty("IsFabricated")
    private boolean fabricated;

    @JsonProperty("IsSensitive")
    private boolean sensitive;

    @JsonProperty("IsActive")
    private boolean active;

    @JsonProperty("IsRetired")
    private boolean retired;

    @JsonProperty("IsSpamList")
    private boolean spamList;

    @JsonProperty("LogoType")
    private String logoType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getBreachDate() {
        return breachDate;
    }

    public void setBreachDate(Date breachDate) {
        this.breachDate = breachDate;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getPwnCount() {
        return pwnCount;
    }

    public void setPwnCount(long pwnCount) {
        this.pwnCount = pwnCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getDataClasses() {
        return dataClasses;
    }

    public void setDataClasses(String[] dataClasses) {
        this.dataClasses = dataClasses;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isFabricated() {
        return fabricated;
    }

    public void setFabricated(boolean fabricated) {
        this.fabricated = fabricated;
    }

    public boolean isSensitive() {
        return sensitive;
    }

    public void setSensitive(boolean sensitive) {
        this.sensitive = sensitive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public boolean isSpamList() {
        return spamList;
    }

    public void setSpamList(boolean spamList) {
        this.spamList = spamList;
    }

    public String getLogoType() {
        return logoType;
    }

    public void setLogoType(String logoType) {
        this.logoType = logoType;
    }

    @Override
    public String toString() {
        return "PwnResponseDetails{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                ", breachDate=" + breachDate +
                ", addedDate=" + addedDate +
                ", modifiedDate=" + modifiedDate +
                ", pwnCount=" + pwnCount +
                ", description='" + description + '\'' +
                ", dataClasses=" + Arrays.toString(dataClasses) +
                ", verified=" + verified +
                ", fabricated=" + fabricated +
                ", sensitive=" + sensitive +
                ", active=" + active +
                ", retired=" + retired +
                ", spamList=" + spamList +
                ", logoType='" + logoType + '\'' +
                '}';
    }
}

