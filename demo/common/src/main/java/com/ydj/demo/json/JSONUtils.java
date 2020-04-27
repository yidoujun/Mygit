package com.ydj.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 *
 * @author yidujun
 * @date 2020/4/27 10:45
 */
public class JSONUtils {
    private static final SerializerFeature[] features = {
      SerializerFeature.SkipTransientField,
      SerializerFeature.WriteDateUseDateFormat,
      SerializerFeature.WriteMapNullValue,
      SerializerFeature.WriteNullListAsEmpty,
      SerializerFeature.WriteNullBooleanAsFalse,
      SerializerFeature.WriteNullStringAsEmpty };

    public static String toJsonString(Object object) {
        return JSONObject.toJSONString(object, features);
    }

    public static String toJsonpString(Object obj, String callback) {
        return callback + '(' + toJsonString(obj) + ")";
    }

    public static String toJsonResultString(Object obj) {
        JsonResult jsonResult = JsonResult.buildSuccess(obj);
        return JSONObject.toJSONString(jsonResult, features);
    }

    public static String toJsonResultString(Object obj, String callback) {
        return callback + "(" + toJsonResultString(obj) + ")";
    }

    public static Object parseObjectOrArray (String json, Class<?> clazz) {
        Object o = JSON.parse(json);
        if ((o instanceof JSONArray)) {
            o = JSONObject.parseArray(json, clazz);
        } else {
            o = JSONObject.parseObject(json, clazz);
        }
        return o;
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

    public static <T> T parseObject(String json, TypeReference<T> type) {
        return JSONObject.parseObject(json, type);
    }

    public static <T> T parseObject(JSONObject json, Class<T> clazz) {
        return json.toJavaObject(clazz);
    }

    public static <T> List<T> parseArray(String json, Class<T> clazz) {
        return JSONObject.parseArray(json, clazz);
    }

    public static <T> List<T> parseArray(JSONArray json, Class<T> clazz) {
        return json.toJavaList(clazz);
    }

    /**
     * 对JSON每一个KEY的VALUE文本中可能引起问题的特殊字符做JS转义 双引号，单引号都转义
     * @param json
     * @return
     */
    public static String escapeValue(String json) {
        if(StringUtils.isBlank(json)) return json;

        json = json.replaceAll("\\\\", "\\\\\\\\");
        json = json.replaceAll("\"", "\\\\\"");
        json = json.replaceAll("'", "\\\\'");
        json = json.replaceAll("\b", "\\\\b");
        json = json.replaceAll("\n", "\\\\n");
        json = json.replaceAll("\t", "\\\\t");
        json = json.replaceAll("\f", "\\\\f");
        json = json.replaceAll("\r", "\\\\r");

        return json;
    }

}
