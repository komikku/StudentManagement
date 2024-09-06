package student.management.StudentManagement;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper

public interface StudentRepository {
  @Select("SELECT * FROM students")
  //選択された名前を探してくる　検索処理
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentCourses> search2();

}


