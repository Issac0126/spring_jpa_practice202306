package com.spring.jpa.chap02_qurrymethod.repository;

import com.spring.jpa.chap02_qurrymethod.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository
        extends JpaRepository<Student, String> {

    //이름은 다 정해져 있음. 카멜케이스 제대로 쓸 것.
    List<Student> findByName(String name); //여기서 'Name'은 필드명.
    List<Student> findByCityAndMajor(String city, String major);
    // =>  where city = {city} and major = {major}
    List<Student> findByMajorContaining(String major);

    //네이티브 쿼리 사용
    //:name으로 적으면 name을 찾아서 자리에 넣어준다.
    // nativeQuery = true -> jpa에서 SQL문 만들지 말고 내 SQL문을 따라라.
    @Query(value = "SELECT * FROM tbl_student WHERE stu_name = :nm", nativeQuery = true)
    Student findNameWithSQL(@Param("nm") String name);

    //도시 이름으로 학생 조회
    @Query("SELECT s FROM Student s WHERE s.city = ?1") // ?1 => 첫번째 매개값
    List<Student> getByCityWithJPQL(String city);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:name%") // ?1이 아니라 이름을 사용해도 무관함.
    List<Student> searchByNamesWithJPQL(String name);

    //JPQL로 수정 삭제 쿼리 쓰기
    @Modifying //조회가 아닌 경우에는 무조건 붙여야 함.
    @Query("DELETE FROM Student s WHERE s.name = ?1")
    void deleteByNameWithJPQL(String name);





}

