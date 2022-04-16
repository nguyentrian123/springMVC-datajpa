package com.laptrinhjavaweb.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;

@Controller(value = "newControllerOfWeb")
public class NewController {
	
	@Autowired
	private INewService newService;
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(value = "/chi-tiet/bai-viet", method = RequestMethod.GET)
	public ModelAndView newDetail(@RequestParam("id") int id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/new/newDetail");
		NewDTO model = new NewDTO();
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setListResult(categoryService.findAlls());
		model = newService.findById(id);
		mav.addObject("models", model);
		mav.addObject("model",categoryDTO);
		return mav;
	}
	

}
