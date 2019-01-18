package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.DoorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorInfoRepository extends JpaRepository<DoorInfo, Long> {
    DoorInfo getBySerialNumber(String serialNumber);
}
