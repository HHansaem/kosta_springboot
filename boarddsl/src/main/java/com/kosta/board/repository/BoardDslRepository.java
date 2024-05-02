package com.kosta.board.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.kosta.board.entity.Board;
import com.kosta.board.entity.BoardLike;
import com.kosta.board.entity.QBoard;
import com.kosta.board.entity.QBoardLike;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BoardDslRepository {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	public List<Board> findBoardListByPaging(PageRequest pageRequest) throws Exception {
		QBoard board = QBoard.board;
		return jpaQueryFactory.selectFrom(board)
							.orderBy(board.num.desc())
							.offset(pageRequest.getOffset())
							.limit(pageRequest.getPageSize())
							.fetch();
	}
	
	public Long findBoardCount() throws Exception {
		QBoard board = QBoard.board;
		return jpaQueryFactory.select(board.count())
							.from(board)
							.fetchOne();
	}
	
	public List<Board> searchBoardListByPaging(PageRequest pageRequest, String type, String word) throws Exception {
		QBoard board = QBoard.board;
		if(type.equals("subject")) {
			return jpaQueryFactory.selectFrom(board)
								.where(board.subject.contains(word))
								.orderBy(board.num.desc())
								.offset(pageRequest.getOffset())
								.limit(pageRequest.getPageSize())
								.fetch();
		} else if(type.equals("content")) {
			return jpaQueryFactory.selectFrom(board)
								.where(board.content.contains(word))
								.orderBy(board.num.desc())
								.offset(pageRequest.getOffset())
								.limit(pageRequest.getPageSize())
								.fetch();
		} else if(type.equals("writer")) {
			return jpaQueryFactory.selectFrom(board)
								.where(board.writer.eq(word))
								.orderBy(board.num.desc())
								.offset(pageRequest.getOffset())
								.limit(pageRequest.getPageSize())
								.fetch();
		} else return null;
	}
	
	public Long searchBoardCount(String type, String word) throws Exception {
		QBoard board = QBoard.board;
		
		if(type.equals("subject")) {
			return jpaQueryFactory.select(board.count())
								.from(board)
								.where(board.subject.contains(word))
								.fetchOne();
		} else if(type.equals("content")) {
			return jpaQueryFactory.select(board.count())
								.from(board)
								.where(board.content.contains(word))
								.fetchOne();
		} else if(type.equals("writer")) {
			return jpaQueryFactory.select(board.count())
								.from(board)
								.where(board.writer.eq(word))
								.fetchOne();
		} else return null;
	}
	
	@Transactional
	public void setViewCount(Integer boardNum, Integer viewCount) {
		QBoard board = QBoard.board;
		jpaQueryFactory.update(board)
					.set(board.viewCount, viewCount)
					.where(board.num.eq(boardNum))
					.execute();
	}
	
	public BoardLike findBoardLike(String id, Integer num) {
		QBoardLike boardLike = QBoardLike.boardLike;
		return jpaQueryFactory.select(boardLike)
							.where(boardLike.memberId.eq(id).and(boardLike.boardNum.eq(num)))
							.fetchOne();
	}
	
}
