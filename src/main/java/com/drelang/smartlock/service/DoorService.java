package com.drelang.smartlock.service;

import com.drelang.smartlock.domain.DoorHelpOrder;
import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.DoorRequestHelpParam;

import java.util.List;

/**
  *  门禁服务
  * Created by Drelang on 2019/01/18
  */
public interface DoorService {

    /**
     * 根据门序列号来开门
     * @param serialNumber 序列号
     * @return 包含开门密码的 CommonResult
     */
    CommonResult openDoor(String serialNumber);

    /**
     * 根据门序列号来重置门密码
     * @param serialNumber  序列号
     * @return 包含开门密码的 CommonResult
     */
    CommonResult resetDoorPassword(String serialNumber);

    /**
     * help 申请
     * @param doorRequestHelpParam 一系列参数
     * @return CommonResult
     */
    CommonResult requestHelp(DoorRequestHelpParam doorRequestHelpParam);

    /**
     * 管理员处理用户的help申请
     * @param helpOrderId  help_order id
     * @param message 0：拒绝， 1：同意
     * @return
     */
    CommonResult helpHandle(Long helpOrderId, Integer message);

    /**
     * 获取当前用户的help申请记录
     * @return List<DoorHelpOrder>
     */
    List<DoorHelpOrder> getCurrentUserHelpOrders();

    /**
     * 获取特定管理员管理的help申请记录
     * @return
     */
    List<DoorHelpOrder> getHelpOrdersByAuditorId();
}
