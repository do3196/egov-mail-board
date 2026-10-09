package egovframework.example.student;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* 학생 관리 기능을 담당하는 Controller
 * 브라우저에서 들어오는 요청을 받아서 StudentService를 호출하고
 * 결과를 JSP 화면으로 전달하는 역할을 한다. */
@Controller
public class StudentController {
	/* StudentService 객체를 저장할 변수
     * Controller에서 직접 학생 데이터를 처리하지 않고
     * StudentService에게 실제 데이터 처리를 맡긴다. */
    private final StudentService studentService;

    /* 생성자 : Spring이 StudentService 객체를 자동으로 생성해서 이 생성자의 매개변수로 전달한다.
     * 이것을 생성자 주입(Constructor Injection)이라고 한다. */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. 학생 전체 목록 조회
    /* 브라우저에서 /students.do 주소로 GET 요청이 들어오면 실행된다.
     * 예) http://localhost:8080/egov-project/students.do */
    @GetMapping("/students.do")
    public String students(Model model) throws IOException {
        /* StudentService의 getStudents()를 호출해서 전체 학생 목록을 가져온다.
         * students 변수에는 여러 개의 Student 객체가 들어 있다. */
        List<Student> students =
                studentService.getStudents();
        /* JSP 화면에서 학생 목록을 사용할 수 있도록 Model 객체에 저장한다.
         * "students"라는 이름으로 저장했기 때문에 JSP에서는 ${students}로 사용할 수 있다. */
        model.addAttribute("students", students);
        /* 전체 학생 수를 계산해서 JSP 화면으로 전달한다.
         * students.size() → 현재 학생 수를 반환한다.
         * JSP에서는 ${studentCount}로 사용한다. */
        model.addAttribute("studentCount", students.size());
        /* /WEB-INF/jsp/students.jsp */
        return "students";
    }
    
    @GetMapping("/student-form.do")
    public String studentForm() {
        /* student-form.jsp 화면을 보여준다. 
         * ViewResolver에 의해 /WEB-INF/jsp/student-form.jsp 파일이 실행된다. */
        return "student-form";
    }

    // 3. 학생 등록 처리
    /* student-form.jsp에서 등록 버튼을 누르면 실행된다.
     * form에서 method="post"로 데이터를 보내기 때문에 @PostMapping을 사용한다.
     * 요청 주소: /student-add.do */
    @PostMapping("/student-add.do")
    public String addStudent(
    		@RequestParam("studentNo") String studentNo,
    		@RequestParam("name") String name,
    		@RequestParam("department") String department,
    		@RequestParam("status") String status, Model model) throws IOException {
    		/* student-form.jsp에서
    		/* studentNo="studentNo" 으로 입력된 값을 받는다. */
    		/* name="name" 으로 입력된 값을 받는다. */
            /* name="department" 으로 입력된 학과 값을 받는다. */
            /* name="status" 로 선택한 재학/휴학 값을 받는다. */
    	 
		// 학번 중복 확인
		if (studentService.existsStudentNo(studentNo)) {
	        // 오류 메시지를 JSP로 전달
	        model.addAttribute("error", "이미 등록된 학번입니다.");
	        // 입력했던 값도 다시 JSP로 전달
	        model.addAttribute("studentNo", studentNo);
	        model.addAttribute("name", name );
	        model.addAttribute("department", department);
	        model.addAttribute("status", status);
	        // 다시 학생 등록 화면
	        return "student-form";
	    }
    			
        /* StudentService의 addStudent() 메서드를 호출한다.
         * 사용자가 입력한 이름, 학과, 상태 값을 StudentService로 전달한다. */
        studentService.addStudent(studentNo, name, department, status);
        /* 학생 등록이 끝나면 학생 목록 페이지로 다시 이동한다.
         * redirect:를 사용하면 브라우저가 /students.do 주소로 새로운 요청을 다시 보낸다. */
        return "redirect:/students.do";
    }
    // 학생 상세정보 조회
    @GetMapping("/student-detail.do")
    public String studentDetail(@RequestParam("studentNo") String studentNo, Model model)
            throws IOException {
        // 학번을 이용하여 학생 한 명 조회
        Student student = studentService.getStudent(studentNo);
        // 조회한 학생 정보를 "student"라는 이름으로 JSP에 전달
        model.addAttribute("student", student);
        // student-detail.jsp 화면으로 이동
        return "student-detail";
    }
}
