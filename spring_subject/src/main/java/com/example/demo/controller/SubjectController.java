package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.service.SubjectService;

/**
 * 科目情報 Controller
 */
@Controller
public class SubjectController {

	/**
	 * 科目情報 Service
	 */
	@Autowired
	SubjectService subjectService;

	/**
	 * 科目情報一覧画面を表示
	 * @param  model Model
	 * @return  科目情報一覧画面のHTML
	 */
	@GetMapping("/subject/list")
	public String subjectList(Model model) {
		List<SubjectEntity> subjectlist = subjectService.searchAll();
		model.addAttribute("subjectlist", subjectlist);
		return "subject/list";
	}
	
}