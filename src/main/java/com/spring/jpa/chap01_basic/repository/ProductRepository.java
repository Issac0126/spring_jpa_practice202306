package com.spring.jpa.chap01_basic.repository;

import com.spring.jpa.chap01_basic.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
//extends JpaRepository<엔티티 타입, PK데이터타입>




}
