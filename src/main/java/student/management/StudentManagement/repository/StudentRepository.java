package student.management.StudentManagement.repository;


import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */

@Mapper
public interface StudentRepository {

  /**
   * 全件検索します。
   *
   * @return 全件検索した受講生情報の一覧
   */
  @Select("SELECT * FROM students WHERE is_deleted<>1")
  //削除フラグが立っていないものだけを取ってくる。
  List<Student> search();

  /**
   * 受講生のコース情報の全件検索を行います。
   *
   * @ return 受講生のコース情報(全件)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourses> searchStudentCoursesList();

  /**
   * 受講生の検索を行います。
   *
   * @param id 受講生ID
   * @return 受講生
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  //WHERE の後ろに書いたものによって↓もListで書くのかどうするのかを判断する。
  Student searchStudent (String id);
  //idは、名前と紐づく情報を一つしか持ってないからListにせず書いた

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param studentId  受講生ID
   * @return 受講生IDに紐づく受講生コース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourses> searchStudentCourses(String studentId);
  //これが複数コース選択してる場合を見たいであればListでとってくる必要がある。

  @Insert("INSERT INTO students(name,  kana_name, nickname, email, area, age, sex, remark, is_deleted)"
      //SQL文はデータベースのそのままの名前
      + " VALUES(#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, false)")
      //Javaの書き方の変数名を入れる部分は、snakeケースで書く

  //idは、自動採番をしているから書かない　isDeletedは、削除機能だから今回はfalseで良い
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);
  //今回は、返り値を持たないからvoidで書く

  @Insert("INSERT INTO students_courses(student_id, course_name, course_start_at, course_end_at) "
      + "VALUES(#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  //INSERTのstudents_coursesの()の中身は、SQLで作った情報を正しく入れる
  //指定したものと数が合えばserviceで呼べば終わり
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentCourses(StudentCourses studentCourses);



  @Update("UPDATE students SET name = #{name}, kana_name = #{kanaName},nickname = #{nickname},"
      + "email = #{email},area = #{area},age = #{age},sex = #{sex},remark = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
  //Javaの書き方の変数名を入れる部分は、snakeケースで書く

  //idは、自動採番をしているから書かない　isDeletedは、削除機能だから今回はfalseで良い

  void updateStudent(Student student);
  //今回は、返り値を持たないからvoidで書く

  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
        //受講するコースIDと対象の受講生紐づいてるものは変えないからstudent_idは更新で来ちゃダメだから消した
        //受講時期のcourse_start_atとcourse_end_atも変えちゃだめだから消す
  void updateStudentCourses(StudentCourses studentCourses);
}



