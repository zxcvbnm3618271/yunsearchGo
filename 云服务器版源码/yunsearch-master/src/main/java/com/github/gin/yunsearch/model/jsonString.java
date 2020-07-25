package com.github.gin.yunsearch.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
@Entity //这个注解必备
public class jsonString implements Serializable {
    @Transient
    private String json;
    @Id //这个注解必备，必须有个id
    private String uuid;
    public jsonString(){}
    public jsonString(String json){
        this.json=json;
    }
}
