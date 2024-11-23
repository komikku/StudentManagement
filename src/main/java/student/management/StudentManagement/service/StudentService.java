package student.management.StudentManagement.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import student.management.StudentManagement.controller.converter.StudentConverter;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;
import student.management.StudentManagement.domain.StudentDetail;
import student.management.StudentManagement.repository.StudentRepository;

/**
 *
 * 受講生譲歩を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */

@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生詳細の一覧検索です。
   * 全件検索を行うので、条件指定は行いません。
   *
   *@return 受講生詳細一覧(全件)
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentCourses> studentsCourseList = repository.searchStudentCoursesList();
    return converter.convertStudentDetails(studentList, studentsCourseList);
  }


  /**
   *受講生詳細検索です。
   * IDに紐づく受講生情報を取得したあと、その受講生に紐づく受講生コース情報を取得して設定します。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    //受講生情報単一の情報をとってきて
    List<StudentCourses> studentsCourse =repository.searchStudentCourse(student.getId());
    //受講生情報に紐づく複数持っているであろうコース情報を取得しに行く
    //取れてきたものをそれぞれstudentDetailに設定して
    return new StudentDetail(student, studentsCourse);
    //返す
  }

  /**
   * 受講生詳細の登録を行います。
   * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値とコース開始日、コース終了日を設定します。
   *
   * @param studentDetail　受講生詳細
   * @return 登録情報を付与した受講生詳細
   */

  @Transactional
  //  @Transactionalは、　登録をしたり更新したり削除をしたりするときは、必ず書くこと
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    //StudentDetailはどこの型を使うか　studentDetailは型を指定した情報を入れる箱でその名前を書いている
    Student student = studentDetail.getStudent();

    repository.registerStudent(student);
    //TODO:コース情報登録を行う。
    //型の情報が入ったstudentDetailをgetでstudent情報をとってきてその情報をregisterStudentに渡して保存する
    //StudentCourse.javaに書いた型と一致させるLocalDateだけなら日付だけLocalDateTimeは、日付と時間
    //StudentCourseにはcourseNameしか設定されていない他はされてない状態だからここで作ってあげる
    studentDetail.getStudentCoursList().forEach(studentCourses -> {
      initStudentsCourse(studentCourses, student);
      repository.registerStudentCourse(studentCourses);
    });
    return studentDetail;

  }

  /**
   * 受講生コース情報を登録する際の初期情報を設定する。
   *
   * @param studentCourses　受講生コース情報
   * @param student 受講生
   */

  private static void initStudentsCourse(StudentCourses studentCourses, Student student) {
    LocalDateTime now = LocalDateTime.now();

    studentCourses.setStudentId(student.getId());
    studentCourses.setCourseStartAt(now);
    studentCourses.setCourseEndAt(now.plusYears(1));
  }

  /**
   * 受講生詳細の更新を行います。受講生と受講生コース情報をそれぞれ更新します。
   *
   * @param studentDetail 受講生詳細
   */

  @Transactional
  //@Transactionalは、　登録をしたり更新したり削除をしたりするときは、必ず書くこと
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCoursList()
        .forEach(studentsCourses -> repository.updateStudentCourse(studentsCourses));
  }
}
