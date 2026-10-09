package egovframework.example.student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.DataFormatter;

import org.springframework.stereotype.Repository;

@Repository
public class StudentExcelRepository {

    // Excel 파일 위치
    private static final String FILE_PATH = "C:/egov/data/students.xlsx";

    // Excel 시트 이름
    private static final String SHEET_NAME = "학생정보";

    // 전체 학생 조회
    public List<Student> findAll() throws IOException {
        List<Student> students = new ArrayList<>();
        try (
            FileInputStream fis = new FileInputStream(FILE_PATH);
            XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            
            if (sheet == null) {
                throw new IllegalArgumentException("Excel 파일에서 '"
                        + SHEET_NAME + "' 시트를 찾을 수 없습니다."
                );
            }
            
            DataFormatter formatter = new DataFormatter();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                String studentNo = formatter.formatCellValue(row.getCell(0));
                String name = formatter.formatCellValue(row.getCell(1));
                String department = formatter.formatCellValue(row.getCell(2));
                String status = formatter.formatCellValue(row.getCell(3));
                Student student = new Student(rowIndex, studentNo, name, department, status);
                students.add(student);
            }
        }

        return students;
    }

    // 학생 추가
    public void addStudent(Student student) throws IOException {
        try (
            FileInputStream fis = new FileInputStream(FILE_PATH);
            XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(SHEET_NAME);
            int newRowIndex = sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(newRowIndex);
            row.createCell(0).setCellValue(student.getStudentNo());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getDepartment());
            row.createCell(3).setCellValue(student.getStatus());
            try (
                FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }
        }
    }
    // 학번으로 학생 1명 조회 
    public Student findByStudentNo(String studentNo) throws IOException {
        // Excel 파일의 전체 학생 목록 조회
        List<Student> students = findAll();
        // 학생을 한 명씩 확인
        for (Student student : students) {
            // 입력한 학번과 같은 학생인지 비교
            if (student.getStudentNo().equals(studentNo)) {
                // 같은 학번의 학생을 찾으면 반환
                return student;
            }
        }
        // 해당 학번의 학생이 없으면 null 반환
        return null;
    }
}