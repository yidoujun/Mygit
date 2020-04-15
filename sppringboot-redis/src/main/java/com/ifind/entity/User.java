package com.ifind.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -1203696699995822134L;

    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;

}
