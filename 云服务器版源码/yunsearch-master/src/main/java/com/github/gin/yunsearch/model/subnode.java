package com.github.gin.yunsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class subnode implements Serializable {
private String name;
private String mode;
private String size;
}
