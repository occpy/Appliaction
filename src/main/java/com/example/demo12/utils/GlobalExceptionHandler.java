package com.example.demo12.utils;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({SaTokenException.class})
    public SaResult handlerSaTokenException(SaTokenException e) {

        // 根据不同异常细分状态码返回不同的提示
//        if(e.getCode() == 20001) {
//            return SaResult.error("redirect 重定向 url 是一个无效地址");
//        }
//        if(e.getCode() == 20002) {
//            return SaResult.error("redirect 重定向 url 不在 allowUrl 允许的范围内");
//        }
//        if(e.getCode() == 20004) {
//            return SaResult.error("提供的 ticket 是无效的");
//        }
        // 更多 code 码判断 ...
        if (e.getCode() != -1){
            return SaResult.error().setCode(e.getCode()).setMsg(e.getMessage());
        }
        // 默认的提示
        return SaResult.error("服务器繁忙，请稍后重试...");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SaResult methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        logger.error("MethodArgumentNotValidHandler",exception);
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String msg = fieldErrors.stream().findFirst().map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage())).orElse(null);
        return SaResult.error(msg);
    }

    @ExceptionHandler(RuntimeException.class)
    public SaResult handleRuntimeException(RuntimeException e) {
        logger.error("handleRuntimeException",e);
        return SaResult.error("系统异常");
    }

    @ExceptionHandler(Exception.class)
    public SaResult handleException(Exception e) {
        logger.error("handleException",e);
        return SaResult.error("系统异常");
    }
}

