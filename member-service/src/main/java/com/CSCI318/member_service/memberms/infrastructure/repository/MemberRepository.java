package com.CSCI318.member_service.memberms.infrastructure.repository;
import com.CSCI318.member_service.memberms.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
