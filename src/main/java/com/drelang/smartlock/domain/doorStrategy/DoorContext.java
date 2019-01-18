package com.drelang.smartlock.domain.doorStrategy;

public class DoorContext {
    private DoorSuper doorSuper;
    private String doorSerialNumber;

    public DoorContext(String doorType, String doorSerialNumber ) {
        switch (doorType) {
            case "lab":
                doorSuper = new DoorLab();
                break;

            case "floor":
                doorSuper = new DoorFloor();
                break;

             default:
                 doorSuper = null;
        }
        this.doorSerialNumber = doorSerialNumber;
    }

    public String getRoomSerialNumber() {
        return doorSuper.getRoomSerialNumber(doorSerialNumber);
    }
}
