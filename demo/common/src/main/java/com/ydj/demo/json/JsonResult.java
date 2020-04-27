package com.ydj.demo.json;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公共错误码
     * @see CommonCodeConstant
     */
    @Setter
    @Getter
    private Integer code;

    /**
     * 公共错误信息
     */
    @Setter
    @Getter
    private String msg;

    /**
     * 业务自定义错误码
     */
    @Setter
    @Getter
    private String subCode;

    /**
     * 业务自定义错误信息
     */
    @Setter
    @Getter
    private String subMsg;

    @Setter
    @Getter
    private T data;

    public JsonResult() {
        this.code = CommonCodeConstant.SUCCESS_CODE;
        this.msg = CommonCodeConstant.SUCCESS_MSG;
    }

    public JsonResult(T data) {
        this.code = CommonCodeConstant.SUCCESS_CODE;
        this.msg = CommonCodeConstant.SUCCESS_MSG;
        this.data = data;
    }

    public JsonResult(CommonCode exceptionCode) {
        this.code = BizCodeConstant.BUSINESS_EXCEPTION_CODE;
        this.msg = BizCodeConstant.BUSINESS_EXCEPTION_MSG;
        this.subCode = exceptionCode.getSubCode();
        this.subMsg = exceptionCode.getSubMsg();
    }

    public JsonResult(Integer code, String msg, String subCode, String subMsg) {
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.data = null;
    }

    public JsonResult(Integer code, String msg, String subCode, String subMsg, T date) {
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
        this.data = date;
    }

    public static <C> JsonResult<C> build(Integer code, String msg, String subCode, String subMsg, C date) {
        return new JsonResult(code, msg, subCode, subMsg, date);
    }

    public static JsonResult buildSuccess() {
        return build(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_MSG, null, null, null);
    }

    public static <C> JsonResult<C> buildSuccess(C date) {
        return build(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_MSG, null, null,date);
    }

    public static JsonResult buildBizError(String subCode, String subMsg) {
        return build(BizCodeConstant.BUSINESS_EXCEPTION_CODE, BizCodeConstant.BUSINESS_EXCEPTION_MSG, subCode, subMsg, null);
    }

    public static <C> JsonResult<C> buildBizError(String subCode, String subMsg, C data) {
        return build(BizCodeConstant.BUSINESS_EXCEPTION_CODE, BizCodeConstant.BUSINESS_EXCEPTION_MSG, subCode, subMsg, data);
    }


}
