package egovframework.example.student;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
	// private final로 StudentExcelRepository 클래스를 studentRepository로 변수 지정
	private final StudentExcelRepository studentRepository;
    /* 생성자 주입 Spring이 StudentExcelRepository 객체를 생성한 후 StudentService에 전달한다. */
    public StudentService(StudentExcelRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 전체 학생 조회
    public List<Student> getStudents() throws IOException  {
		return studentRepository.findAll();
    }
    
    // 학생 추가
    public void addStudent(String studentNo, String name, String department, 
    		String status) throws IOException  {
        // 현재 Excel 파일에 저장된 전체 학생 정보를 읽는다.
    	List<Student> students = studentRepository.findAll();

    	int id = students.size() + 1;
        Student student = new Student(id, studentNo, name, department, status);

        studentRepository.addStudent(student);
    }

    // 학번 중복 확인
    public boolean existsStudentNo(String studentNo) throws IOException {
    	/* Excel 파일의 전체 학생 목록을 읽는다. */
        List<Student> students = studentRepository.findAll();
        
    	for (Student student : students) {
            if (student.getStudentNo().equals(studentNo)) {
                return true;
            }
        }
        return false;
    }
    // 전체 학생 조회
    public Student getStudent(String studentNo) throws IOException  {
		return studentRepository.findByStudentNo(studentNo);
    }
    // 5. 학생 정보 수정
    public void updateStudent(String studentNo, String name, String department, String status)
            throws IOException {
        // Repository에게 학생 정보 수정 요청
        studentRepository.updateStudent(studentNo, name, department, status);
    }

    // 6. 학생 삭제
    public void deleteStudent(String studentNo) throws IOException {
        // Repository에게 해당 학번 학생 삭제 요청
        studentRepository.deleteStudent(studentNo);
    }
    
    // 7. 학생 검색
    public List<Student> searchStudents(String keyword) throws IOException {
        // Repository에게 검색어를 전달하여 학번 또는 이름에 해당하는 학생을 검색한다.
        return studentRepository.searchStudents(keyword);
    }
}

