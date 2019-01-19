package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.MemberLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoginLogRepository extends JpaRepository<MemberLoginLog, Long> {

}
