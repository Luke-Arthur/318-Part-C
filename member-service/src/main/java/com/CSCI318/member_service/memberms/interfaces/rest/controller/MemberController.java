package com.CSCI318.member_service.memberms.interfaces.rest.controller;


import com.CSCI318.member_service.memberms.domain.model.Member;
import com.CSCI318.member_service.memberms.application.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    // Get all members
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
    }

    // Get member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMemberById(id), HttpStatus.OK);
    }

    // Create a member
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.createMember(member), HttpStatus.CREATED);
    }

    // Create multiple members
    @PostMapping("/bulk")
    public ResponseEntity<List<Member>> createMembers(@RequestBody List<Member> members) {
        List<Member> savedMembers = memberService.createMembers(members);
        return new ResponseEntity<>(savedMembers, HttpStatus.CREATED);
    }

    // Delete a member
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete multiple members
    @DeleteMapping("/bulk")
    public ResponseEntity<Void> deleteMembers(@RequestBody List<Long> ids) {
        memberService.deleteMembers(ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
