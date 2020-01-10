package org.study.web.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.web.model.Result;

/**
 * @author lipo
 * @version v1.0
 * @date 2020-01-10 14:28
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Result jsonBizExceptionHandler(BizException e) {
        return new Result<>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result jsonExceptionHandler(Exception e) {
        return Result.error0();
    }

}
