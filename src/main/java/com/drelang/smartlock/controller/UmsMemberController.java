package com.drelang.smartlock.controller;

import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.UmsMemberLoginParam;
import com.drelang.smartlock.dto.UmsMemberRegisterParam;
import com.drelang.smartlock.pojo.entity.UmsMember;
import com.drelang.smartlock.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
  *  会员登录注册管理 Controller
  * Created by Drelang on 2019/01/10
  */
@Controller
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
//    @Autowired
//    public UmsMemberController( UmsMemberService umsMemberService) {
//        this.umsMemberService = umsMemberService;
//    }

    @PostMapping("/register")
    @ResponseBody
    // TODO: 验证为啥不起作用
    public Object register(@RequestBody @Validated UmsMemberRegisterParam umsMemberRegisterParam,
                                                                        BindingResult bindingResult) {
        UmsMember umsMember = umsMemberService.register(umsMemberRegisterParam);
        if(umsMember == null){
            new CommonResult().failed("注册失败");
        }

        return new CommonResult().success("注册成功", umsMember);
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody UmsMemberLoginParam umsMemberLoginParam, BindingResult bindingResult) {
        String token = umsMemberService.login(umsMemberLoginParam.getUsername(), umsMemberLoginParam.getPassword());
        if (token == null){
            return new CommonResult().failed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult().success(tokenMap);
    }
}
