package student.management.StudentManagement;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
//@Mapperをつけると自動的にMybatisが認識して処理してくれることを認識する
public interface StudentRepository {
  @Select("SELECT * FROM students")
  //選択された名前を探してくる　検索処理
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentCourses> search2();
/*
  @Insert("INSERT student values(#{name},#{age})")
  void registerStudent(String name, int age);


  @Update("UPDATE student SET age = #{age} WHERE name = #{name}")
  void updateStudent(String name, int age);

  @Delete("DELETE FROM student WHERE name = #{name}")
  void deleteStudent(String name);

 */
}


