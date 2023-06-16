package com.spring.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;


}
