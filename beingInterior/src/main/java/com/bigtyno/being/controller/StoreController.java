package com.bigtyno.being.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigtyno.being.domain.Criteria;
import com.bigtyno.being.domain.PageMaker;
import com.bigtyno.being.domain.SearchCriteria;
import com.bigtyno.being.domain.StoreVO;
import com.bigtyno.being.service.StoreService;

@Controller
@RequestMapping("/store/*")
public class StoreController {

  private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

  @Inject
  private StoreService storeService;

  @RequestMapping(value = "/listStore", method = RequestMethod.GET)
  public void listStorePage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

    logger.info(cri.toString());

     model.addAttribute("listStore", storeService.listStoreCriteria(cri));
//    model.addAttribute("list", service.listSearchCriteria(cri));

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);

     pageMaker.setTotalCount(storeService.listStoreCountCriteria(cri));
//    pageMaker.setTotalCount(service.listSearchCount(cri));

    model.addAttribute("pageMaker", pageMaker);
  }

  @RequestMapping(value = "/readStore", method = RequestMethod.GET)
  public void read(@RequestParam("prodnum") int prodnum, @ModelAttribute("cri") Criteria cri, Model model)
      throws Exception {

    model.addAttribute(storeService.read(prodnum));
  }

  @RequestMapping(value = "/removePage", method = {RequestMethod.POST, RequestMethod.GET})
  public String remove(@RequestParam("prodnum") int prodnum, RedirectAttributes rttr) throws Exception {

	  storeService.remove(prodnum);

    rttr.addFlashAttribute("msg", "SUCCESS");

    return "redirect:/store/listStore";
  }

  @RequestMapping(value = "/storeModifyForm", method = RequestMethod.GET)
  public void modifyPagingGET(int prodnum, Model model) throws Exception {

    model.addAttribute(storeService.read(prodnum));
  }

  @RequestMapping(value = "/storeModifyForm", method = RequestMethod.POST)
  public String modifyPagingPOST(StoreVO store, SearchCriteria cri, RedirectAttributes rttr) throws Exception {

    logger.info(cri.toString());
    storeService.modify(store);

//    rttr.addAttribute("page", cri.getPage());
//    rttr.addAttribute("perPageNum", cri.getPerPageNum());
//    rttr.addAttribute("searchType", cri.getSearchType());
//    rttr.addAttribute("keyword", cri.getKeyword());

    rttr.addFlashAttribute("msg", "SUCCESS");

    logger.info(rttr.toString());

    return "redirect:/store/readStore?prodnum="+store.getProdnum();
  }

  @RequestMapping(value = "/newStoreForm", method = RequestMethod.GET)
  public void registGET() throws Exception {

    logger.info("regist get ...........");
  }

  @RequestMapping(value = "/newStoreForm", method = RequestMethod.POST)
  public String registPOST(StoreVO store, RedirectAttributes rttr) throws Exception {

    logger.info("regist post ...........");
    logger.info(store.toString());

    storeService.create(store);

    rttr.addFlashAttribute("msg", "SUCCESS");

    return "redirect:/store/readStore?prodnum="+store.getProdnum();
  }
  
  
//  @RequestMapping("/getAttach/{prodnum}")
//  @ResponseBody
//  public List<String> getAttach(@PathVariable("prodnum")Integer prodnum)throws Exception{
//    
//    return service.getAttach(prodnum);
//  }  

  // @RequestMapping(value = "/list", method = RequestMethod.GET)
  // public void listPage(@ModelAttribute("cri") SearchCriteria cri,
  // Model model) throws Exception {
  //
  // logger.info(cri.toString());
  //
  // model.addAttribute("list", service.listCriteria(cri));
  //
  // PageMaker pageMaker = new PageMaker();
  // pageMaker.setCri(cri);
  //
  // pageMaker.setTotalCount(service.listCountCriteria(cri));
  //
  // model.addAttribute("pageMaker", pageMaker);
  // }
}
