package com.spring.jpa.chap01_basic.repository;

import com.spring.jpa.chap01_basic.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import static com.spring.jpa.chap01_basic.entity.Product.Category.*;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback(false) // (true) => 테스트에 사용한 더미 데이터를 롤백하겠다.
class ProductRepositoryTest {

    @BeforeEach //테스트를 돌리기 전에 실행
    void insertDummyData() {
        //given
        Product p1 = Product.builder()
                .name("아이폰")
                .category(ELECTRONIC)
                .price(1200000)
                .build();
        Product p2 = Product.builder()
                .name("탕수육")
                .category(FOOD)
                .price(16000)
                .build();
        Product p3 = Product.builder()
                .name("구두")
                .category(FASHION)
                .price(30000)
                .build();
        Product p4 = Product.builder()
                .name("새싹")
                .category(FOOD)
                .build();

        //when
        Product saved1 = productRepository.save(p1);
        Product saved2 = productRepository.save(p2);
        Product saved3 = productRepository.save(p3);
        Product saved4 = productRepository.save(p4);

        //then

    } //테스트 돌리기 전 기본실행 END


    @Test
    @DisplayName("5번째 상품을 데이터베이스에 저장해야 한다.")
    void testSave() {
        Product product = Product.builder()
                .name("정장")
                .price(58000)
                .category(FASHION)
                .build();

        Product saved = productRepository.save(product);
        assertNotNull(saved); //then

    }

    @Autowired
    ProductRepository productRepository;


    @Test
    @DisplayName("id가 2번인 데이터를 삭제해야 한다.")
    void testRemove() {
        //given
        long id = 2L;

        //when
        productRepository.deleteById(id);

        //then
    }
    
    @Test
    @DisplayName("상품 전체조회를 하면 상품의 개수가 4개여야 한다.")
    void testFindAll() {
        //given
        //when
        List<Product> productList = productRepository.findAll();

        //then
        productList.forEach(System.out::println);
        assertEquals(4, productList.size());

    }
    
    
    @Test
    @DisplayName("3번 상품을 조회하면 상품명이 구두여야 한다.")
    void testFindOne() {
        //given
        long id = 3L;

        //when
        Optional<Product> product = productRepository.findById(id);

        //then
        product.ifPresent(p -> {
            assertEquals("구두", p.getName());
        });
        Product foundProduct = product.get();
        assertNotNull(foundProduct);
        // 단언 둘 다 통과 = 반환된 상품명은 "구두"이고, null이 아니다.


    }
    



}
