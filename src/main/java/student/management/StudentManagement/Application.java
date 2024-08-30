package student.management.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	private	String name = "Toshisue Hiroto";
	private String age = "22";
	private Map<String, String>studentNameMap = new HashMap<>(Map.of("1","田中","2","斎藤","3","佐藤","4","広田","5","草薙"));
	//変数名は絶対に必要だよ

	public static void main(String[] args) {
		//localhost:8080
		SpringApplication.run(Application.class, args);

	}

	@GetMapping("/studentInfo")
	//この(/hello)が入力されたら↓のものを動かしますよってこと
	public String getStudentInfo(){


		//StringUtilsでも色々合ってapache commonsの方を使うたくさん機能がある　
		//文字列を返す
		return name + " " + age + "歳" + studentNameMap;


	}
	@PostMapping("/studentInfo")
	public void setStudentInfo(String name, String age){
		this.name = name;
		this.age = age;

		//二つPOSTで書きたい場合は↓こういう書き方をする。
		//curl -X POST -d "name=Toshisue Hiroto&age=22" http://localhost:8080/studentInfo
	}

	@PostMapping("/studentName")
	public void updateStudentName(String name){
		this.name = name;
	}
	@PostMapping("/studentNumber")
	public void updateStudentNumber(String id, String name1){
		studentNameMap.put(id,name1);
	}



	//GET POST
	//GETは取得する、リクエストの結果を受け取る、データを取得するときはGETを使う
	//POSTは情報を与える、渡す　、データ登録や更新するときはPOSTを使う


}
