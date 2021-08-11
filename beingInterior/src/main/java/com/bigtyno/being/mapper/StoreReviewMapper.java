package com.bigtyno.being.mapper;

import java.util.List;

import com.bigtyno.being.domain.Criteria;
import com.bigtyno.being.domain.StoreReviewVO;

public interface StoreReviewMapper {
	
	  public List<StoreReviewVO> list(Integer prodnum) throws Exception;

	  public void create(StoreReviewVO vo) throws Exception;

	  public void update(StoreReviewVO vo) throws Exception;

	  public void delete(Integer num) throws Exception;

	  public List<StoreReviewVO> listPage(Integer bno, Criteria cri) throws Exception;

	  public int count(Integer prodnum) throws Exception;

	  public int getProdnum(Integer num) throws Exception;
	  
	  public void updateGrade(Integer prodnum) throws Exception;
}
