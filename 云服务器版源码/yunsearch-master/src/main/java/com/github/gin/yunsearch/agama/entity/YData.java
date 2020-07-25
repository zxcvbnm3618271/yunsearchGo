package com.github.gin.yunsearch.agama.entity;

import com.github.gin.agama.annotation.Json;
import com.github.gin.agama.site.entity.AgamaEntity;
import com.github.gin.yunsearch.model.Resource;
import com.github.gin.yunsearch.model.referrer;
import com.github.gin.yunsearch.model.subnode;

import java.util.Date;
import java.util.List;

/**
 * @author GinPonson
 */
public class YData extends AgamaEntity {

    @Json("$.shareid")
    private long shareId;

    @Json("$.data_id")
    private long dataId;

    @Json("$.title")
    private String shareName;

    @Json("$.uk")
    private long uk;

    @Json("$.desc")
    private String description;

    @Json("$.feed_time")
    private Date shareTime;

    @Json("$.avatar_url")
    private String avatarUrl;

    @Json("$.updated_time")
    private Date updateTime;
    @Json("$.mo_de")
    private String mode;
    @Json("$.na_me")
    private String name;
    @Json("$.si_ze")
    private String size;
    @Json("$.user_id")
    private String userId;
    @Json("$.user_name")
    private String userName;
    @Json("$.Re_source")
    private Resource resource;
    @Json("$.subnodes")
    private List<subnode> subnodes;
    @Json("$.p_id")
    private String pid;
    @Json("$.updated_at")
    private Date updateAt;
    @Json("$.created_at")
    private Date createAt;
    @Json("$.referrers")
    private List<referrer> referrers;
    @Json("$.invalid_count")
    private Integer invalidCount;
    @Json("$.sta_te")
    private String state;
    @Json("$.access_code")
    private String accessCode;
    @Json("$.ty_pe")
    private String type;

    public long getShareId() {
        return shareId;
    }

    public void setShareId(long shareId) {
        this.shareId = shareId;
    }

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public long getUk() {
        return uk;
    }

    public void setUk(long uk) {
        this.uk = uk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public List<subnode> getSubnodes() {
        return subnodes;
    }

    public void setSubnodes(List<subnode> subnodes) {
        this.subnodes = subnodes;
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

    public List<referrer> getReferrers() {
        return referrers;
    }

    public void setReferrers(List<referrer> referrers) {
        this.referrers = referrers;
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
}
