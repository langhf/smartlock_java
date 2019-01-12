package com.drelang.smartlock.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
  *  用户登录参数
  * Created by Drelang on 2019/01/12
  */
@Getter
@Setter
public class UmsMemberLoginParam {
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

}
