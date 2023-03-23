package com.yupi.yupaiinterface.controller;


import com.yupi.yupiclientsdk.model.User;
import com.yupi.yupiclientsdk.utils.SignUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mv
 * @date 2023/3/22 16:51
 * 名称API
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name) {

        return "GET 你的名字是：" + name;
    }


    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String secretKey = request.getHeader("secretKey");
        String nonce = request.getHeader("nonce");
        String body = request.getHeader("body");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");

        // todo 实际情况下，应是去数据库查是否已分配给用户
        if(!"yupi".equals(accessKey)) {
            throw new RuntimeException("无权限");
        }
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }

        // todo timestamp校验，不超过五分钟

        // todo 实际情况中，是从数据库中查出secretKey
        String serverSign = SignUtils.getSign(body, "12345678");
        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }


        return "POST 用户名是：" + user.getUsername();
    }
}
