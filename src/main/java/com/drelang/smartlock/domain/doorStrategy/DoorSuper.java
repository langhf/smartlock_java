package com.drelang.smartlock.domain.doorStrategy;

public abstract class DoorSuper {
    /**
     * 根据门序列号获取房间序列号
     * @param doorSerialNumber 门序列号
     * @return 房间序列号
     */
    public abstract String getRoomSerialNumber(String doorSerialNumber);
}
