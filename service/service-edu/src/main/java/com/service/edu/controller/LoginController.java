package com.service.edu.controller;

import com.common.utils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @date 2020/5/1
 */
@RestController
public class LoginController {

    @PostMapping("/user/login")
    public R login() {
        return R.ok().put("token", "admin");
    }
    @GetMapping("/user/info")
    public R info() {

        return R.ok().put("roles", "[admin]").put("name", "admin").put("avatar", "https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/07/91871e25-fd83-4af6-845f-ea8d471d825d.png");
    }
}
