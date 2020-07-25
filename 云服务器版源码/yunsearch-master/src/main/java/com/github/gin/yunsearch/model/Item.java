package com.github.gin.yunsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Item implements Serializable {
    private String uuid;
    private String type;
    private String pid;
    private Date updated_at;
    private Date created_at;
    private Integer invalid_count=0;
    private String state;
    private String access_code;
    public Item(String uuid,String type,String pid,Date updated_at,Date created_at,Integer invalid_count,String state,String access_code){
        this.uuid=uuid;
        this.pid=pid;
        this.type=type;
        this.updated_at=updated_at;
        this.created_at=created_at;
        this.invalid_count=invalid_count;
        this.state=state;
        this.access_code=access_code;
    }

    public Item() {
    }
}
