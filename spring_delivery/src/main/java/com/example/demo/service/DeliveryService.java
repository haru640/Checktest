package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DeliveryEntity;
import com.example.demo.form.DeliveryForm;
import com.example.demo.repository.DeliveryRepository;

/**
 * 科目情報 Service
 */
@Service
public class DeliveryService {
	/**
	 * 科目情報 Repository
	 */
	@Autowired
	private DeliveryRepository deliveryRepository;

	/**
	 * 科目情報 全検索
	 * @return  検索結果
	 */
	public List<DeliveryEntity> searchAll() {
		return deliveryRepository.findAll();
	}

	/**
	 * 科目情報 新規登録
	 * @param  subject 科目情報
	 */
	public void create(DeliveryForm deliveryRequest) {
		DeliveryEntity delivery = new DeliveryEntity();
		delivery.setName(deliveryRequest.getName());
		delivery.setDeliveryitem(deliveryRequest.getDeliveryitem());
		delivery.setAddress(deliveryRequest.getAddress());
		deliveryRepository.save(delivery);
	}

	/**
	 * 科目情報 主キー検索
	 * @return  検索結果
	 */
	public DeliveryEntity findById(Integer id) {
		return deliveryRepository.getOne(id);
	}

	/**
	 * 科目情報 更新
	 * @param  subject 科目情報
	 */
	public void update(DeliveryForm deliveryUpdateRequest) {
		DeliveryEntity delivery = findById(deliveryUpdateRequest.getId());
		delivery.setName(deliveryUpdateRequest.getName());
		delivery.setDeliveryitem(deliveryUpdateRequest.getDeliveryitem());
		delivery.setAddress(deliveryUpdateRequest.getAddress());
		
		deliveryRepository.save(delivery);
	}

	/**
	 * 科目情報 物理削除
	 * @param  id ID
	 */
	public void delete(Integer id) {
		DeliveryEntity delivery = findById(id);
		deliveryRepository.delete(delivery);
	}
}