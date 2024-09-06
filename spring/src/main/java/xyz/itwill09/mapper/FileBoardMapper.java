package xyz.itwill09.mapper;

import java.util.List;
import java.util.Map;

import xyz.itwill09.dto.FileBoard;

public interface FileBoardMapper {
	int insertFileBoard(FileBoard board);
	int deleteFileBoard(int num);
	FileBoard selectFileBoard(int num);
	int selectFileBoardCount();
	List<FileBoard> selectFileBoardList(Map<String, Object> map);
}