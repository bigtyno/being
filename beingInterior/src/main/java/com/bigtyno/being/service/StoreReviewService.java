package com.bigtyno.being.service;

import java.util.List;

import com.bigtyno.being.domain.Criteria;
import com.bigtyno.being.domain.StoreReviewVO;

public interface StoreReviewService {

  public void addReview(StoreReviewVO vo) throws Exception;

  public List<StoreReviewVO> listReview(Integer prodnum) throws Exception;

  public void modifyReview(StoreReviewVO vo) throws Exception;

  public void removeReview(Integer num) throws Exception;

  public List<StoreReviewVO> listReviewPage(Integer prodnum, Criteria cri) 
      throws Exception;

  public int count(Integer bno) throws Exception;
  
  public void updateGrade(Integer prodnum) throws Exception;
}
