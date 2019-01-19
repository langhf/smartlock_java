package com.drelang.smartlock.repository;

import com.drelang.smartlock.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
     Member findByUsername(String username);
}
