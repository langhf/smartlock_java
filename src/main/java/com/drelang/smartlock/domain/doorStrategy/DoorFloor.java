package com.drelang.smartlock.domain.doorStrategy;

public class DoorFloor extends DoorSuper {
    @Override
    public String getRoomSerialNumber(String doorSerialNumber) {
        return doorSerialNumber.substring(0, 3);
    }
}
