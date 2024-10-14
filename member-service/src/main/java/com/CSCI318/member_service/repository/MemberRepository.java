package com.CSCI318.member_service.repository;
import com.CSCI318.member_service.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
