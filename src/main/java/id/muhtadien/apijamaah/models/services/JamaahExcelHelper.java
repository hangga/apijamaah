package id.muhtadien.apijamaah.models.services;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JamaahExcelHelper {
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static final String SHEET = "Sheet1"; // <-- Sheet Name

    public static boolean isExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<JamaahEntity> excelToJamaahEntitys(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<JamaahEntity> JamaahEntitys = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                // Entitas/ tabel
                JamaahEntity jamaahEntity = new JamaahEntity();

                int columnIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCellInRow = cellsInRow.next();
                    switch (columnIndex) {
                        case 0:
                            jamaahEntity.setNama(currentCellInRow.getStringCellValue());
                            break;

                        case 1:
                            jamaahEntity.setSex(currentCellInRow.getStringCellValue());
                            break;

                        case 2:
                            jamaahEntity.setAlamat(currentCellInRow.getStringCellValue());
                            break;

                        case 4:
                            jamaahEntity.setStatus(String.valueOf(currentCellInRow.getNumericCellValue()));
                            break;

                        default:
                            break;
                    }

                    jamaahEntity.setSkill("");

                    columnIndex++;
                }

                JamaahEntitys.add(jamaahEntity);
            }

            workbook.close();

            return JamaahEntitys;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
