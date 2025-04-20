package com.example.EduBridge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long courseId;

    @Basic
    @Column(length = 64,nullable=false)
    private String courseName;

    @Basic
    @Column(length = 64,nullable=false)
    private String courseDuration;

    @Basic
    @Column(unique=true,nullable=false)
    private String courseDescription;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "instructorId",referencedColumnName = "instructorId",nullable = false)
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrolled_in",
    joinColumns = {@JoinColumn(name="courseId")},
    inverseJoinColumns = {@JoinColumn(name = "studentId")})
    private Set<Student> students = new HashSet<>();

    public Course(String courseName, String courseDuration, String courseDescription, Instructor instructor) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseDescription = courseDescription;
        this.instructor = instructor;
    }

    public Course() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId.equals(course.courseId) && Objects.equals(courseName ,course.courseName) && Objects.equals(courseDuration, course.courseDuration) && Objects.equals(courseDescription, course.courseDescription);

    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseDuration, courseDescription);
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }


    public void assignStudentToCourse(Student student) {
        this.students.add(student);
        student.getCourses().add(this);
    }
}
