package student.management.StudentManagement.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import student.management.StudentManagement.controller.converter.StudentConverter;
import student.management.StudentManagement.data.Student;
import student.management.StudentManagement.data.StudentCourses;
import student.management.StudentManagement.domain.StudentDetail;
import student.management.StudentManagement.service.StudentService;

/**
 *受講生の検索や登録、更新などを行うREST APIとして実行されるControllerです。
 */

@RestController
public class StudentController {


  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    //Autowiredは、GetやSetを書かなくても自動的にやってくれる serviceとconverterを自動的に用意してくれる
    this.service = service;
  }


  /**
   * 受講生一覧検索です。
   * 全件検索を行うので、条件指定は行いません
   *
   * @return  受講生一覧(全件)
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {

    return service.searchStudentList();
  }

  /**
   *受講生検索です。
   * IDに紐づく任意の受講生情報を取得します。
   *
   * @param id 受講生ID
   * @return 受講生
   * */
  @GetMapping("/student/{id}")
  //()のstudentは、studetHTMLのth:hrefの@指定したものと一致にしないといけない
  public StudentDetail getStudent(@PathVariable String id) {
    return service.searchStudent(id);
  }

//  @GetMapping("/newStudent")
//  public String newStudent(Model model) {
//    StudentDetail studentDetail = new StudentDetail();
//    //新しく入れれる空っぽの箱を作った
//    studentDetail.setStudentCourses(Arrays.asList(new StudentCourses()));
//    //箱にStudentCoursesをセットして複数コースも選択できるようにArrays.asListにnew StudentCoursesを入れる
//    model.addAttribute("studentDetail", studentDetail);
//    //空っぽのオブジェクトを設定して↓のregisterStudentでregisterStudent.htmlを表示
//    return "registerStudent";
//  }

//  @GetMapping("/updateStudent/{id}")
//  public String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
//    if (result.hasErrors()) {
//      return "updateStudent";
//    }
//    String studentId = (studentDetail.getStudent().getId());
//    List<StudentCourses> studentCourses = service.searchStudentCourseList();
//    for (StudentCourses courses : studentCourses) {
//      if (studentId.equals(courses.getStudentId())) {
//        //StudentRepositiryにSQL文で呼びだして探してくる
//
//        return "redirect:/studentCourseList";
//      } else {
//        return "updateStudent";
//      }
//    }
//    return "";
//    //return"redirect:/studentList";
//  }


  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    //registerStudent.htmlの紐づけを@ModelAttributeをつけることによりやってくれる
    //thymeleafを使うときは、確実に行うもの　入力チェックしたその結果をBindinResultに格納して元の画面に返す

    //System.out.println(studentDetail.getStudent().getName() + "さんが新規受講生として登録されました。");
    //　①新規受講生情報を登録する処理を実装する。
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    // ②コース情報も一緒に登録できるように実装する。コースは単体で良い。
    return ResponseEntity.ok(responseStudentDetail);
    //studentListに飛ばす

  }

  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
    //POSTで更新を行いましたそして、OKだったときに何を返すかを()の中に書く
  }
}
