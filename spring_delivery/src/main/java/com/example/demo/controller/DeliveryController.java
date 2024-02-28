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

import com.example.demo.entity.DeliveryEntity;
import com.example.demo.form.DeliveryForm;
import com.example.demo.service.DeliveryService;

/**
 * 配達先情報 Controller
 */
@Controller
public class DeliveryController {

	/**
	 * 配達先情報 Service
	 */
	@Autowired
	DeliveryService deliveryService;

	/**
	 * 科目情報一覧画面を表示
	 * @param  model Model
	 * @return  科目情報一覧画面のHTML
	 */
	@GetMapping("/delivery/list")
	public String deliveryList(Model model) {
		List<DeliveryEntity> deliverylist = deliveryService.searchAll();
		model.addAttribute("deliverylist", deliverylist);
		return "delivery/list";
	}

	/**
	 * 配達先新規登録画面を表示
	 * @param  model Model
	 * @return  配達先情報一覧画面
	 */
	@GetMapping("/delivery/add")
	public String deliveryRegister(Model model) {
		model.addAttribute("deliveryRequest", new DeliveryForm());
		return "delivery/add";
	}

	/**
	 * 配達先新規登録
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  配達先情報一覧画面
	 */
	@PostMapping("/delivery/create")
	public String deliveryCreate(@Validated @ModelAttribute DeliveryForm deliveryRequest, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("deliveryRequest", new DeliveryForm());
			model.addAttribute("validationError", errorList);
			return "delivery/add";
		}
		//  配達先情報の登録
		deliveryService.create(deliveryRequest);
		return "redirect:/delivery/list";
	}

	/**
	 *  配達先情報詳細画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return   配達先情報詳細画面
	 */
	@GetMapping("/delivery/{id}")
	public String userDetail(@PathVariable Integer id, Model model) {
		DeliveryEntity delivery = deliveryService.findById(id);
		model.addAttribute("delivery", delivery);
		return "delivery/detail";
	}

	/**
	 * 科目編集画面を表示
	 * @param  id 表示する科目ID
	 * @param  model Model
	 * @return  配達先情報編集画面
	 */
	@GetMapping("/delivery/{id}/edit")
	public String userEdit(@PathVariable Integer id, Model model) {
		DeliveryEntity delivery = deliveryService.findById(id);
		DeliveryForm deliveryUpdateRequest = new DeliveryForm();
		deliveryUpdateRequest.setId(delivery.getId());
		deliveryUpdateRequest.setName(delivery.getName());
		deliveryUpdateRequest.setDeliveryitem(delivery.getDeliveryitem());
		deliveryUpdateRequest.setAddress(delivery.getAddress());
		model.addAttribute("deliveryUpdateRequest", deliveryUpdateRequest);
		return "delivery/edit";
	}

	/**
	 * 配達先更新
	 * @param  userRequest リクエストデータ
	 * @param  model Model
	 * @return  配達先情報詳細画面
	 */
	@PostMapping("/delivery/update")
	public String deliveryUpdate(@Validated @ModelAttribute DeliveryForm deliveryUpdateRequest, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "delivery/edit";
		}
		// 配達先情報の更新
		deliveryService.update(deliveryUpdateRequest);
		return String.format("redirect:/delivery/%d", deliveryUpdateRequest.getId());
	}

	/**
	*配達先情報削除
	* @param  id 表示するID
	* @param  model Model
	* @return  配達先情報詳細画面
	*/
	@GetMapping("/delivery/{id}/delete")
	public String deliveryDelete(@PathVariable Integer id, Model model) {
		//  配達先情報の削除
		deliveryService.delete(id);
		return "redirect:/delivery/list";
	}
}
