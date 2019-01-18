package com.drelang.smartlock.dto;

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
    private String serialNumber;

    @NotEmpty(message = "起始时间不能为空")
    private Date startTime;

    @NotEmpty(message = "终止时间不能为空")
    private Date endTime;

    @NotEmpty(message = "主题不能为空")
    private String theme;
}
