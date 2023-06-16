package com.spring.jpa.chap02_qurrymethod.repository;


import com.spring.jpa.chap02_qurrymethod.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback(false)
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void insertData(){
        Student s1 = Student.builder()
                .name("춘식이")
                .city("서울시")
                .major("수학과")
                .build();
        Student s2 = Student.builder()
                .name("라이언")
                .city("부산시")
                .major("멀티미디어디자인과")
                .build();
        Student s3 = Student.builder()
                .name("감귤이")
                .city("제주도")
                .major("수학교육과")
                .build();
        Student s4 = Student.builder()
                .name("호로롤")
                .city("대구")
                .major("건축과")
                .build();

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
        studentRepository.save(s4);
    }


    @Test
    @DisplayName("이름이 춘식이인 학생의 정보를 조회해야 한다.")
    void testFindByName() {
        //given
        String name = "춘식이";
        //when
        List<Student> student = studentRepository.findByName(name);

        //then
        assertEquals(1, student.size());
        System.out.println("student.get(0) = " + student.get(0));
        
    }

    @Test
    @DisplayName("findByCityAndMajor")
    void findByCityAndMajor() {
        //given
        String city = "제주도";
        String major = "수학교육과";

        //when
        List<Student> students = studentRepository.findByCityAndMajor(city, major);

        //then
        assertEquals(1, students.size());
        assertEquals("감귤이", students.get(0).getName());
        System.out.println("students.get(0) = " + students.get(0));
    }

    @Test
    @DisplayName("findByMajorContaining")
    void findByMajorContaining() {
        //given
        String major = "수학";

        //when
        List<Student> students = studentRepository.findByMajorContaining(major);

        //then
        assertEquals(2, students.size());

        System.out.println("\n\n");
        students.forEach(System.out::println);
        System.out.println("\n\n");

    }

    @Test
    @DisplayName("testNativeSQL")
    void testNativeSQL() {
        //given
        String name = "감귤이";

        //when
        Student student = studentRepository.findNameWithSQL(name);

        //then
        assertEquals("제주도", student.getCity());

        System.out.println("\n\n");
        System.out.println("student = " + student);
        System.out.println("\n\n");
    }


    @Test
    @DisplayName("testFindCityWithJPQL")
    void testFindCityWithJPQL() {
        //given
        String city = "제주도";

        //when
        List<Student> studentList = studentRepository.getByCityWithJPQL(city);

        //then
        assertEquals(1, studentList.size());
        assertEquals("감귤이", studentList.get(0).getName());

        System.out.println("\n\n");
        System.out.println("studentList.get(0) = " + studentList.get(0));
        System.out.println("\n\n");
    }


    @Test
    @DisplayName("testSearchNameJPQL")
    void testSearchNameJPQL() {
        //given
        String name = "이";

        //when
        List<Student> studentList = studentRepository.searchByNamesWithJPQL(name);

        //then
        assertEquals(3, studentList.size()); //춘식이 라이언 감귤이

        System.out.println("\n\n");
        studentList.forEach(System.out::println);
        System.out.println("\n\n");
    }


    @Test
    @DisplayName("JPQL로 삭제하기")
    void testDeleteWithJPQL() {
        //given
        String name = "호로롤";

        //when
        studentRepository.deleteByNameWithJPQL(name);

        //then
        List<Student> studentList = studentRepository.findByName(name);
        assertEquals(0, studentList.size());
    }








}
