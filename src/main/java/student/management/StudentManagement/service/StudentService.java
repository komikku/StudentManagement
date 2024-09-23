package student.management.StudentManagement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;
import student.management.StudentManagement.domain.StudentDetail;
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

  @Transactional
  //@Transactionalは、　登録をしたり更新したり削除をしたりするときは、必ず書くこと
  public void registerStudent(StudentDetail studentDetail){
    //StudentDetailはどこの型を使うか　studentDetailは型を指定した情報を入れる箱でその名前を書いている
    repository.registerStudent(studentDetail.getStudent());
    studentDetail.getStudent().getId();

    //TODO:コース情報登録を行う。
    //型の情報が入ったstudentDetailをgetでstudent情報をとってきてその情報をregisterStudentに渡して保存する

    for(StudentCourses studentCourses : studentDetail.getStudentCourses()){
    studentCourses.setStudentId(studentDetail.getStudent().getId());

    studentCourses.setCourseStartAt(LocalDateTime.now());
    //StudentCourse.javaに書いた型と一致させるLocalDateだけなら日付だけLocalDateTimeは、日付と時間

    studentCourses.setCourseEndAt(LocalDateTime.now().plusYears(1));
    //StudentCourseにはcourseNameしか設定されていない他はされてない状態だからここで作ってあげる
      repository.registerStudentCourses(studentCourses);
    }

  }


}
