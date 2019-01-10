package com.drelang.smartlock.controller;

import com.drelang.smartlock.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
  *  会员登录注册管理 Controller
  * Created by Drelang on 2019/01/10
  */
@Controller
@RequestMapping("/sso")
public class UmsMemberController {

    private UmsMemberService umsMemberService;

    @Autowired
    public UmsMemberController( UmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(@RequestParam String username,
                                            @RequestParam String password,
                                            @RequestParam String telephone) {
        return umsMemberService.register(username, password, telephone);
    }
}
