package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.DoorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoorInfoRepository extends JpaRepository<DoorInfo, Long> {
    DoorInfo getBySerialNumber(String doorSerialNumber);

    /**
     * 根据房间序列号去查找房间拥有的门
     * @param roomSerialNumber  房间序列号
     * @return List<DoorInfo>
     */
    List<DoorInfo> getAllBySerialNumberLike(String roomSerialNumber);
}
