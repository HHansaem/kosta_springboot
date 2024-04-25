package com.kosta.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

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
			return "redirect:/boardlist";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "게시글 작성 오류");
			return "error";
		}
	}
	
	@GetMapping("/boardlist")
	public ModelAndView boardList(@RequestParam(name="page", required = false, defaultValue = "1") Integer page,
								@RequestParam(name="type", required = false) String type,
								@RequestParam(name="word", required = false) String word) {
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = PageInfo.builder().curPage(page).build();
			List<BoardDto> boardList = boardService.boardListByPage(pageInfo, type, word);
			mav.addObject("boardList", boardList);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("type", type);
			mav.addObject("word", word);
			mav.setViewName("boardlist");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 목록 조회 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	
}
