package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;



    @Column(name = "student_name")
    private String studentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(name = "student_teacher_real",joinColumns = {@JoinColumn(name = "student_id")},inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private Set<Teacher> teacherSet;

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }



    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
