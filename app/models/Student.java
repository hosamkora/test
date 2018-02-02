package models;

import java.util.HashSet;
import java.util.Set;

import io.ebean.Model;

import javax.persistence.*;

@Entity
public class Student extends Model{
    @Id
    public Integer id;
    public String name;
    public Integer age;

    private static Set<Student> students;

    static {
        students = new HashSet<>();
        students.add(new Student(0,"Hosam", 21));
        students.add(new Student(1,"Ahmed", 20));
        students.add(new Student(2,"Rimon", 19));
    }

    public Student(){

    }

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static Set<Student> allStudents(){
        return students;
    }

    public static Student findByID(Integer id){
        for (Student student: students)
            if(id.equals(student.id))
                return student;
        return null;
    }

    public static void addStudent(Student student){
        students.add(student);
    }

    public static boolean removeStudent(Student student){
        return students.remove(student);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
