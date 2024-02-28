package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DeliveryEntity;

/**
 * 配達先情報 Repository
 */
@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Integer> {
}