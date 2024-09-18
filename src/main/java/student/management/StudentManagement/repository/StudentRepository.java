package student.management.StudentManagement.repository;


import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;

/**
 * 受講生情報を扱うリポジトリ 全件検索や単一条件での検索、コース情報の検索が行えるクラスです。
 */

@Mapper

public interface StudentRepository {

  /**
   * 全件検索します。
   *
   * @return 全件検索した受講生情報の一覧
   */

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentCourses> search2();

  @Insert("INSERT INTO students(name, kana_name, nickname, email, area, age, sex, remark, isDeleted)"
      + " VALUES(#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remark}, false)")
  //idは、自動採番をしているから書かない　isDeletedは、削除機能だから今回はfalseで良い
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);
  //今回は、返り値を持たないからvoidで書く
}


