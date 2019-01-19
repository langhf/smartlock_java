package com.drelang.smartlock.controller;

import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.MemberInfoResult;
import com.drelang.smartlock.dto.MemberLoginParam;
import com.drelang.smartlock.dto.MemberRegisterParam;
import com.drelang.smartlock.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
  *  会员登录注册管理 Controller
  * Created by Drelang on 2019/01/10
  */
@RestController
@RequestMapping("/sso")
@Api(tags = "1. MemberController",description = "用户相关")
public class MemberController {

    @Autowired
    private MemberService umsMemberService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户名要唯一，不能在数据库中注册过", position = 1)
    // TODO: 验证为啥不起作用
    public CommonResult register(@Valid @RequestBody MemberRegisterParam umsMemberRegisterParam, BindingResult bindingResult) {
        MemberInfoResult memberInfoResult = umsMemberService.register(umsMemberRegisterParam);
        if(memberInfoResult == null){
           return new CommonResult().failed("注册失败");
        }
        return new CommonResult().success("注册成功", memberInfoResult);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "使用用户名和密码来登录", position = 2)
    public CommonResult login(@RequestBody MemberLoginParam umsMemberLoginParam, BindingResult bindingResult) {
        String token = umsMemberService.login(umsMemberLoginParam.getUsername(), umsMemberLoginParam.getPassword());
        if (token == null){
            return new CommonResult().failed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return new CommonResult().success(tokenMap);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "返回特定用户的信息", notes = "根据用户id返回用户信息", position = 3)
    public MemberInfoResult getUser(@PathVariable Long id) {
        return umsMemberService.getById(id);
    }

    @GetMapping("/user")
    @ApiOperation(value = "返回当前用户的信息", notes = "返回当前登录用户的信息", position = 4)
    public MemberInfoResult getCurrentUser() {
        return umsMemberService.getCurrentMember();
    }
}
