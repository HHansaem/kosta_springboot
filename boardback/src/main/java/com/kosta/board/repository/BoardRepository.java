package com.kosta.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	//파라미터 받은 subject를 포함하는 거 select
	Page<Board> findBySubjectContains(String subject, PageRequest pageRequest);
	Page<Board> findByContentContains(String content, PageRequest pageRequest);
	Page<Board> findByMember_Id(String writer, PageRequest pageRequest);
}
