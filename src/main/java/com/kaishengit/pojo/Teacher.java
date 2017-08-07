package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
@Entity
@Table
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "teacher_name")
    private String teacherName;

    @ManyToMany(mappedBy = "teacherSet")
    private Set<Student> studentSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
