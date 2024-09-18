package student.management.StudentManagement.controller;


import java.util.ArrayList;
import java.util.List;
import javax.naming.Binding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import student.management.StudentManagement.controller.converter.StudentConverter;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;
import student.management.StudentManagement.domain.StudentDetail;
import student.management.StudentManagement.service.StudentService;


@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    //Autowiredは、GetやSetを書かなくても自動的にやってくれる serviceとconverterを自動的に用意してくれる
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Student> students = service.searchStudentList();
    List<StudentCourses> studentsCourses = service.searchStudentCourseList();

    model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));
    return "studentList";


  }

  @GetMapping("/studentCourseList")
  public List<StudentCourses> getStudentCourseList() {
    return service.searchStudentCourseList();

  }

  @GetMapping("/newStudent")
  public String newStudent(Model model){
    model.addAttribute("studentDetail",new StudentDetail());
    //空っぽのオブジェクトを設定して↓のregisterStudentでregisterStudent.htmlを表示
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result){
    //registerStudent.htmlの紐づけを@ModelAttributeをつけることによりやってくれる
    //thymeleafを使うときは、確実に行うもの　入力チェックしたその結果をBindinResultに格納して元の画面に返す
    if(result.hasErrors()){
      return  "registerStudent";
    }
    //System.out.println(studentDetail.getStudent().getName() + "さんが新規受講生として登録されました。");

    //　①新規受講生情報を登録する処理を実装する。
    service.registerStudent(studentDetail);
    // ②コース情報も一緒に登録できるように実装する。コースは単体で良い。

    return "redirect:/studentList";
    //studentListに飛ばす
  }
}
