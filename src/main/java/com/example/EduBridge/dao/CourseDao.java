package com.example.EduBridge.dao;

import com.example.EduBridge.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Long> {

    List<Course> findCoursesByCourseNameContains(String keyword);


    @Query(value = "select * from Courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    List<Course> getCoursesByStudentId(@Param("studentId") Long studentId);
}
