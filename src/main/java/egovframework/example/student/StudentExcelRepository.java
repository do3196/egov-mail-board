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
    // 4. 학생 정보 수정
    public void updateStudent(String studentNo, String name, String department, String status) throws IOException {

        // Excel 파일을 읽어서 수정하기 위해 Workbook 객체 생성
        XSSFWorkbook workbook;
        // Excel 파일 읽기
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            workbook = new XSSFWorkbook(fis);
        }
        // 학생정보 시트 가져오기
        Sheet sheet = workbook.getSheet(SHEET_NAME);

        if (sheet == null) { // 시트가 없는 경우 오류 발생
            workbook.close();
            throw new IllegalArgumentException("Excel 파일에서 '" + SHEET_NAME
                    + "' 시트를 찾을 수 없습니다.");
        }
        // 숫자/문자 셀을 문자열로 읽기 위해 사용
        DataFormatter formatter = new DataFormatter();
        // Excel의 학생 데이터를 한 행씩 검색
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            if (row == null) { // 빈 행이면 다음 행으로 이동
                continue;
            }
            // 현재 행에 있는 학번 읽기
            String excelStudentNo = formatter.formatCellValue(row.getCell(0));
            // 수정하려는 학생의 학번과 Excel의 학번이 같은지 비교
            if (excelStudentNo.equals(studentNo)) {
                // 이름 수정
                row.getCell(1).setCellValue(name);
                // 학과 수정
                row.getCell(2).setCellValue(department);
                // 상태 수정
                row.getCell(3).setCellValue(status);
                break; // 수정할 학생을 찾았으므로 반복문 종료
            }
        }
        // 수정된 내용을 Excel 파일에 저장
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        }
        workbook.close(); // Workbook 사용 종료
    }
    // 5. 학생 삭제
    public void deleteStudent(String studentNo) throws IOException {XSSFWorkbook workbook; // Excel 파일을 읽기 위한 Workbook
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {// Excel 파일 읽기
            workbook = new XSSFWorkbook(fis);
        }

        Sheet sheet = workbook.getSheet(SHEET_NAME); // 학생정보 시트 가져오기
        if (sheet == null) { // 시트가 없으면 오류 발생
            workbook.close();
            throw new IllegalArgumentException("Excel 파일에서 '" + SHEET_NAME + "' 시트를 찾을 수 없습니다.");
        }
        // Excel 셀 값을 문자열로 읽기 위해 사용
        DataFormatter formatter = new DataFormatter();
        // 삭제할 행 번호를 저장, -1은 아직 학생을 찾지 못했다는 의미
        int deleteRowIndex = -1;
        // 학생 데이터를 한 행씩 검색
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) { // 빈 행이면 다음 행으로 이동
                continue;
            }
            // 현재 행의 학번 읽기
            String excelStudentNo = formatter.formatCellValue(row.getCell(0));

            if (excelStudentNo.equals(studentNo)) { // 삭제할 학번과 같은지 비교
                deleteRowIndex = rowIndex; // 삭제할 행 번호 저장
                break; // 학생을 찾았으므로 반복 종료
            }
        }

        if (deleteRowIndex != -1) { // 삭제할 학생을 찾은 경우
            int lastRowIndex = sheet.getLastRowNum(); // Excel의 마지막 행 번호 확인
            if (deleteRowIndex < lastRowIndex) {// 삭제할 행이 마지막 행이 아닌 경우
                // 삭제할 행 아래의 데이터를 한 칸씩 위로 이동
                sheet.shiftRows(deleteRowIndex + 1, lastRowIndex, -1);
            }
            else {
                // 마지막 행을 삭제하는 경우
                Row row = sheet.getRow(deleteRowIndex);

                if (row != null) { // 행이 존재하면 삭제
                    sheet.removeRow(row);
                }
            }
        }
        // 삭제 결과를 Excel 파일에 저장
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        }
        workbook.close(); // Workbook 사용 종료
    }
    // 6. 학생 검색
    public List<Student> searchStudents(String keyword) throws IOException {
        // Excel 파일의 전체 학생 목록 조회
        List<Student> students = findAll();
        // 검색 결과를 저장할 새로운 List 생성
        List<Student> result = new ArrayList<>();
        
        for (Student student : students) {// 전체 학생을 한 명씩 확인
            // 학번에 검색어가 포함되어 있거나 이름에 검색어가 포함되어 있는지 확인
            if (student.getStudentNo().contains(keyword) || student.getName().contains(keyword) || student.getDepartment().contains(keyword))) {                
                result.add(student); // 검색 조건에 맞는 학생을 검색 결과 List에 추가
            }
        }      
        return result; // 검색된 학생 목록 반환
    }
}