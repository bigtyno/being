package com.bigtyno.being.mapper;

import java.util.ArrayList;
import java.util.List;

import com.bigtyno.being.common.Criteria;
import com.bigtyno.being.domain.BoardVO;
import com.bigtyno.being.domain.FileVO;
import com.bigtyno.being.domain.InteriorFileVO;
import com.bigtyno.being.domain.InteriorVO;

public interface InteriorMapper {

	public void create(InteriorVO interior) throws Exception;

	public List<InteriorVO> selectInteriorList() throws Exception;

	public InteriorVO read(Integer num) throws Exception;

	public List<InteriorVO> listPage(int page) throws Exception;

	public List<InteriorVO> listCriteria(Criteria cri) throws Exception;

	public int countPaging(Criteria cri) throws Exception;

	public void update(InteriorVO vo) throws Exception;

	public void delete(Integer num) throws Exception;
	
	public void insertInteriorFile(InteriorFileVO interiorfileVO) throws Exception;
	
	public List<InteriorFileVO> selectInteriorFileList(Integer num) throws Exception;
	
	public void deleteInteriorFile(Integer num) throws Exception;
	
	public InteriorFileVO selectInteriorFileList1(Integer num) throws Exception;
	
	 public ArrayList<InteriorVO> listQuery(String query, String content) throws Exception;
}
