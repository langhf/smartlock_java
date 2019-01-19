package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
  *  返回用户信息类
  * Created by Drelang on 2019/01/19
  */
@Data
public class MemberInfoResult {
    @ApiModelProperty(value = "用户id", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "用户名", example = "drelang", position = 2)
    private String username;

    @ApiModelProperty(value = "电话", example = "13156533607", position = 3)
    private String telephone;

    @ApiModelProperty(value = "状态，是否激活", example = "1", position = 4)
    private Integer status;

    @ApiModelProperty(value = "注册时间", example = "2019-1-12 14:00:00", position = 5)
    private Date createTime;

    @ApiModelProperty(value = "最后一次登录时间", example = "2019-1-12 15:00:00", position = 6)
    private Date lastLoginTime;
}
