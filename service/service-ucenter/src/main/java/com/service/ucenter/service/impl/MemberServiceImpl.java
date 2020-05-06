package com.service.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ucenter.entity.Member;
import com.service.ucenter.entity.vo.LoginVo;
import com.service.ucenter.entity.vo.RegisterVo;
import com.service.ucenter.entity.vo.SessionVo;
import com.service.ucenter.mapper.MemberMapper;
import com.service.ucenter.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2020-05-05
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    RedisTemplate redisTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String SMS_CODE_PREFIX = "SMS:REGISTER:CODE:";
    @Override
    public String login(LoginVo loginVo) throws JsonProcessingException {
        String mobile = loginVo.getMobile();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", mobile);
        Member member = this.getOne(queryWrapper);
        if (member == null) {
            throw new RuntimeException("该用户不存在");
        }
        if (!(loginVo.getPassword()).equals(member.getPassword())){
            throw new RuntimeException("用户名或密码错误");
        }
        if (member.getIsDisabled()) {
            throw new RuntimeException("该用户已被引用");
        }
        SessionVo sessionVo = new SessionVo();
        BeanUtils.copyProperties(member, sessionVo);
        String session = mapper.writeValueAsString(sessionVo);
        String token = JwtUtil.createJwtToken(session);
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        if (StringUtils.isEmpty(code)) {
            throw new RuntimeException("验证码不能位空");
        }

        String key = SMS_CODE_PREFIX + registerVo.getMobile();

        String session_code = (String) redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(session_code)) {
            throw new RuntimeException("验证码已过期，请重新获取");
        }
        if (!session_code.equals(code)) {
            throw new RuntimeException("验证码不正确");
        }

        //验证成功，删除验证码
        redisTemplate.delete(key);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", registerVo.getMobile());
        int count = this.count(queryWrapper);
        if (count > 0) {
            throw new RuntimeException("该手机号已被注册");
        }
        Member member = new Member();
        BeanUtils.copyProperties(registerVo, member);
        member.setIsDisabled(false);
        member.setIsDeleted(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }

    @Override
    public SessionVo auth(HttpServletRequest request) throws JsonProcessingException{
        String auth = request.getHeader("token");
        if (StringUtils.isEmpty(auth)) {
            throw new RuntimeException("登录已过期，请重新登录");
        }
        String session = (String) JwtUtil.getTokenInfo(auth);
        if (session == null) {
            throw new RuntimeException("非法token");
        }
        SessionVo sessionVo = mapper.readValue(session, SessionVo.class);
        return sessionVo;
    }
}
