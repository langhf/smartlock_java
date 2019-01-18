package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.DoorOpenLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorOpenLogRepository extends JpaRepository<DoorOpenLog, Long> {
}
