package com.kosta.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.BFile;
import com.kosta.board.dto.Board;

@Mapper
@Repository
public interface BoardDao {
	List<Board> selectBoardList(Integer row) throws Exception;
	Integer selectBoardCount() throws Exception;
	Board selectBoard(Integer num) throws Exception;
	void insertBoard(Board board) throws Exception;
	void insertFile(BFile file) throws Exception;
	void updateBoard(Board board) throws Exception;
	void updateBrdViewCnt(Integer num) throws Exception;
	void deleteFile(Integer num) throws Exception;
	//검색에 따른 리스트
	List<Board> selectBoardSearchList(@Param("row") Integer row, @Param("type") String type, @Param("word") String word) throws Exception;
	Integer selectBoardSearchCount(@Param("type") String type, @Param("word") String word) throws Exception;
}
