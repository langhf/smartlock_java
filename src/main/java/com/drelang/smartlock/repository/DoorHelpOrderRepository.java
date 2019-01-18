package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.DoorHelpOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorHelpOrderRepository extends JpaRepository<DoorHelpOrder, Long> {
}
