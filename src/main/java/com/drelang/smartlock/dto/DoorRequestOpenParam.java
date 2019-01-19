package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
  *  请求开门参数
  * Created by Drelang on 2019/01/19
  */
@Getter
@Setter
public class DoorRequestOpenParam {
    @ApiModelProperty(value = "门编号", example = "Q-408-1", required = true)
    private String doorSerialNumber;
}
