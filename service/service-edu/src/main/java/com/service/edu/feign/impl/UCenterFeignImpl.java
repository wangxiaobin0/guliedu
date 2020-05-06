package com.service.edu.feign.impl;

import com.common.entity.vo.SessionVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.edu.feign.UCenterFeign;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2020/5/6
 */
@Component
public class UCenterFeignImpl implements UCenterFeign {
    @Override
    public SessionVo auth(HttpServletRequest request) throws JsonProcessingException {
        return null;
    }
}
