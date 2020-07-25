package com.github.gin.yunsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource implements Serializable {
private String mode;
private Date share_time;
private String name;
private String size;
private String user_id;
private String user_name;

}
