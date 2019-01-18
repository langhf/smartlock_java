package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {
    RoomInfo findBySerialNumber(String serialNumber);
}
