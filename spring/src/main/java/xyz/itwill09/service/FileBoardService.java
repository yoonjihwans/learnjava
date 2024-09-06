package xyz.itwill09.service;

import java.util.Map;

import xyz.itwill09.dto.FileBoard;

public interface FileBoardService {
	void addFileBoard(FileBoard fileBoard);
	void removeFileBoard(int num);
	FileBoard getFileBoard(int num);
	Map<String, Object> getFileBoardList(int pageNum, int pageSize);
}