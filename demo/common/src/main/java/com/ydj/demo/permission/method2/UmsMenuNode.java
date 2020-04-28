package com.ydj.demo.permission.method2;

import lombok.*;

import java.util.List;

/**
 * 菜单节点封装
 *
 * @author yidujun
 * @date 2020/4/28 10:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsMenuNode extends UmsMenu{

    private List<UmsMenuNode> children;

    @Override
    public String toString() {
        return "UmsMenuNode{" +
                "children=" + children +
                '}';
    }
}
