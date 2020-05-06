package com.service.sms.config;

import com.common.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author
 * @date 2020/5/5
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R deal(Exception e) {
        return R.error().message(e.getMessage());
    }
}
