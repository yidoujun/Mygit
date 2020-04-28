package com.ydj.demo.permission.method2;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单按钮
 *
 * @author yidujun
 * @date 2020/4/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String title;


}
