package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.UmsMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsMemberRepository extends JpaRepository<UmsMember, Long> {
     UmsMember findByUsername(String username);
}
