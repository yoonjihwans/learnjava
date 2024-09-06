package xyz.itwill09.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.dao.FileBoardDAO;
import xyz.itwill09.dto.FileBoard;
import xyz.itwill09.util.Pager;

@Service
@RequiredArgsConstructor
public class FileBoardServiceImpl implements FileBoardService {
	private final FileBoardDAO fileBoardDAO;	

	@Transactional
	@Override
	public void addFileBoard(FileBoard fileBoard) {
		fileBoardDAO.insertFileBoard(fileBoard);
	}

	@Transactional
	@Override
	public void removeFileBoard(int num) {
		if(fileBoardDAO.selectFileBoard(num) == null) {
			throw new RuntimeException("삭제하고자 하는 게시글을 찾을 수 없습니다.");
		}
		
		fileBoardDAO.deleteFileBoard(num);
	}

	@Override
	public FileBoard getFileBoard(int num) {
		FileBoard fileBoard=fileBoardDAO.selectFileBoard(num);
		
		if(fileBoard == null) {
			throw new RuntimeException("게시글을 찾을 수 없습니다.");
		}
		
		return fileBoard;
	}

	//메소드의 매개변수로 요청 페이지 번호와 하나에 페이지에 출력될 게시글의 갯수를 전달받아 
	//FILE_BOARD 테이블에 저장된 행에서 페이지 번호에 해당하는 게시글 목록을 검색해 게시글 
	//목록과 페이징 처리 관련 정보를 Map 객체의 엔트리로 추가하여 반환하는 메소드
	@Override
	public Map<String, Object> getFileBoardList(int pageNum, int pageSize) {
		//FILE_BOARD 테이블에 저장된 모든 행의 갯수를 반환받아 저장
		int totalSize=fileBoardDAO.selectFileBoardCount();
		
		//하나의 블럭에 출력될 페이지 번호의 갯수를 임의의 값으로 저장
		int blockSize=5;
		
		Pager pager=new Pager(pageNum, pageSize, totalSize, blockSize);
		
		//FileBoardDAO 객체의 selectFileBoardList() 메소드의 매개변수에 전달될 Map 객체 생성
		// => Map 객체에는 시작 행번호와 종료 행번호를 엔트리의 맵값으로 저장
		Map<String, Object> pageMap=new HashMap<String, Object>();
		pageMap.put("startRow", pager.getStartRow());
		pageMap.put("endRow", pager.getEndRow());
		List<FileBoard> fileBoardList=fileBoardDAO.selectFileBoardList(pageMap);
		
		//요청 처리 메소드에게 반환될 처리 결과값을 저장하기 위한 Map 객체 생성
		// => 요청 처리 메소드는 반환받은 Map 객체를 Request Scope 속성값으로 저장해 뷰에게 제공
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("pager", pager);
		resultMap.put("fileBoardList", fileBoardList);
		
		return resultMap;
	}

}














