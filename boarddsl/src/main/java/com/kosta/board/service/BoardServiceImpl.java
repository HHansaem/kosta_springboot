package com.kosta.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entity.BFile;
import com.kosta.board.entity.Board;
import com.kosta.board.entity.BoardLike;
import com.kosta.board.repository.BoardDslRepository;
import com.kosta.board.repository.BoardLikeRepository;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileRepository;
import com.kosta.board.repository.MemberRepository;
import com.kosta.board.util.PageInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardDslRepository boardDslRepository;
	private final BoardRepository boardRepository;
	private final BoardLikeRepository boardLikeRepository;
	private final FileRepository fileRepository;
	private final MemberRepository memberRepository;
	
	@Value("${upload.path}")  //application.properties 에서 정의해준 경로 
	private String uploadPath;

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
			File upFile = new File(uploadPath, bFile.getNum()+"");
			file.transferTo(upFile);
			boardDto.setFileNum(bFile.getNum());
		}
		Board board = boardDto.toBoard();
		boardRepository.save(board);
	}

	@Override
	public List<BoardDto> boardListByPage(PageInfo pageInfo, String type, String word) throws Exception {
		PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage()-1, 10);
		
		List<Board> boardList = null;
		Long allCount = 0L;
		if(word ==  null || word.trim().equals("")) {  //전체목록
			boardList = boardDslRepository.findBoardListByPaging(pageRequest);
			allCount = boardDslRepository.findBoardCount();
		} else {  //검색
			boardList = boardDslRepository.searchBoardListByPaging(pageRequest, type, word);
			allCount = boardDslRepository.searchBoardCount(type, word);
			
		}
		
		int allPage = (int)(Math.ceil(allCount.doubleValue()/pageRequest.getPageSize()));
		int startPage = (pageInfo.getCurPage()-1)/10*10+1;
		int endPage = Math.min(startPage+10-1, allPage);
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		return boardList.stream()
						.map(Board::toBoardDto)
						.collect(Collectors.toList());
	}

	@Override
	public BoardDto boardDetail(Integer num) throws Exception {
		Optional<Board> oboard = boardRepository.findById(num);
		if(oboard.isEmpty()) throw new Exception("글번호 오류");
		boardDslRepository.setViewCount(num, oboard.get().getViewCount()+1);
		return oboard.get().toBoardDto();
	}

	@Override
	public void boardModify(BoardDto boardDto, MultipartFile file) throws Exception {
		//1. 변경파일:O, 기존파일:O  boardDto.setNum(new file), 기존 파일 삭제
		//2. 변경파일:X, 기존파일:O  boardDto.setNum(before file)
		//3. 변경파일:O, 기존파일:X  boardDto.setNum(new file)
		//4. 변경파일:X, 기존파일:X  
		
		Board beforeBoard = boardRepository.findById(boardDto.getNum()).get();
		if(file != null && !file.isEmpty()) {  //1,3
			if(beforeBoard.getFileNum() != null) {  //1
				fileRepository.deleteById(beforeBoard.getFileNum());
				File beforeFile = new File(uploadPath, beforeBoard.getFileNum()+"");
				beforeFile.delete();
			}
			BFile bFile = BFile.builder()
								.name(file.getOriginalFilename())
								.directory(uploadPath)
								.size(file.getSize())
								.contenttype(file.getContentType())
								.build();
			fileRepository.save(bFile);  //file table에 파일정보 삽입
			File upFile = new File(uploadPath, bFile.getNum()+"");
			file.transferTo(upFile);  //file upload
			boardDto.setFileNum(bFile.getNum());
		}  else {  //2,4
			if(beforeBoard.getFileNum() != null) {  //2
				boardDto.setFileNum(beforeBoard.getFileNum());
			}
		}
		Board board = boardDto.toBoard();
		boardRepository.save(board);  //board table에 글 삽입
	}

	@Override
	public Boolean isSelectBoardLike(String memberId, Integer boardNum) throws Exception {
		BoardLike boardLike = boardDslRepository.findBoardLike(memberId, boardNum);
		return boardLike != null;
	}

	@Override
	public Boolean checkBoardLike(String memberId, Integer boardNum) throws Exception {
		BoardLike boardLike = boardDslRepository.findBoardLike(memberId, boardNum);
		if(boardLike == null) {
			boardLikeRepository.save(BoardLike.builder()
											.memberId(memberId)
											.boardNum(boardNum)
											.build());
			return true;
		} else {
			boardLikeRepository.deleteById(boardLike.getNum());
			return false;
		}
	}

}
