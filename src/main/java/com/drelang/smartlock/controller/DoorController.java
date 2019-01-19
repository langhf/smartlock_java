package com.drelang.smartlock.controller;

import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.DoorRequestOpenParam;
import com.drelang.smartlock.service.DoorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  *  开门控制类
  * Created by Drelang on 2019/01/18
  */
@RestController
@RequestMapping("door")
@Api(tags = "3. DoorController", description = "门禁相关")
public class DoorController {
    private DoorService doorService;

    public DoorController(DoorService doorService) {
        this.doorService = doorService;
    }

    @PostMapping("/quest")
    @ApiOperation(value = "请求开门", notes = "请求端需要发送门序列号")
    public CommonResult openDoor(@RequestBody DoorRequestOpenParam doorRequestOpenParam) {
        return doorService.openDoor(doorRequestOpenParam.getDoorSerialNumber());
    }
}
