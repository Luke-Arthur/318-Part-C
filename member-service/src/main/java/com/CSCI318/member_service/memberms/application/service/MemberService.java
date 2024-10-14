package com.CSCI318.member_service.memberms.application.service;


import com.CSCI318.member_service.memberms.infrastructure.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CSCI318.member_service.memberms.domain.model.Member;
import java.util.ArrayList;
import java.util.List;


@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Get member by ID
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    // create a member
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    // create multiple members
    public List<Member> createMembers(List<Member> members) {
        List<Member> savedMembers = new ArrayList<>();
        for (Member member : members) {
            if (member.getId() == null || !memberRepository.existsById(member.getId())) {
                savedMembers.add(createMember(member));
            }
        }
        return savedMembers;
    }

    // delete a member
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    // delete multiple members
    public void deleteMembers(List<Long> ids) {
        for (Long id : ids) {
            deleteMember(id);
        }
    }
}
