package cells;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

/**
 * ExcelTest
 * <p>
 * Creation Time: 2024-12-20 15:52:48
 *
 * @author Kevin Huang
 * @since 0.0.1
 */
public class ExcelTest {

    public static void main(String[] args) throws Exception {
        Workbook wb = new Workbook();
        Worksheet ws = wb.getWorksheets().get(0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ws.getCells().get(i, j).setValue((i + 1) + "*" + (j + 1) + "=" + (i + 1) * (j + 1));
            }
        }
        wb.save("./file/excel/text.xlsx");
    }
}
