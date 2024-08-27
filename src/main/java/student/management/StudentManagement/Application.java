package student.management.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
	public static void main(String[] args) {
		//localhost:8080
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/raisetech")
	//この(/hello)が入力されたら↓のものを動かしますよってこと
	public	String hello(){
		//文字列を返す
		return "第２回演習課題";
	}
}
