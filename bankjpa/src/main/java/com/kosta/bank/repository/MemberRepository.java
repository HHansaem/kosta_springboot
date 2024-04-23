package com.kosta.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bank.entity.Member;

//Member Entity의 CRUD 메서드 제공 <entity명, key column의 type>
public interface MemberRepository extends JpaRepository<Member, String> {

}
