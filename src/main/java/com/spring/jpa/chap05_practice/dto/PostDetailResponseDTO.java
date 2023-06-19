package com.spring.jpa.chap05_practice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.jpa.chap05_practice.entity.HashTag;
import com.spring.jpa.chap05_practice.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PostDetailResponseDTO {
    //글 상세보기 리스트에서도 재활용 가능!

    private String author;
    private String title;
    private String content;
    private List<String> hashTags;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime regDate;

    public PostDetailResponseDTO(Post post){
        this.author = post.getWriter();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.regDate = post.getCreateDate();
        //HashTag 같은 경우에는 가공해서 집어넣어야 한다. (리스크의 타입이 서로 다름.)
//        List<String> list = new ArrayList<>();
//        for (HashTag ht : post.getHashTags()){
//            list.add(ht.getTagName());
//        }
//        this.hashTags = list;
        this.hashTags = post.getHashTags()  // List<HashTag>
                .stream()                   //stream 객체를 받아옴. (컬렉션 데이터를 함수선언으로 처리할 수 있게 해주는 객체)
                .map(HashTag::getTagName)   //스트림 내의 요소들에 함수가 적용된 결과를 새로운 요소로 맵핑
                .collect(Collectors.toList()); //스크림 객체를 새로운 리스트의 형태로 리턴 -> this.hashTags에게 대입
    }



}
