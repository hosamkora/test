package controllers;

import models.Student;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import views.html.StudentControllerViews.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

public class StudentsController extends Controller {

    @Inject
    FormFactory formFactory;

    // show all students
    public Result index() {
        List<Student> studentsModel = Student.find.all();
        return ok(students.render(studentsModel));
    }

    // add student
    public Result addStudent() {
        Form<Student> studentForm = formFactory.form(Student.class);
        return ok(add_student.render(studentForm));
    }

    public Result saveStudent() {
        Form<Student> studentForm = formFactory.form(Student.class).bindFromRequest();
        Student student = studentForm.get();
        student.save();
        System.out.println( Student.find.all().size());
        return redirect(routes.StudentsController.index());
    }

    public Result editStudent(Integer id) {
        Student student = Student.find.byId(id);
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
        Student oldSudent  = Student.find.byId(student.id) ;
        if(oldSudent == null)
            return notFound("Student not found");
        else {
            oldSudent.name = student.name;
            oldSudent.age = student.age ;
            oldSudent.update();
        }
        return redirect(routes.StudentsController.index());
    }

    // student details
    public Result showStudentDetails(Integer id) {
        Student student = Student.find.byId(id);
        if(student ==  null)
            return notFound("Not Found");
        else
            return ok(student_details.render(student));
    }

    public Result deleteStudent(Integer id) {
        Student student = Student.find.byId(id);
        if(student ==null)
            return notFound("not found");
        else {
            student.delete();
            return redirect(routes.StudentsController.index());
        }
    }





}
