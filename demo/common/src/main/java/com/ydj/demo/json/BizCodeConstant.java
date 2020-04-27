package com.ydj.demo.json;

/**
 * 自行定义各业务模块的异常码和明细异常描述
 *
 * @author yidujun
 * @date 2020/4/27 10:27
 */
public class BizCodeConstant extends CommonCodeConstant {

    /**
     * 业务异常
     */
    public static final Integer BUSINESS_EXCEPTION_CODE = -40000;
    public static final String BUSINESS_EXCEPTION_MSG = "发生业务异常";

    public enum BizBaseCodeEnum implements CommonCode {
        ASSERT_EXCEPTION("assert check error", "断言校验异常"),

        OTHER_EXCEPTION("other error", "fail");

        BizBaseCodeEnum(String subCode, String subMsg) {
            this.subCode = subCode;
            this.subMsg = subMsg;
        }

        private String subCode;
        private String subMsg;

        @Override
        public String getSubCode() {
            return subCode;
        }

        @Override
        public String getSubMsg() {
            return subMsg;
        }
    }
}
