package com.spring.jpa.chap05_practice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter  @Setter  @ToString
public class PageResponseDTO<T> {

    private int startPage; // 시작페이지 : 1 
    private int endPage;  // 끝 페이지 : 10
    private int currentPage;  // 현재 페이지 : ?
    private boolean prev; //이전 페이지로
    private boolean next; //다음 페이지로

    private int totalCount; // 총 페이지 수

    //한 페이지에 배치할 버튼 수 (1~10 // 11~20) 고정이라 상수.
    private static final int PAGE_COUNT = 10;

    // 제네릭 타입을 <T>로 선언하면, 전달되는 객체의 제네릭 타입에 따라
    // T가 결정된다. -> 클래스에도 제네릭 타입 <T> 선언을 해야됨.
    public PageResponseDTO(Page<T> pageData) { //페이지 공식
        this.totalCount = (int) pageData.getTotalElements();
        this.currentPage = pageData.getPageable().getPageNumber() + 1;
        this.endPage
                = (int) (Math.ceil((double) currentPage / PAGE_COUNT) * PAGE_COUNT);
        this.startPage = endPage - PAGE_COUNT + 1;

        int realEnd = pageData.getTotalPages();

        if(realEnd < this.endPage) this.endPage = realEnd;

        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }


}
