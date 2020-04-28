package com.ydj.demo.permission.method2;

import com.ydj.demo.json.JSONUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单测试
 *
 * @author yidujun
 * @date 2020/4/28
 */
public class MenuTest {

    private List<UmsMenu> menuList = new ArrayList<UmsMenu>();

    @Before
    public void setUmsMenus() {
        // 构建一级菜单
        UmsMenu m1_1 = new UmsMenu(1L, 0L, "用户管理");
        UmsMenu m1_2 = new UmsMenu(2L, 0L, "会员管理");

        // 构建二级菜单
        UmsMenu m2_1 = new UmsMenu(10L, 1L, "个人中心");
        UmsMenu m2_2 = new UmsMenu(20L, 2L, "会员中心");

        // 构建三级菜单
        UmsMenu m3_1 = new UmsMenu(100L, 10L, "修改密码");
        UmsMenu m3_2 = new UmsMenu(101L, 10L, "查看订单");
        UmsMenu m3_3 = new UmsMenu(102L, 20L, "添加会员");
        UmsMenu m3_4 = new UmsMenu(103L, 20L, "删除会员");

        // 手动添加并打乱顺序
        menuList.add(m2_1);
        menuList.add(m3_1);
        menuList.add(m3_3);
        menuList.add(m3_4);
        menuList.add(m3_2);
        menuList.add(m1_1);
        menuList.add(m1_2);
        menuList.add(m2_2);
    }

    @Test
    public void treeList() {
        System.out.println("构造前：");
        for (UmsMenu menu : menuList) {
            System.out.println(menu);
        }

        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList))
                .collect(Collectors.toList());
        System.out.println("构造后：");
        for (UmsMenuNode menu : result) {
            System.out.println(menu);
        }
//        System.out.println(JSONUtils.toJsonString(result));
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     * @param menu
     * @param menuList
     * @return
     */
    public UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        // 浅拷贝
        BeanUtils.copyProperties(menu, node);
        System.out.println(node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList))
                .collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
