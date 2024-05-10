package com.kosta.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Value("${upload.path}") 
	private String uploadPath;
	
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
	
	@GetMapping("/deleteBoard")
	public ResponseEntity<Integer> deleteBoard(@RequestParam("num") Integer num) {
		try {
			Integer boardNum = boardService.deleteBoard(num);
			return new ResponseEntity<Integer>(boardNum, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/boardWrite")
	public ResponseEntity<Integer> boardWrite(@RequestParam("subject") String subject,
											@RequestParam("content") String content, 
											@RequestParam("writer") String writer,
											@RequestParam("file") MultipartFile[] files) {
		try {
			Integer boardNum = boardService.boardWrite(subject, content, writer, files);
			return new ResponseEntity<Integer>(boardNum, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//@PathVariable : url의 /{num}를 받아옴 (이름 같아야 함)
	@GetMapping("/boardDetail/{num}/{id}")
	public ResponseEntity<Map<String, Object>> boardDetail(@PathVariable Integer num, 
														@PathVariable String id) {
		try {
			Map<String, Object> res = new HashMap<>();
			BoardDto boardDto = boardService.boardDetail(num);
			res.put("board", boardDto);
			Boolean isLike = boardService.isSelectBoardLike(id, num);
			res.put("like", isLike);
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/image/{num}")
	public void image(@PathVariable String num, HttpServletResponse response) {
		try {
			FileInputStream fis = new FileInputStream(new File(uploadPath, num));
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(fis, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
