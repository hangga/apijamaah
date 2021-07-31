package id.muhtadien.apijamaah.models;

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

public class ExcelHelper {
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    //static String[] HEADERs = { "Id", "Title", "Description", "Published" };
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

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            jamaahEntity.setNama(currentCell.getStringCellValue() == null? "-":currentCell.getStringCellValue());
                            break;

                        case 1:
                            jamaahEntity.setSex(currentCell.getStringCellValue() == null? "-":currentCell.getStringCellValue());
                            break;

                        case 2:
                            try{
                                jamaahEntity.setAlamat(currentCell.getStringCellValue() == null? "-":currentCell.getStringCellValue());
                            }catch (Exception e){
                                jamaahEntity.setAlamat("");
                            }
                            break;

                        case 4:
                            try{
                                jamaahEntity.setStatus(String.valueOf(currentCell.getNumericCellValue()));
                            }catch (Exception e){
                                jamaahEntity.setStatus("");
                            }

                            break;

                        default:
                            break;
                    }

                    jamaahEntity.setSkill("");

                    cellIdx++;
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
