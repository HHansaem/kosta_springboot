package com.kosta.board.controller;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Value("${upload.path}") 
	private String uploadPath;
	
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
	
	// jsp에서 링크 "boarddetail/${board.num}" 이렇게 줌
	@GetMapping("/boarddetail/{num}")
	public ModelAndView boardDetail(@PathVariable Integer num) {  //위에 적힌 path의 {num}을 가져오겠다 (num 이름 똑같아야 함)
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("board", boardService.boardDetail(num));
			mav.setViewName("boarddetail");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "글 상세조회 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/image/{num}")
	public void imageView(@PathVariable Integer num, HttpServletResponse response) {
		try {
			FileInputStream fis = new FileInputStream(uploadPath+num);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/boardmodify/{num}")
	public ModelAndView boardModify(@PathVariable Integer num) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("board", boardService.boardDetail(num));
			mav.setViewName("modifyform");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 수정 폼 오류");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@PostMapping("/boardmodify")
	public ModelAndView boardModify(@ModelAttribute BoardDto boardDto, 
								@RequestPart(value = "file", required = false) MultipartFile file) {
		ModelAndView mav = new ModelAndView();
		try {
			boardService.boardModify(boardDto, file);
			mav.setViewName("redirect:/boarddetail/" + boardDto.getNum());
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 수정 오류");
			mav.setViewName("error");
		}
		return mav;
	}
//	
//	@ResponseBody
//	@PostMapping("/boardlike")
//	public String boardLike(@RequestParam("like") String like) {
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
}
