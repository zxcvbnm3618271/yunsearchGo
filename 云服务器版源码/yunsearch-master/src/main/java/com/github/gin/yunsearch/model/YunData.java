package com.github.gin.yunsearch.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyName;
import com.google.gson.annotations.SerializedName;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jboss.logging.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author GinPonson
 */
@Entity
@Table(name = "yun_data")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class YunData implements Serializable {


    @Column(name = "uuid")
    @JSONField(name = "uuid")
    private String uuid;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JSONField
    private Long id;

    @Column(name = "shareid")
    @JSONField(name = "shareid")
    @JsonProperty("shareid")
    private Long shareId;

    @Column(name = "dataid")
    @JSONField(name = "dataid")
    @JsonProperty("dataid")
    private Long dataId;

    @Column(name = "sharename")
    @JSONField(name = "sharename")
    @JsonProperty("sharename")
    private String shareName;

    @Column(name = "uk")
    @JSONField(name = "uk")
    private Long uk;

    @Column(name = "description")
    @JSONField(name = "description")
    private String description;

    @Column(name = "updatetime")
    @JSONField(name = "updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("updatetime")
    private Date updateTime;

    @Column(name = "sharetime")
    @JSONField(name = "sharetime")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("sharetime")
    private Date shareTime;

    @Column(name = "avatarurl")
    @JSONField(name = "avatarurl")
    @JsonProperty("avatarurl")
    private String avatarUrl;

    @Version
    @Column
    @JSONField
    private Integer version;


    @Column(name = "mode")
    @JSONField(name = "mode")
    private String mode;

    @Column(name = "name")
    @JSONField(name = "name")
    private String name;

    @Column(name = "size")
    @JSONField(name = "size")
    private String size;

    @Column(name = "userid")
    @JSONField(name = "userid")
    @JsonProperty("userid")
    private String userId;

    @Column(name = "username")
    @JSONField(name = "username")
    @JsonProperty("username")
    private String userName;

//    @Type( type = "json" )
//    @Column(name="Re_source",columnDefinition = "json")
//    @ElementCollection(targetClass=Resource.class)
    @Column(name="Resource")
    @JSONField(name = "Resource")
    @JsonProperty("Resource")
    private String resource;

//    @Type( type = "json" )
//    @Column(name = "subnodes",columnDefinition = "json")
//    @ElementCollection(targetClass=subnode.class)
    @Column(name = "subnodes")
    @JSONField(name = "subnodes")
    private String subnodes;



    @Column(name = "pid")
    @JSONField(name = "pid")
    private String pid;

    @Column(name = "updatedat")
    @JSONField(name = "updatedat")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("updateat")
    private Date updateAt;

    @Column(name = "createdat")
    @JSONField(name = "createdat")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("createat")
    private Date createAt;

//    @Type( type = "json" )
//    @Column(name = "referrers",columnDefinition = "json")
    //    @ElementCollection(targetClass=referrer.class)
    @Column(name = "referrers")
    @JSONField(name = "referrers")
    private String referrers;

    @Column(name = "invalidcount")
    @JSONField(name = "invalidcount")
    private Integer invalidCount;

    @Column(name = "state")
    @JSONField(name = "state")
    private String state;

    @Column(name = "accesscode")
    @JSONField(name = "accesscode")
    @JsonProperty("accesscode")
    private String accessCode;

    @Column(name = "type")
    @JSONField(name = "type")
    private String type;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public Long getUk() {
        return uk;
    }

    public void setUk(Long uk) {
        this.uk = uk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getSubnodes() {
        return subnodes;
    }

    public void setSubnodes(String subnodes) {
        this.subnodes = subnodes;
    }

    public String getReferrers() {
        return referrers;
    }

    public void setReferrers(String referrers) {
        this.referrers = referrers;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public Integer getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(Integer invalidCount) {
        this.invalidCount = invalidCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public YunData(Long id, Long shareId, Long dataId, String shareName, Long uk, String description, Date updateTime, Date shareTime, String avatarUrl, Integer version, String mode, String name, String size, String userId, String userName, String resource, String subnodes, String pid, Date updateAt, Date createAt, String referrers, Integer invalidCount, String state, String accessCode, String type) {
        this.id = id;
        this.shareId = shareId;
        this.dataId = dataId;
        this.shareName = shareName;
        this.uk = uk;
        this.description = description;
        this.updateTime = updateTime;
        this.shareTime = shareTime;
        this.avatarUrl = avatarUrl;
        this.version = version;
        this.mode = mode;
        this.name = name;
        this.size = size;
        this.userId = userId;
        this.userName = userName;
        this.resource = resource;
        this.subnodes = subnodes;
        this.pid = pid;
        this.updateAt = updateAt;
        this.createAt = createAt;
        this.referrers = referrers;
        this.invalidCount = invalidCount;
        this.state = state;
        this.accessCode = accessCode;
        this.type = type;
    }

    public YunData() {

    }

    public YunData(String avatarurl,String sharename,String sharetime,String description) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.avatarUrl=avatarurl;
        this.shareName=sharename;
        this.shareTime=df.parse(sharetime);
        this.description=description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        YunData data = (YunData) o;
        return Objects.equals(uuid, data.uuid);
    }


    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
