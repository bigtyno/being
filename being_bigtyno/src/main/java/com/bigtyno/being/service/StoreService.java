package com.bigtyno.being.service;

import java.util.List;

import com.bigtyno.being.common.Criteria;
import com.bigtyno.being.domain.StoreVO;

public interface StoreService {

  public void create(StoreVO store) throws Exception;

  public StoreVO read(Integer prodnum) throws Exception;

  public void modify(StoreVO store) throws Exception;

  public void remove(Integer prodnum) throws Exception;

  public List<StoreVO> listStorePage(int page) throws Exception;

  public List<StoreVO> listStoreCriteria(Criteria cri) throws Exception;

  public int listStoreCountCriteria(Criteria cri) throws Exception;

//  public List<StoreVO> listSearchCriteria(SearchStoreCriteria cri) 
//      throws Exception;
//
//  public int listSearchCount(SearchStoreCriteria cri) throws Exception;
//  
//  
//  public List<String> getAttach(Integer prodnum)throws Exception;
  

}
