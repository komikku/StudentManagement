package student.management.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;
import student.management.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return service.searchStudentList();


  }

  @GetMapping("/studentCourseList")
  public List<StudentCourses> getStudentCourseList() {
    return service.searchStudentCourseList();

  }

}
