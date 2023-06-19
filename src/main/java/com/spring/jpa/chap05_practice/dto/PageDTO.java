package com.spring.jpa.chap05_practice.dto;

import lombok.*;

@Getter @Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class PageDTO {

    private int page;
    private int size;

    public PageDTO() { //기본값 설정!
        this.page = 1;
        this.size = 10;
    }
}
