package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ShippingEntity;

/**
 * 科目情報 Repository
 */
@Repository
public interface ShippingRepository extends JpaRepository<ShippingEntity, Integer> {
}