package org.study.web.aop;

/**
 * @author lipo
 * @version v1.0
 * @date 2020-01-10 14:29
 */
public class BizException extends RuntimeException {
    private int code;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
