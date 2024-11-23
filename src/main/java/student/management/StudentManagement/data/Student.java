package student.management.StudentManagement.data;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  //@Min()   制限をかける方法 Student.javaに書く
  // StudentControllerに@Validを書いてから
  private String id;
  private String name;
  private String kanaName;
  private String nickname;
  private String email;
  private String area;
  private int age;
  private String sex;
  private String remark;
  private boolean isDeleted;
}



