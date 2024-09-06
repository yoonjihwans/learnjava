package xyz.itwill09.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.dto.FileBoard;
import xyz.itwill09.mapper.FileBoardMapper;

@Repository
@RequiredArgsConstructor
public class FileBoardDAOImpl implements FileBoardDAO {
	private final SqlSession sqlSession;
		
	@Override
	public int insertFileBoard(FileBoard board) {
		return sqlSession.getMapper(FileBoardMapper.class).insertFileBoard(board);
	}

	@Override
	public int deleteFileBoard(int num) {
		return sqlSession.getMapper(FileBoardMapper.class).deleteFileBoard(num);
	}

	@Override
	public FileBoard selectFileBoard(int num) {
		return sqlSession.getMapper(FileBoardMapper.class).selectFileBoard(num);
	}

	@Override
	public int selectFileBoardCount() {
		return sqlSession.getMapper(FileBoardMapper.class).selectFileBoardCount();
	}

	@Override
	public List<FileBoard> selectFileBoardList(Map<String, Object> map) {
		return sqlSession.getMapper(FileBoardMapper.class).selectFileBoardList(map);
	}
}