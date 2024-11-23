package student.management.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;
import student.management.StudentManagement.domain.StudentDetail;

/**
 * 受講生詳細を受講生や受講生コース情報、もしくはその逆の変換を行うコンバーター
 */
@Component
public class StudentConverter {

  /**
   * 受講生に紐づく受講生コース情報をマッピングする。
   * 受講生コース情報は受講生に対して複数存在するのでループを回して受講生詳細情報を組み立てる。
   * @param studentList 受講生一覧
   * @param studentCoursList　受講生コース情報のリスト
   * @return 受講生詳細情報のリスト
   */
  public List<StudentDetail> convertStudentDetails(List<Student> studentList,
      List<StudentCourses> studentCoursList) {


    List<StudentDetail> studentDetails = new ArrayList<>();
    for (Student student : studentList) {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourses> convertStudentCourseList = new ArrayList<>();
      for (StudentCourses studentCourses : studentCoursList) {
        if (student.getId().equals(studentCourses.getStudentId())) {
          convertStudentCourseList.add(studentCourses);
        }
      }
      studentDetail.setStudentCoursList(convertStudentCourseList);
      studentDetails.add(studentDetail);
    }
    return studentDetails;

  }

}
