package com.drelang.smartlock.domain.doorStrategy;

public class DoorLab extends DoorSuper {
    @Override
    public String getRoomSerialNumber(String doorSerialNumber) {
        return doorSerialNumber.substring(0, 5);
    }
}
