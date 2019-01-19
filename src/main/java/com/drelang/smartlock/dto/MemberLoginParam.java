package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
  *  用户登录参数
  * Created by Drelang on 2019/01/12
  */
@Getter
@Setter
public class MemberLoginParam {
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", example = "drelang", required = true, position = 1)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码", example = "hahahaha", required = true, position = 2)
    private String password;

}
