package com.kosta.board.service;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;


public interface BoardService {
	void boardWrite(BoardDto boardDto, MultipartFile file) throws Exception;
}
