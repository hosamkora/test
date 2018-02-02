package controllers;

import models.Student;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.StudentControllerViews.*;

import javax.inject.Inject;
import java.util.Set;

public class StudentsController extends Controller {

    @Inject
    FormFactory formFactory;

    // show all students
    public Result index() {
        Set<Student> studentsModel = Student.allStudents();
        return ok(students.render(studentsModel));
    }

    // add student
    public Result addStudent() {
        Form<Student> studentForm = formFactory.form(Student.class);
        System.out.println(Student.allStudents().size());
        return ok(add_student.render(studentForm));
    }

    public Result saveStudent() {
        Form<Student> studentForm = formFactory.form(Student.class).bindFromRequest();
        Student student = studentForm.get();
        Student.addStudent(student);
        System.out.println( Student.allStudents().size());
        return redirect(routes.StudentsController.index());
    }

    public Result editStudent(Integer id) {
        Student student = Student.findByID(id);
        if(student == null) return notFound("Not Found 404");
        else {
            Form<Student> studentForm = formFactory.form(Student.class).fill(student);
            return ok(edit.render(studentForm));
        }
    }

    public Result updateStudent() {
        //Form<Student> studentForm = formFactory.form(Student.class).bindFromRequest();
        //Student student = studentForm.get();
        Student student  =formFactory.form(Student.class).bindFromRequest().get();
        Student oldSudent  = Student.findByID(student.getId()) ;
        if(oldSudent == null)
            return notFound("Student not found");
        else {
            oldSudent.name = student.name;
            oldSudent.age = student.age ;
        }
        return redirect(routes.StudentsController.index());
    }

    // student details
    public Result showStudentDetails(Integer id) {
        Student student = Student.findByID(id);
        if(student ==  null)
            return notFound("Not Found");
        else
            return ok(student_details.render(student));
    }

    public Result deleteStudent(Integer id) {
        Student student = Student.findByID(id);
        if(student ==null)
            return notFound("not found");
        else {
            Student.removeStudent(student);
            return redirect(routes.StudentsController.index());
        }
    }





}
