package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ShippingEntity;
import com.example.demo.form.ShippingForm;
import com.example.demo.repository.ShippingRepository;

/**
 * 科目情報 Service
 */
@Service
public class ShippingService {
	/**
	 * 科目情報 Repository
	 */
	@Autowired
	private ShippingRepository shippingRepository;

	/**
	 * 科目情報 全検索
	 * @return  検索結果
	 */
	public List<ShippingEntity> searchAll() {
		return shippingRepository.findAll();
	}
	
	/**
	 * 科目情報 新規登録
	 * @param  subject 科目情報
	 */
	public void create(ShippingForm shippingRequest) {
		ShippingEntity shipping = new ShippingEntity();
		shipping.setName(shippingRequest.getName());
		shipping.setAddress(shippingRequest.getAddress());
		shipping.setAge(shippingRequest.getAge());
		shippingRepository.save(shipping);
	}
	
	/**
	 * 科目情報 主キー検索
	 * @return  検索結果
	 */
	public ShippingEntity findById(Integer id) {
		return shippingRepository.getOne(id);
	}
	
	/**
	 * 科目情報 更新
	 * @param  subject 科目情報
	 */
	public void update(ShippingForm subjectUpdateRequest) {
		ShippingEntity subject = findById(subjectUpdateRequest.getId());
		subject.setSubject(subjectUpdateRequest.getSubject());
		subjectRepository.save(subject);
	}
	
	/**
	 * 科目情報 物理削除
	 * @param  id ID
	 */
	public void delete(Integer id) {
		ShippingEntity subject = findById(id);
		subjectRepository.delete(subject);
	}
}