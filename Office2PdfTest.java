import com.aspose.words.Document;
import com.aspose.words.FontSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Office2PdfTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        File file = new File("/Users/kevin/Desktop/1.pdf");

        try (FileOutputStream os = new FileOutputStream(file);
             InputStream fis = new FileInputStream("template.docx")) {
            Document doc = new Document(fis);

            FontSettings fontSettings = new FontSettings();
            fontSettings.setFontsFolder("/Users/kevin/Desktop/管局投诉", false);
            doc.setFontSettings(fontSettings);

            doc.save(os, com.aspose.words.SaveFormat.PDF);
            doc.cleanup();
            os.flush();
            System.out.println("convert cost " + (System.currentTimeMillis() - start) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
