package com.kosta.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList")
	public ResponseEntity<Map<String, Object>> boardList(@RequestParam(name="page", required = false, defaultValue = "1") Integer page,
								@RequestParam(name="type", required = false) String type,
								@RequestParam(name="word", required = false) String word) {
		Map<String, Object> res = new HashMap<>();
		try {
			PageInfo pageInfo = PageInfo.builder().curPage(page).build();
			List<BoardDto> boardList = boardService.boardListByPage(pageInfo, type, word);
			res.put("boardList", boardList);
			res.put("pageInfo", pageInfo);
			return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
