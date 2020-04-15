package com.ifind.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Employee {

    private Integer id;
    private String empName;
    private String empPhone;
    private String empPosition;
    private String empDepartment;

}
