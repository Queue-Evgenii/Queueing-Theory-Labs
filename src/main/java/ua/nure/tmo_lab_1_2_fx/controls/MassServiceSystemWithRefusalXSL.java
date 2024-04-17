package ua.nure.tmo_lab_1_2_fx.controls;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import ua.nure.tmo_lab_1_2_fx.service.MassServiceSystemWithRefusal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class MassServiceSystemWithRefusalXSL {
    public MassServiceSystemWithRefusalXSL(MassServiceSystemWithRefusal sys) {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Лист 1");

        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("r");
        row.createCell(1).setCellValue("z");
        row.createCell(2).setCellValue("e");
        row.createCell(3).setCellValue("t пост");
        row.createCell(4).setCellValue("T звіл");
        row.createCell(5).setCellValue("Номер каналу");

        float[] seq = sys.getFlow().getInitialSequence();
        float[] seqZ = sys.getFlow().getZSequence();
        float[] seqT = sys.getFlow().getTSequence();
        float[] times = sys.getWaitingTimes();
        Map<Float, Integer> map = sys.getServiceNodeOverload();

        for (int i = 0; i < seqT.length - 1; ++i) {
            Row dataRow = sheet.createRow(i + 1);

            dataRow.createCell(0).setCellValue(seq[i]);
            dataRow.createCell(1).setCellValue(seqZ[i]);
            dataRow.createCell(2).setCellValue(times[i] != -1 ? times[i] : 0);
            dataRow.createCell(3).setCellValue(seqT[i]);
            dataRow.createCell(4).setCellValue(times[i] != -1 ? seqT[i] + times[i] : 0);
            dataRow.createCell(5).setCellValue(times[i] != -1 ? map.get(seqT[i] + times[i]) : 0);
        }

        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream outputStream = new FileOutputStream("result.xls")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
