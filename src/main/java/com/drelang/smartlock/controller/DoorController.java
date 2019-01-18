package com.drelang.smartlock.controller;

import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.service.DoorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("door")
public class DoorController {
    private DoorService doorService;

    public DoorController(DoorService doorService) {
        this.doorService = doorService;
    }

    @PostMapping("/quest")
    @ResponseBody
    public CommonResult openDoor(@RequestBody Map<String, String> requestBody) {
        return doorService.openDoor(requestBody.get("doorSerialNumber"));
    }
}
