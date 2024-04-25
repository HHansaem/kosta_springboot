package com.kosta.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entity.BFile;
import com.kosta.board.entity.Board;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileRepository;
import com.kosta.board.util.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  //final로 선언 -> @Autowired 말고 해당 어노테이션 선언
public class BoardServiceImpl implements BoardService {

	@Value("${upload.path}")  //application.properties 에서 정의해준 경로 
	private String uploadPath;
	
	private final FileRepository fileRepository;
	private final BoardRepository boardRepository;
	
	@Override
	public void boardWrite(BoardDto boardDto, MultipartFile file) throws Exception {
		if(file != null && !file.isEmpty()) {
			BFile bFile = BFile.builder()
								.name(file.getOriginalFilename())
								.directory(uploadPath)
								.size(file.getSize())
								.contenttype(file.getContentType())
								.build();
			fileRepository.save(bFile);
			File upFile = new File(uploadPath, bFile.getName());
			file.transferTo(upFile);
			boardDto.setFileNum(bFile.getNum());
		}
		Board board = boardDto.toBoard();
		boardRepository.save(board);  //board table에 글 삽입
	}

	@Override
	public List<BoardDto> boardListByPage(PageInfo pageInfo, String type, String word) throws Exception {
		//PageRequest 정보를 객체에 담기
		PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage()-1, 10, Sort.by(Sort.Direction.DESC, "num"));
		Page<Board> pages = null;
		
		if(word == null || word.trim().equals("")) {  //목록 조회
			pages = boardRepository.findAll(pageRequest);
		} else {  //검색
			if(type.equals("subject")) {
				pages = boardRepository.findBySubjectContains(word, pageRequest);
			} else if(type.equals("content")) {
				pages = boardRepository.findByContentContains(word, pageRequest);
			} else if(type.equals("writer")) {
				pages = boardRepository.findByMember_Id(word, pageRequest);
			}
		}
		pageInfo.setAllPage(pages.getTotalPages());
		
		int startPage = (pageInfo.getCurPage()-1)/10*10+1;
		int endPage = Math.min(startPage+10-1, pageInfo.getAllPage());
		
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		List<BoardDto> boardDtoList = new ArrayList<>();
		for(Board board : pages.getContent()) {
			boardDtoList.add(board.toBoardDto());
		}
		
		return boardDtoList;
	}

}
