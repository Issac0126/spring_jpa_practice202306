package com.spring.jpa.chap02_qurrymethod.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Setter //실무적 측면에서 setter는 신중하게 만들 것.
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //id가 같으면 같은 객체로 인식한다.
@Builder
@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @Column(name = "stu_id") //참고: 좌측과 같은 카멜케이스 <-> 스네이크케이스는 컬럼 작성할 필요 X.
    @GeneratedValue(generator = "uid") //하단 name으로 지목
    @GenericGenerator(strategy = "uuid", name="uid")
    private String id;

    @Column(name = "stu_name", nullable = false)
    private String name;

    private String city;

    private String major;













}
