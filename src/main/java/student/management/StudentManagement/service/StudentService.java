package student.management.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;
import student.management.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {

//    List<Student> students = repository.search();
//    List<Student> filteredStudents = new ArrayList<>();
//
//    for (Student student : students) {
//      if (student.getAge() >= 22) {
//        filteredStudents.add(student);
//      }
  //  }

   // return filteredStudents;
    return repository.search();
  }

  public List<StudentCourses> searchStudentCourseList() {
//    List<StudentCourses> studentCourses = repository.search2();
//    List<StudentCourses> filteredCourses = new ArrayList<>();
//
//    for (StudentCourses courses : studentCourses) {
//      if ("Javaコース".equals(courses.getCourse_name())) {
//        filteredCourses.add(courses);
//      }
 //   }

    //return filteredCourses;
    return repository.search2();
  }


}
