package com.service.ucenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @date 2020/5/5
 */
@Data
public class RegisterVo {


    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;
}
