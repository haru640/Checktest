package com.example.demo.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 科目情報 リクエストデータ
 */
@Data
public class DeliveryForm {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 科目
	 */
	@NotEmpty(message = "※名前を入力してください")
	private String name;
	
	@NotEmpty(message = "※配達物を入力してください")
	private String deliveryitem;
	
	@NotEmpty(message = "※住所を入力してください")
	private String address;

}