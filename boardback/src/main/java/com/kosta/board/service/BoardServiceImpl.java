package com.kosta.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entity.BFile;
import com.kosta.board.entity.Board;
import com.kosta.board.entity.BoardLike;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.BoardLikeRepository;
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
	private final BoardLikeRepository boardLikeRepository;
	
	@Override
	public Integer boardWrite(String subject, String content, String writer, MultipartFile[] files) throws Exception {
		String fileNums = "";
		for(MultipartFile file : files) {
			if(file != null && !file.isEmpty()) {
				BFile bFile = BFile.builder()
						.name(file.getOriginalFilename())
						.directory(uploadPath)
						.size(file.getSize())
						.contenttype(file.getContentType())
						.build();
				fileRepository.save(bFile);  //file table에 파일 정보 삽입
				File upFile = new File(uploadPath, bFile.getNum()+"");
				file.transferTo(upFile);  //file upload
				if(fileNums.equals("")) {
					fileNums = bFile.getNum() + "";
				} else {
					fileNums += "," + bFile.getNum();
				}
			}
		}
		Board board = Board.builder()
							.subject(subject)
							.content(content)
							.member(Member.builder().id(writer).build())
							.fileNums(fileNums)
							.build();
		boardRepository.save(board);  //board table에 글 삽입
		return board.getNum();
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

	@Override
	public BoardDto boardDetail(Integer num) throws Exception {
		Optional<Board> oboard = boardRepository.findById(num);
		if(oboard.isEmpty()) throw new Exception("글번호 오류");
		Board board = oboard.get();
		board.setViewCount(board.getViewCount()+1);
		boardRepository.save(board);
		return board.toBoardDto();
	}

//	@Override
//	public void boardModify(BoardDto boardDto, MultipartFile file) throws Exception {
//		//파일 수정의 경우
//		//1. 기존파일이 있으면서 파일을 변경할 경우
//		//2. 기존파일이 없으면서 파일을 추가하는 경우
//		//3. 기존파일이 있는데 변경하지 않는 경우
//		
//		BoardDto beforeBoardDto = boardDetail(boardDto.getNum());
//		
//		if(file != null && !file.isEmpty()) {  //변경할 파일이 있는 경우
//			//새 파일 업로드 & 새 파일정보 삽입
//			BFile bFile = BFile.builder()
//								.name(file.getOriginalFilename())
//								.directory(uploadPath)
//								.size(file.getSize())
//								.contenttype(file.getContentType())
//								.build();
//			fileRepository.save(bFile);
//			File upFile = new File(uploadPath, bFile.getNum()+"");
//			file.transferTo(upFile);
//			boardDto.setFileNum(bFile.getNum());
//		} else {  //파일을 변경하지 않는 경우 (기존 file정보)
//			boardDto.setFileNum(beforeBoardDto.getFileNum());
//		}
//		
//		boardRepository.save(boardDto.toBoard());
//		
//		//1의 경우에만 삭제
//		//파일을 수정하고 && 기존 업로드된 파일이 있을 경우 기존 업로드한 파일을 테이블에서 데이터 삭제하고 파일 삭제
//		if(file != null && !file.isEmpty() &&  //새로운 파일로 변경하는 조건 
//					beforeBoardDto.getFileNum() != null) {  //기존에 파일이 있는 조건
//			fileRepository.deleteById(beforeBoardDto.getFileNum());
//			File beforeFile = new File(uploadPath, beforeBoardDto.getFileNum()+"");
//			beforeFile.delete();
//		}
//	}

	@Override
	public Boolean checkBoardLike(String memberId, Integer boardNum) throws Exception {
		Optional<BoardLike> oboardLike = boardLikeRepository.findByMember_IdAndBoard_Num(memberId, boardNum);
		if(oboardLike.isPresent()) {  //기존에 좋아요를 선택했을 경우, 좋아요 제거
			boardLikeRepository.deleteById(oboardLike.get().getNum());
			return false;
		} else {  //기존에 좋아요를 선택하지 않았을 경우, 좋아요 삽입
			boardLikeRepository.save(
									BoardLike.builder()
											 .member(Member.builder().id(memberId).build())
											 .board(Board.builder().num(boardNum).build())
											 .build()
									);
			return true;
		}
	}

	@Override
	public Boolean isSelectBoardLike(String memberId, Integer boardNum) throws Exception {
		Optional<BoardLike> oboardLike = boardLikeRepository.findByMember_IdAndBoard_Num(memberId, boardNum);
		return oboardLike.isPresent();
	}

	@Override
	public Integer deleteBoard(Integer num) throws Exception {
		Optional<Board> oboard = boardRepository.findById(num);
		if(oboard.isEmpty()) throw new Exception("글번호 오류");
		boardRepository.deleteById(num);
		return num;
	}

}
