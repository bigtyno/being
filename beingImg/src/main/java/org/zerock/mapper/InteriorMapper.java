package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.InteriorVO;

public interface InteriorMapper {

	public void create(InteriorVO interior) throws Exception;

	public List<InteriorVO> selectInteriorList() throws Exception;

	public InteriorVO read(Integer num) throws Exception;

	public List<InteriorVO> listPage(int page) throws Exception;

	public List<InteriorVO> listCriteria(Criteria cri) throws Exception;

	public int countPaging(Criteria cri) throws Exception;

	public void update(InteriorVO vo) throws Exception;

	public void delete(Integer num) throws Exception;

}
