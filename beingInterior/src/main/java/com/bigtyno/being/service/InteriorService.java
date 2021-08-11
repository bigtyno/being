package com.bigtyno.being.service;

import java.util.List;

import com.bigtyno.being.domain.Criteria;
import com.bigtyno.being.domain.InteriorFileVO;
import com.bigtyno.being.domain.InteriorVO;

public interface InteriorService {

	public void create(InteriorVO interior, List<InteriorFileVO> filelist) throws Exception;

	public List<InteriorVO> selectInteriorList() throws Exception;

	public InteriorVO read(Integer num) throws Exception;

	public List<InteriorVO> listPage(int page) throws Exception;

	public List<InteriorVO> listCriteria(Criteria cri) throws Exception;

	public int listCountCriteria(Criteria cri) throws Exception;

	public void modify(InteriorVO interior) throws Exception;

	public void remove(Integer num) throws Exception;
	
	public List<InteriorFileVO> selectInteriorFileList(Integer num) throws Exception;
	
	//public InteriorFileVO selectInteriorFileList1(Integer num) throws Exception;
}
