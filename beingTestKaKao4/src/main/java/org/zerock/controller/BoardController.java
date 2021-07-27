package org.zerock.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.FileUtil;
import org.zerock.domain.FileVO;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService boardService;
	
	 @RequestMapping(value = "/newArticleForm", method = RequestMethod.GET)
	  public void registerGET(BoardVO board, Model model) throws Exception {

	    logger.info("newArticleForm get ...........");
	  }

	 @RequestMapping(value = "/newArticleForm", method = RequestMethod.POST)
	  public String registPOST(BoardVO board, RedirectAttributes rttr) throws
	  Exception {
	    logger.info("newArticleForm post ...........");
	    logger.info(board.toString());		  

       FileUtil fs = new FileUtil();
       List<FileVO> filelist = null;
       
       	filelist = fs.saveAllFiles(board.getUploadfile());
       	logger.info(filelist.toArray()[0].toString());
       	
       boardService.create(board, filelist);
	  
       rttr.addFlashAttribute("msg", "SUCCESS");
	  
       logger.info(board.toString());
	  
	  return "redirect:/board/listArticle"; }
	  
	 
	 @RequestMapping(value = "/listArticle", method = RequestMethod.GET)
	  public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

	    logger.info(cri.toString());

	     model.addAttribute("listArticle", boardService.listCriteria(cri));
//	    model.addAttribute("listArticle", boardService.listPage(page));

	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);

	     pageMaker.setTotalCount(boardService.listCountCriteria(cri));
//	    pageMaker.setTotalCount(boardService.listSearchCount(cri));

	    model.addAttribute("pageMaker", pageMaker);
	  }
	
//	 @RequestMapping(value = "/readArticle", method = RequestMethod.GET)
//	  public void read(@RequestParam("num") int num, Model model) throws Exception {
//
//	    model.addAttribute(boardService.read(num));
//	  }
	 
	  @RequestMapping(value = "/readArticle", method = RequestMethod.GET)
	     public void read(@RequestParam("num") int num, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {

	       model.addAttribute(boardService.read(num));
	     }
	   
	     @RequestMapping(value = "/remove", method = {RequestMethod.POST ,RequestMethod.GET})
	     public String remove(@RequestParam("num") int num, RedirectAttributes rttr) throws Exception {

	        boardService.remove(num);

	       rttr.addFlashAttribute("msg", "SUCCESS");

	       return "redirect:/board/listArticle";
	     }

	     @RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	     public void modifyGET(int num, Model model) throws Exception {

	       model.addAttribute(boardService.read(num));
	     }

	     @RequestMapping(value = "/modifyForm", method = RequestMethod.POST)
	     public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

	       logger.info("mod post............");

	       boardService.modify(board);
	       rttr.addFlashAttribute("msg", "SUCCESS");

	       return "redirect:/board/listArticle";
	     } 
	
	   
}
