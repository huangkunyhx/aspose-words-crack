package ocr;

import com.aspose.ocr.*;

import java.util.ArrayList;

/**
 * ImageTest
 * <p>
 * Creation Time: 2024-11-01 10:22:54
 *
 * @author Kevin Huang
 * @since 0.0.1
 */
public class ImageTest {
    public static void main(String[] args) throws AsposeOCRException {
        Resources.FetchResource("./file/ocr/aspose-ocr-chinese-v1.ocr");

        // Create instance of OCR API
        AsposeOCR api = new AsposeOCR();

        OcrInput images  = new OcrInput(InputType.SingleImage);
        images.add("./file/ocr/1.png");
        // Recognize images
        RecognitionSettings settings = new RecognitionSettings();
        settings.setLanguage(Language.Chi);
        settings.setLinesFiltration(true);
        settings.setDetectAreasMode(DetectAreasMode.PHOTO);
        settings.setAllowedCharacters(CharactersAllowedType.ALL);
        ArrayList<RecognitionResult> results = api.Recognize(images, settings);
        System.out.println(results.get(0).recognitionText);
    }
}
