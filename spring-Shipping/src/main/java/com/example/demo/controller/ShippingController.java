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
	public String shippingCreate(@Validated @ModelAttribute ShippingForm shippingRequest, BindingResult result,
			Model model) {
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
		shippingService.create(shippingRequest);
		return "redirect:/shipping/list";
	}

	/**
	 * 科目情報詳細画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@GetMapping("/shipping/{id}")
	public String userDetail(@PathVariable Integer id, Model model) {
		ShippingEntity shipping = shippingService.findById(id);
		model.addAttribute("shipping", shipping);
		return "shipping/detail";
	}

	/**
	 * 科目編集画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  科目編集画面
	 */
	@GetMapping("/shipping/{id}/edit")
	public String userEdit(@PathVariable Integer id, Model model) {
		ShippingEntity subject = shippingService.findById(id);
		ShippingForm shippingUpdateRequest = new ShippingForm();
		shippingUpdateRequest.setId(shipping.getId());
		shippingUpdateRequest.setName(shipping.getName());
		shippingUpdateRequest.setAddress(shipping.getAddress());
		model.addAttribute("shippingUpdateRequest", shippingUpdateRequest);
		return "shipping/edit";
	}

	/**
	 * 科目更新
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  科目情報詳細画面
	 */
	@PostMapping("/shipping/update")
	public String shippingUpdate(@Validated @ModelAttribute ShippingForm shippingdateRequest, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "shipping/edit";
		}
		// 科目情報の更新
		shippingService.update(shippingUpdateRequest);
		return String.format("redirect:/shipping/%d", shippingUpdateRequest.getId());
	}

	/**
	* 科目情報削除
	* @param  id 表示するID
	* @param  model Model
	* @return  科目情報詳細画面
	*/
	@GetMapping("/shipping{id}/delete")
	public String shippingDelete(@PathVariable Integer id, Model model) {
		// 科目情報の削除
		shippingService.delete(id);
		return "redirect:/shipping/list";
	}
}
