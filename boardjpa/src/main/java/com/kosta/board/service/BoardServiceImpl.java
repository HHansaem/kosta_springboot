package com.kosta.board.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entity.BFile;
import com.kosta.board.entity.Board;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileRepository;

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

}
