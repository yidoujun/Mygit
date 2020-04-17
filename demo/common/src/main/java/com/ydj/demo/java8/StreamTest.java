package com.ydj.demo.java8;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 测试java8新特性--Stream
 *
 * 终结流操作：forEach和collect
 *
 * peek和map的区别：前者不会返回，后面会返回
 *
 * @author yidujun
 * @date 2020/4/16 20:34
 */
public class StreamTest {

///*    @Test
//    public void testStream() {
//
//        *//**
//         * 去除列表为空的字段
//         *//*
//        filterDemo();
//
//        *//**
//         * 迭代流中的每个数据
//         *//*
//        forEachDemo();
//
//        mapDemo();
//
//        *//**
//         * 排序
//         * 可自定义排序规则
//         *//*
//        sortedDemo();
//
//        *//**
//         * Collectors操作
//         *//*
//        collectorsDemo();
//
//        *//**
//         * 统计
//         *//*
//        intSummaryDemo();
//    }*/

//    /**
//     * filter过滤--去除列表为空的字段
//     */
//    private void filterDemo(){
//        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//        List<String> filered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
//        System.out.println(filered);
//    }
//
//    /**
//     * forEach--迭代流中的每个数据
//     */
//    private void forEachDemo() {
//        Random random = new Random();
//        random.ints().limit(10).forEach(System.out::println);
//    }
//
//    /**
//     *
//     */
//    private void mapDemo(){
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        // 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map(i -> i*i).distinct().collect(Collectors.toList()); //distinct()去除重复值
//        System.out.println(squaresList);
//    }
//
//    /**
//     * sorted---排序
//     */
//    private void sortedDemo() {
//        Random random = new Random();
//        random.ints().limit(10).sorted().forEach(System.out::println);
//    }
//
//    /**
//     * Collectors--实现归约操作
//     * 例如：将流转换成集合和聚合元素
//     * Collectors可用于返回列表或字符串
//     */
//    private void collectorsDemo() {
//        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//        // 返回不为空的列表
//        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
//        System.out.println("筛选列表：" + filtered);
//
//        // 合并字符串
//        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
//        System.out.println("合并字符串：" + mergedString);
//    }
//
//    /**
//     * 统计
//     */
//    private void intSummaryDemo() {
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
//        System.out.println("列表中最大的数：" + stats.getMax());
//        System.out.println("列表中最小的数：" + stats.getMin());
//        System.out.println("所有数之和：" + stats.getSum());
//        System.out.println("平均数：" + stats.getAverage());
//    }

    @Test
    public void java8StreamTest() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
        Random random = new Random();
        System.out.println("使用 Java 8: ");
        System.out.println("列表: " +strings);

        Long count = strings.stream().filter(string->string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        List<String> filtered = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        String mergedString = strings.stream().filter(string ->!string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        List<Integer> squaresList = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " +integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);

        // 给对象排序--按照id从高到低
        User user1 = new User(1, "ydj","123456");
        User user2 = new User(2, "ds","1233456");
        User user3 = new User(3, "rrd","533334");
        User user4 = new User(4, "wwd","355634");
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        System.out.println("排序前：");
        for (User u:users) {
            System.out.println(u);
        }
        // 多属性排序
        users = users.stream().sorted(Comparator.comparing(User::getId, Comparator.reverseOrder()).thenComparing(User::getUserName)).collect(Collectors.toList());
        System.out.println("排序后：");
        for (User u:users) {
            System.out.println(u);
        }

        /**
        * toMap方法，可以指定对象两个字段做映射
        */
//        toMapDemo(users);

        matchingDemo(users);
    }
    // 内部类
    private class User {
        private Integer id;
        private String userName;
        private String pwd;

        User() {}

        User(Integer id, String userName, String pwd){
            this.id = id;
            this.userName = userName;
            this.pwd = pwd;
        }

        public void setId(Integer id) {
            this.id = id;
        }
        public Integer getId() {
            return this.id;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getUserName() {
            return this.userName;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
        public String getPwd() {
            return this.pwd;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", pwd='" + pwd + '\'' +
                    '}';
        }
    }


    /**
     * toMap方法，可以指定对象两个字段做映射
     */
    public void toMapDemo(List<User> users){
        Map<Integer, String> userMap = users.stream().collect(Collectors.toMap(User::getId, User::getUserName));
        /*for (Map.Entry<Integer, String> map :userMap.entrySet()) {
            System.out.println(map.getKey());
            System.out.println(map.getValue());
        }*/
        // 神操作--直接提取两个属性进行映射
        String[] split = {"1", "2", "3", "4"};
        String userNames = Arrays.stream(split).map(Integer::valueOf).map(userMap::get).collect(Collectors.joining(","));
        System.out.println(userNames);
    }

    /**
     * map的神操作啊
     */
    public void matchingDemo(List<User> users){
        Map<Integer, String> userMap = users.stream().collect(Collectors.toMap(User::getId, User::getUserName));
        String[] split = {"1", "2", "4"};
        // e的值就是1、2、4
        List<Object> matching = Arrays.stream(split).map(e -> {                                     // 此处的e是split字符串
            Integer id = Integer.valueOf(e);
            User user = new User();
            user.setId(id);
            user.setUserName(userMap.get(id));
            return user;
        }).filter(e -> StringUtils.isNotBlank(e.getUserName())).collect(Collectors.toList());       // 此处的e是User对象

        System.out.println(matching);
    }

}
