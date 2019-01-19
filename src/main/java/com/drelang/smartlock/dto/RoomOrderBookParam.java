package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
  *  预定房间的 http json 参数
  * Created by Drelang on 2019/01/17
  */
@Getter
@Setter
public class RoomOrderBookParam {
    @NotEmpty(message = "房间编号不能为空")
    @ApiModelProperty(value = "房间的序列号", example = "Q-408", required = true, position = 0)
    private String serialNumber;

    // TODO: 无法添加数字 example
    @NotEmpty(message = "起始时间不能为空")
    @ApiModelProperty(value = "预定的起始时间", example = "2019-1-12 14:00:00", dataType = "number", required = true, position = 1)
    private Date startTime;

    @NotEmpty(message = "终止时间不能为空")
    @ApiModelProperty(value = "预定的终止时间", example = "2020-1-12 14:00:00", dataType = "number", required = true, position = 2)
    private Date endTime;

    @NotEmpty(message = "主题不能为空")
    @ApiModelProperty(value = "预定房间的主题", example = "需要在该房间学习", required = true, position = 3)
    private String theme;
}
