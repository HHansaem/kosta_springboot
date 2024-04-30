package com.kosta.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.bank.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

}
