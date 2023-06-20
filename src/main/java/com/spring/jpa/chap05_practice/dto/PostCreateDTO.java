package com.spring.jpa.chap05_practice.dto;


import com.spring.jpa.chap05_practice.entity.Post;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Setter @Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PostCreateDTO {

//    @NotNull -> null을 허용하지 않음. ""(빈 문자열), " "(공백)은 허용
//    @NotEmpty -> null, ""을 허용하지 않음. " "은 허용.
//    @NotBlank -> 셋 다 비허용
    @NotBlank //공백을 허용하지 않음
    @Size(min = 2, max = 5) //입력 길이 제한
    private String writer;

    @NotBlank
    @Size(min = 1, max = 20)
    private String title;
    private String content;
    private List<String> hashTags;

    //dto를 엔티티로 변환하는 메서드
    public Post toEntity(){
        return Post.builder()
                .writer(this.writer)
                .content(this.content)
                .title(this.title)
                //해시태그는 여기서 넣지 않음!! Post 테이블엔 hashTags에 대한 컬럼이 없다.
                .build();
    }


}
