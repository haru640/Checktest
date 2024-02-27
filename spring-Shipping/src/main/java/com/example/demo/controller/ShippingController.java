package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.ShippingEntity;
import com.example.demo.form.ShippingForm;
import com.example.demo.service.ShippingService;

/**
 * 科目情報 Controller
 */
@Controller
public class ShippingController {

	/**
	 * 科目情報 Service
	 */
	@Autowired
	ShippingService shippingService;

	/**
	 * 科目情報一覧画面を表示
	 * @param  model Model
	 * @return  科目情報一覧画面のHTML
	 */
	@GetMapping("/shipping/list")
	public String shippingList(Model model) {
		List<ShippingEntity> shippinglist = shippingService.searchAll();
		model.addAttribute("shippinglist", shippinglist);
		return "shipping/list";
	}
	
	/**
	 * 科目新規登録画面を表示
	 * @param  model Model
	 * @return  科目情報一覧画面
	 */
	@GetMapping("/shipping/add")
	public String shippingRegister(Model model) {
		model.addAttribute("shippingRequest", new ShippingForm());
		return "shipping/add";
	}
	
	/**
	 * 科目新規登録
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  科目情報一覧画面
	 */
	@PostMapping("/shipping/create")
	public String shippingCreate(@Validated  @ModelAttribute  ShippingForm shippingRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("shippingRequest", new ShippingForm());
			model.addAttribute("validationError", errorList);
			return "shipping/add";
		}
		// 科目情報の登録
		subjectService.create(subjectRequest);
		return "redirect:/subject/list";
	}
	
	/**
	 * 科目情報詳細画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@GetMapping("/subject/{id}")
	public String userDetail(@PathVariable  Integer id, Model model) {
		ShippingEntity subject = subjectService.findById(id);
		model.addAttribute("subject", subject);
		return "subject/detail";
	}
	
	/**
	 * 科目編集画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  科目編集画面
	 */
	@GetMapping("/subject/{id}/edit")
	public String userEdit(@PathVariable  Integer id, Model model) {
		ShippingEntity subject = subjectService.findById(id);
		ShippingForm subjectUpdateRequest = new ShippingForm();
		subjectUpdateRequest.setId(subject.getId());
		subjectUpdateRequest.setSubject(subject.getSubject());
		model.addAttribute("subjectUpdateRequest", subjectUpdateRequest);
		return "subject/edit";
	}
	
	/**
	 * 科目更新
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@PostMapping("/subject/update")
	public String subjectUpdate(@Validated  @ModelAttribute  ShippingForm subjectUpdateRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "subject/edit";
		}
		// 科目情報の更新
		subjectService.update(subjectUpdateRequest);
		return String.format("redirect:/subject/%d", subjectUpdateRequest.getId());
	}
	
	 /**
	 * 科目情報削除
	 * @param  id 表示するID
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@GetMapping("/subject/{id}/delete")
	public String subjectDelete(@PathVariable Integer id, Model model) {
		// 科目情報の削除
		subjectService.delete(id);
		return "redirect:/subject/list";
	}
}
