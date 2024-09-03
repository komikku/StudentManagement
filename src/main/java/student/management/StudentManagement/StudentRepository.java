package student.management.StudentManagement;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
//@Mapperをつけると自動的にMybatisが認識して処理してくれることを認識する
public interface StudentRepository {
  @Select("SELECT * FROM student WHERE name = #{name}")
  //選択された名前を探してくる　検索処理
  Student searchByName(String name);


  @Insert("INSERT student values(#{name},#{age})")
  void registerStudent(String name, int age);


  @Update("UPDATE student SET age = #{age} WHERE name = #{name}")
  void updateStudent(String name, int age);

  @Delete("DELETE FROM student WHERE name = #{name}")
  void deleteStudent(String name);
}


