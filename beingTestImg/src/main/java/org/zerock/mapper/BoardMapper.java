package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.FileVO;

public interface BoardMapper {
	
public void create(BoardVO board) throws Exception;
	
	public List<BoardVO> selectBoardList() throws Exception;
	
	 public BoardVO read(Integer num) throws Exception;
	 
	 public List<BoardVO> listPage(int page) throws Exception;

	  public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	  public int countPaging(Criteria cri) throws Exception;
	  
	   public void update(BoardVO vo) throws Exception;

	   public void delete(Integer num) throws Exception;
	   
	   public void fileInsert(FileVO file) throws Exception;

}
