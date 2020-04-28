package com.ydj.demo.permission.method1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 添加权限树
 *
 * @author yidujun
 * @date 2020/4/16 16:28
 */
public class PermissonTest {

    List<Permission> permissions = new ArrayList<Permission>();

    /**
     * 构建权限，用于模拟从数据库取出权限
     */
    @Before
    public void setPermisson() {
        // 构建一级权限
        Permission p1_1 = new Permission(1, "001", "查证管理", "", 1, 1);
        Permission p1_2 = new Permission(2, "002", "查证配置", "", 1, 2);

        // 构建二级权限
        Permission p2_1 = new Permission(3, "001001", "进度查询", "001", 2, 100999);
        Permission p2_2 = new Permission(4, "001002", "修改查询", "001", 2, 200);
        Permission p2_3 = new Permission(5, "002001", "修改查证顺序", "002", 2, 300999);
        Permission p2_4 = new Permission(6, "002002", "配置人员信息", "002", 2, 400);

        // 构建三级权限
        Permission p3_1 = new Permission(7, "001001001", "随便1", "001001", 3, 100001);
        Permission p3_2 = new Permission(8, "001002002", "随便2", "001002", 3, 200001);
        Permission p3_3 = new Permission(9, "002001001", "随便3", "002001", 3, 300001);
        Permission p3_4 = new Permission(10, "002002002", "随便4", "002002", 3, 400001);

        // 手动打乱顺序
        permissions.add(p3_1);
        permissions.add(p3_2);
        permissions.add(p3_3);
        permissions.add(p3_4);
        permissions.add(p1_1);
        permissions.add(p2_4);
        permissions.add(p1_2);
        permissions.add(p2_1);
        permissions.add(p2_2);
        permissions.add(p2_3);
    }

    @Test
    public void permissonTreeTest() {
        System.out.println("排序前");
        for (Permission p: permissions) {
            System.out.println(p);
        }

        permissions = permissions.stream().sorted(Comparator.comparing(Permission::getId).thenComparing(Permission::getActionOrder)).collect(Collectors.toList());
        System.out.println("排序后");
        for (Permission p: permissions) {
            System.out.println(p);
        }
        // 1.利用java8新特性给集合分组并排序
        Map<Integer, List<Permission>> listMap = permissions.stream()
                .filter(e -> e.getActionLevel() != null)
                .collect(Collectors.groupingBy(Permission::getActionLevel));
        /*for (Map.Entry<Integer, List<Permission>> map : listMap.entrySet()) {
            System.out.println(map.getKey());
            for (Permission p : map.getValue()) {
                System.out.println(p);
            }
            System.out.println("==========================");
        }*/

        // 2.创建一个boss权限对象
        Permission root = new Permission(0, "", "权限", null, 0, 0);

        buildTree(root, listMap, 1);

        System.out.println(root);
    }

    /**
     * 构建权限树
     * @param parent
     * @param childs
     * @param level
     */
    private void buildTree(Permission parent, Map<Integer, List<Permission>> childs, Integer level) {
        // 获取当前level层的节点
        List<Permission> permissions = childs.get(level);
        if (permissions == null) {
            return;
        }
        for (Permission permission : permissions) {
            // 如果是父子关系，则将permission加入到parent中
            if (permission.getParentCode().equals(parent.getActionCode())) {
                parent.setChild(permission);
                permission.setPId(parent.getId());
            }
        }
        int nextLevel = level + 1;

        for (Permission p:permissions) {
            // 递归
            buildTree(p, childs, nextLevel);
        }
    }

}
