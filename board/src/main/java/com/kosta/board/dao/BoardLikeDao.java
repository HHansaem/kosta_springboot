package com.kosta.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardLikeDao {
	void insertBoardLike(@Param("memberId") String memberId, @Param("boardNum") Integer boardNum) throws Exception;
	void deleteBoardLike(@Param("memberId") String memberId, @Param("boardNum") Integer boardNum) throws Exception;
	Integer selectBoardLike(@Param("memberId") String memberId, @Param("boardNum") Integer boardNum) throws Exception;
}
