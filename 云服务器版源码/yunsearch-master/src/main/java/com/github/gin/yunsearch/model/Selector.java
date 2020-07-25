package com.github.gin.yunsearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@ToString
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Selector {
    bdy("dl.pickpw dd > input","dl.pickpw a > span","div.verify-form dl.pickpw > dt");

    @Getter
    @Setter
    private final String input;
    @Getter
    @Setter
    private final String submit;
    @Getter
    @Setter
    private final String notice;

    private Selector(String input,String submit,String notice){
        this.input=input;
        this.submit=submit;
        this.notice=notice;
    }
}
