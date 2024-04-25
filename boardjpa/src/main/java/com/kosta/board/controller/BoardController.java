package com.kosta.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardwrite")
	public String boardWrite() {
		return "writeform";
	}
	
	@PostMapping("/boardwrite")
	public String boardWrite(BoardDto boardDto, MultipartFile file, Model model) {
		try {
			boardService.boardWrite(boardDto, file);
			return "boardlist";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "게시글 작성 오류");
			return "error";
		}
	}
}
