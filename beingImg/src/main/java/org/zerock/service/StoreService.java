package org.zerock.service;

import java.util.List;

import org.zerock.domain.StoreVO;
import org.zerock.domain.Criteria;

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
