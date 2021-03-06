package org.zerock.controller;

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
import org.zerock.domain.InteriorVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.InteriorService;

@Controller
@RequestMapping("/interior/*")
public class InteriorController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private InteriorService interiorService;
	
	 @RequestMapping(value = "/newInteriorForm", method = RequestMethod.GET)
	  public void registerGET(InteriorVO interior, Model model) throws Exception {

	    logger.info("newInteriorForm get ...........");
	  }

	  @RequestMapping(value = "/newInteriorForm", method = RequestMethod.POST)
	  public String registPOST(InteriorVO interior, RedirectAttributes rttr) throws Exception {

	    logger.info("newArticleForm post ...........");
	    logger.info(interior.toString());

	    interiorService.create(interior);

	    rttr.addFlashAttribute("msg", "SUCCESS");
	    
	    logger.info(interior.toString());
	    
	    return "redirect:/interior/listInterior";
	  }
	  
	  @RequestMapping(value = "/listInterior", method = RequestMethod.GET)
	  public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

	    logger.info(cri.toString());

	     model.addAttribute("listInterior", interiorService.listCriteria(cri));

	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);

	     pageMaker.setTotalCount(interiorService.listCountCriteria(cri));

	    model.addAttribute("pageMaker", pageMaker);
	  }
	 
	  @RequestMapping(value = "/readInterior", method = RequestMethod.GET)
	     public void read(@RequestParam("num") int num, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {

	       model.addAttribute(interiorService.read(num));
	     }
	   
	     @RequestMapping(value = "/remove", method = {RequestMethod.POST ,RequestMethod.GET})
	     public String remove(@RequestParam("num") int num, RedirectAttributes rttr) throws Exception {

	        interiorService.remove(num);

	       rttr.addFlashAttribute("msg", "SUCCESS");

	       return "redirect:/interior/listInterior";
	     }

	     @RequestMapping(value = "/modifyInteriorForm", method = RequestMethod.GET)
	     public void modifyGET(int num, Model model) throws Exception {

	       model.addAttribute(interiorService.read(num));
	     }

	     @RequestMapping(value = "/modifyInteriorForm", method = RequestMethod.POST)
	     public String modifyPOST(InteriorVO interior, RedirectAttributes rttr) throws Exception {

	       logger.info("mod post............");

	       interiorService.modify(interior);
	       rttr.addFlashAttribute("msg", "SUCCESS");

	       return "redirect:/interior/listInterior";
	     } 

}
