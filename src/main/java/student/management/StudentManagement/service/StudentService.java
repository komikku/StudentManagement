package student.management.StudentManagement.service;

import java.time.LocalDateTime;
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
  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    //受講生情報単一の情報をとってきて
    List<StudentCourses>studentCourses=repository.searchStudentCourses(student.getId());
    //受講生情報に紐づく複数持っているであろうコース情報を取得しに行く
    StudentDetail studentDetail = new StudentDetail();
    //取れてきたものをそれぞれstudentDetailに設定して
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(studentCourses);
    return studentDetail;
    //返す
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
    return repository.searchStudentCoursesList();
  }

  @Transactional
  //@Transactionalは、　登録をしたり更新したり削除をしたりするときは、必ず書くこと
  public void registerStudent(StudentDetail studentDetail) {
    //StudentDetailはどこの型を使うか　studentDetailは型を指定した情報を入れる箱でその名前を書いている
    repository.registerStudent(studentDetail.getStudent());

    //TODO:コース情報登録を行う。
    //型の情報が入ったstudentDetailをgetでstudent情報をとってきてその情報をregisterStudentに渡して保存する

    for (StudentCourses studentCourses : studentDetail.getStudentCourses()) {
      studentCourses.setStudentId(studentDetail.getStudent().getId());
      studentCourses.setCourseStartAt(LocalDateTime.now());
      //StudentCourse.javaに書いた型と一致させるLocalDateだけなら日付だけLocalDateTimeは、日付と時間
      studentCourses.setCourseEndAt(LocalDateTime.now().plusYears(1));
      //StudentCourseにはcourseNameしか設定されていない他はされてない状態だからここで作ってあげる
      repository.registerStudentCourses(studentCourses);
    }

  }


  @Transactional
  //@Transactionalは、　登録をしたり更新したり削除をしたりするときは、必ず書くこと
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentCourses studentCourses : studentDetail.getStudentCourses()) {
      repository.updateStudentCourses(studentCourses);
    }
  }
}
