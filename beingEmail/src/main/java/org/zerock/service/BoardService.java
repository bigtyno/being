package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.FileVO;

public interface BoardService {
	
	public void create(BoardVO param, List<FileVO> filelist) throws Exception;
	
	public List<BoardVO> selectBoardList() throws Exception;
	
	 public BoardVO read(Integer num) throws Exception;
	 
	 public List<BoardVO> listPage(int page) throws Exception;

	 public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	 public int listCountCriteria(Criteria cri) throws Exception;
	 
	 public void modify(BoardVO board, List<FileVO> filelist) throws Exception;

	 public void remove(Integer num) throws Exception;
	 
	 public List<FileVO> selectBoardFileList(Integer num) throws Exception;

	 //public FileVO selectBoardFileList1(Integer num) throws Exception;
}
