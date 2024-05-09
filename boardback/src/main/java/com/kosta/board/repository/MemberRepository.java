package com.kosta.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	Optional<Member> findByNickName(String nickName);  //이름은 반드시 findBy컬렴명 이어야 함
}
