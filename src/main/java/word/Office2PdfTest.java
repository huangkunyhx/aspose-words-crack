package word;

import com.aspose.words.Document;
import com.aspose.words.FontSettings;

import java.io.*;

public class Office2PdfTest {
    public static void main(String[] args) throws Exception {
        String path = "./file/word/";
        if (null != args && args.length >= 1 && !args[0].trim().isEmpty()) {
            path = args[0].trim();
        }

        long start = System.currentTimeMillis();
        File file = new File(path + "out.pdf");

        try (FileOutputStream os = new FileOutputStream(file);
             InputStream fis = new FileInputStream(path + "template.docx")) {
            Document doc = new Document(fis);

            FontSettings fontSettings = new FontSettings();
            fontSettings.setFontsFolder(path + "fonts", false);
            doc.setFontSettings(fontSettings);

            doc.save(os, com.aspose.words.SaveFormat.PDF);
            doc.cleanup();
            os.flush();
            System.out.println("convert cost " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
