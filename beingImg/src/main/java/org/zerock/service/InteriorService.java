package org.zerock.service;

import java.util.List;

import org.zerock.domain.InteriorVO;
import org.zerock.domain.Criteria;

public interface InteriorService {

	public void create(InteriorVO interior) throws Exception;

	public List<InteriorVO> selectInteriorList() throws Exception;

	public InteriorVO read(Integer num) throws Exception;

	public List<InteriorVO> listPage(int page) throws Exception;

	public List<InteriorVO> listCriteria(Criteria cri) throws Exception;

	public int listCountCriteria(Criteria cri) throws Exception;

	public void modify(InteriorVO interior) throws Exception;

	public void remove(Integer num) throws Exception;

}
