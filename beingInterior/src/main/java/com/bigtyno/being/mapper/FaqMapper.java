package com.bigtyno.being.mapper;

import java.util.List;

import com.bigtyno.being.domain.Criteria;
import com.bigtyno.being.domain.FaqVO;

public interface FaqMapper {

	public void create(FaqVO faq) throws Exception;
	
	public List<FaqVO> listPage(int page) throws Exception;

	public List<FaqVO> listCriteria(Criteria cri) throws Exception;
	
	public int countPaging(Criteria cri) throws Exception;

	public List<FaqVO> selectFaqList() throws Exception;
	
	public FaqVO read(Integer num) throws Exception;
	
	public void delete(Integer num) throws Exception;
	
	public void update(FaqVO faq) throws Exception;
}
