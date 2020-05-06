package com.service.ucenter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.service.ucenter.entity.vo.LoginVo;
import com.service.ucenter.entity.vo.RegisterVo;
import com.service.ucenter.entity.vo.SessionVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author wxb
 * @since 2020-05-05
 */
public interface IMemberService extends IService<Member> {

    /**
     * 登录
     * @param loginVo
     */
    String login(LoginVo loginVo) throws JsonProcessingException;

    /**
     * 注册
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 根据token获取信息
     * @param request
     * @return
     */
    SessionVo auth(HttpServletRequest request) throws JsonProcessingException;
}
