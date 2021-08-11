package com.bigtyno.being.mapper;

import java.util.HashMap;
import java.util.List;

import com.bigtyno.being.domain.BoardVO;
import com.bigtyno.being.domain.Criteria;
import com.bigtyno.being.domain.FileVO;

public interface BoardMapper {
	
public void create(BoardVO board) throws Exception;
	
	public List<BoardVO> selectBoardList() throws Exception;
	
	public BoardVO read(Integer num) throws Exception;
 
	public List<BoardVO> listPage(int page) throws Exception;

	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	public int countPaging(Criteria cri) throws Exception;
  
	public void update(BoardVO vo) throws Exception;

	public void delete(Integer num) throws Exception;
 
	public List<FileVO> selectBoardFileList(Integer num) throws Exception;
  
	public void deleteBoardFile(Integer num) throws Exception;
  
	public void insertBoardFile(FileVO fileVO) throws Exception;
  
	public void updateBoardFile(FileVO fileVO) throws Exception;
  
	public FileVO selectBoardFileList1(Integer num) throws Exception;
	
	
	  
	  
	  
}
