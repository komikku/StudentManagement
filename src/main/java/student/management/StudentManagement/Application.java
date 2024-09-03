package student.management.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

  @Autowired
  private StudentRepository repository;

  private String name = "Toshisue Hiroto";
  private String age = "22";
  private Map<String, String> studentNameMap = new HashMap<>(
      Map.of("1", "田中", "2", "斎藤", "3", "佐藤", "4", "広田", "5", "草薙"));
  //変数名は絶対に必要だよ

  public static void main(String[] args) {
    //localhost:8080
    SpringApplication.run(Application.class, args);

  }

  @GetMapping("/student")
  //この(/hello)が入力されたら↓のものを動かしますよってこと
  public String getStudent(@RequestParam String name) {
    //パラメータで検索したものをとってくる
    Student student = repository.searchByName(name);

    //Student student	= new Student();
    //student.setName("Toshisue Hiroto");
    //String name = student.getName();

    //StringUtilsでも色々合ってapache commonsの方を使うたくさん機能がある　
    //文字列を返す
    return student.getName() + " " + student.getAge() + "歳";


  }

  @PostMapping("/student")
  public void registerStudent(String name, int age) {
    repository.registerStudent(name, age);

    //二つPOSTで書きたい場合は↓こういう書き方をする。
    //curl -X POST -d "name=Toshisue Hiroto&age=22" http://localhost:8080/studentInfo
  }

  @PatchMapping("/student")
  public void updateStudent(String name, int age) {
    //年齢だけだとすべての名前の人を変えてしまうからnameと年齢をしてする
    repository.registerStudent(name,age);
  }

  @PostMapping("/studentNumber")
  public void updateStudentNumber(String id, String name1) {
    studentNameMap.put(id, name1);
  }

  //GET POST
  //GETは取得する、リクエストの結果を受け取る、データを取得するときはGETを使う
  //POSTは情報を与える、渡す　、データ登録や更新するときはPOSTを使う

  @DeleteMapping("/student")
  public void deleteStudent(String name){
    repository.deleteStudent(name);
  }


}