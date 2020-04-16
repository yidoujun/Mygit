package com.ydj.demo.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Set;
import java.util.TreeSet;

/**
 * 权限类
 *
 * @author yidujun
 * @date 2020/4/16 16:11
 */
@Data
@Builder(builderMethodName = "permission")
//@NoArgsConstructor          // 无参构造方法
@AllArgsConstructor         // 全部变量有参构造方法
public class Permission implements Comparable<Permission> {

    /**
     * id
     */
    private Integer id;

    /**
     * 父级id
     */
    private Integer pId;

    /**
     * 功能编号
     */
    private String actionCode;

    /**
     * 功能描述
     */
    private String actionDesc;

    /**
     * 父级编号
     */
    private String parentCode;

    /**
     * 权限层级
     */
    private Integer actionLevel;

    /**
     * 权限排序
     */
    private Integer actionOrder;

    /**
     * 子级
     */
    private Set<Permission> childs;

    public Permission() {}

    public Permission(Integer id, String actionCode, String actionDesc, String parentCode, Integer actionLevel, Integer actionOrder) {
        this.id = id;
        this.actionCode = actionCode;
        this.actionDesc = actionDesc;
        this.parentCode = parentCode;
        this.actionLevel = actionLevel;
        this.actionOrder = actionOrder;
    }

    /**
     * 添加子级权限
     * @param permission
     */
    public void setChild(Permission permission) {
        if (childs == null) {
            childs = new TreeSet<>();
        }
        childs.add(permission);
    }

    /**
     * 排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Permission o) {
        if (o == null) {
            return -1;
        }
        return this.getActionOrder().compareTo(o.getActionOrder());
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", actionCode='" + actionCode + '\'' +
                ", actionDesc='" + actionDesc + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", actionLevel=" + actionLevel +
                ", actionOrder=" + actionOrder +
                ", childs=" + childs +
                '}';
    }
}
