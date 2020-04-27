package com.ifind.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

/**
 * 服务器响应信息
 *
 * @author yidujun
 * @date 2020/4/11 10:13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseVO<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseVO(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(ResponseStatus status, T data) {
        this(status.getCode(), status.getMessage(), data);
    }

    /**
     * 转为JSON格式
     * @return
     */
    public String toJson() {
        T t = this.getData();
        if (t instanceof List || t instanceof Collection) {
            return JSONObject.toJSONString(this, SerializerFeature.WriteNullListAsEmpty);
        } else {
            return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
        }
    }
}
