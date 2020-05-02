package com.service.common.base.config;

import com.common.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author
 * @date 2020/5/1
 */
@RestControllerAdvice
public class GlobalExceptionConfig {

    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        e.printStackTrace();
        return R.error();
    }
}
