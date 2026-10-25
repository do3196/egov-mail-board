package egovframework.example.student;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Repository;

@Repository
public class ScoreExcelRepository {
    // 성적 Excel 파일 위치
    private static final String FILE_PATH = "C:/egov/data/scores.xlsx";
    // Excel 시트 이름
    private static final String SHEET_NAME = "성적정보";
    // 학번으로 성적 조회
    public List<Score> findByStudentNo(String studentNo) throws IOException {
        List<Score> scores = new ArrayList<>(); // 검색된 성적을 저장할 List 생성
        // Excel 파일 열기
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
            XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            // "성적정보" 시트 가져오기
            Sheet sheet = workbook.getSheet(SHEET_NAME);
            // 시트가 없는 경우 오류 발생
            if (sheet == null) {
                throw new IllegalArgumentException("Excel 파일에서 '"
                        + SHEET_NAME + "' 시트를 찾을 수 없습니다.");
            }
            // 숫자형 / 문자열형 셀을 문자열 형태로 읽기 위해 사용
            DataFormatter formatter = new DataFormatter();
            // Excel 데이터 한 행씩 확인
            // 0번 행은 제목이므로 1번 행부터 시작
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex); // 현재 행 가져오기
                // 빈 행이면 다음 행으로 이동
                if (row == null) {
                    continue;
                }
                // Excel 데이터 읽기, A열 : 학번
                String excelStudentNo = formatter.formatCellValue(row.getCell(0));
                // B열 : 과목
                String subject = formatter.formatCellValue(row.getCell(1));
                // C열 : 성적
                String scoreValue = formatter.formatCellValue(row.getCell(2));
                // 입력한 학번과 같은지 비교
                if (excelStudentNo.equals(studentNo)) {
                    // 성적을 문자열에서 정수로 변환
                    int score = Integer.parseInt(scoreValue);
                    // Score 객체 생성
                    Score scoreObject = new Score(excelStudentNo, subject, score);
                    // 검색 결과 List에 추가
                    scores.add(scoreObject);
                }
            }
        }
        return scores; // 해당 학생의 전체 성적 목록 반환
    }
}