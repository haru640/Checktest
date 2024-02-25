package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.repository.SubjectRepository;

/**
 * 科目情報 Service
 */
@Service
public class SubjectService {
	/**
	 * 科目情報 Repository
	 */
	@Autowired
	private SubjectRepository subjectRepository;

	/**
	 * 科目情報 全検索
	 * @return  検索結果
	 */
	public List<SubjectEntity> searchAll() {
		return subjectRepository.findAll();
	}
}