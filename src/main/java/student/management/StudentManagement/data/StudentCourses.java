package student.management.StudentManagement.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

@Getter
@Setter

public class StudentCourses {


  private String id;
  private String studentId;
  private String courseName;
  private LocalDateTime courseStartAt;
  private LocalDateTime courseEndAt;


}
