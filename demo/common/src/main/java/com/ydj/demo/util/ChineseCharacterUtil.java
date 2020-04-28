package com.ydj.demo.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取首字母工具
 *
 * @author yidujun
 * @date 2020/4/28 15:14
 */
public class ChineseCharacterUtil {


    @Test
    public void test() {
        System.out.println(getUpperCase("这是一个测试 This is test", false));
    }

    /**
     * 获取汉字首字母或全拼大写字母
     *
     * @param chinese               汉字
     * @param isFull                isFull == true ? 全拼 : 首字母
     *
     * @return                      全拼或者首字母大写字符串
     */
    public static String getUpperCase(String chinese, boolean isFull) {
        return convertHanzi2Pinyin(chinese, isFull).toUpperCase();
    }

    /**
     * 获取汉字首字母或全拼小写字母
     *
     * @param chinese               汉字
     * @param isFull                isFull == true ? 全拼 : 首字母
     *
     * @return                      全拼或者首字母小写字符串
     */
    public static String getLowerCase(String chinese, boolean isFull) {
        return convertHanzi2Pinyin(chinese, isFull).toLowerCase();
    }

    private static String convertHanzi2Pinyin(String chinese, boolean isFull) {

        /***
         * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言
         * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
         * ^[\u4E00-\u9FA5]+$ 匹配简体
         */
        String regExp = "^[\u4E00-\u9FFF]+$";
        StringBuffer sb = new StringBuffer();
        if (chinese == null || "".equals(chinese.trim())) {
            return "";
        }
        String spell = "";
        for (int i = 0; i < chinese.length(); i++) {
            char unit = chinese.charAt(i);
            // 是汉字，转拼音
            if (match(String.valueOf(unit), regExp)) {
                spell = convertSingleHanzi2Pinyin(unit);
                if (isFull) {
                    sb.append(spell);
                } else {
                    sb.append(spell.charAt(0));
                }
            } else {
                sb.append(unit);
            }
        }
        return sb.toString();
    }

    /**
     * 将单个汉字转成拼音
     *
     * @param chinese              汉字字符
     *
     * @return                     拼音
     */
    private static String convertSingleHanzi2Pinyin(char chinese){
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] res;
        StringBuffer sb=new StringBuffer();
        try {
            res = PinyinHelper.toHanyuPinyinStringArray(chinese,outputFormat);
            //对于多音字，只用第一个拼音
            sb.append(res[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }


    /***
     * 匹配
     * <P>
     * 根据字符和正则表达式进行匹配
     *
     * @param str                     源字符串
     * @param regex                   正则表达式
     *
     * @return                        true：匹配成功  false：匹配失败
     */
    private static boolean match(String str,String regex){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        return matcher.find();
    }
}
