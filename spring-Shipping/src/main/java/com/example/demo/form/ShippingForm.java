package com.example.demo.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 科目情報 リクエストデータ
 */
@Data
public class ShippingForm {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 科目
	 */
	@NotEmpty(message = "名前を入力してください")
	private String name;
	
	@NotEmpty(message = "住所を入力してください")
	private String address;
	
	@NotEmpty(message = "年齢を入力してください")
	private String age;

}