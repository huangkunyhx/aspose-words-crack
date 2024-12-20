package pdf;

import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlDocumentType;
import com.aspose.pdf.HtmlSaveOptions;
import com.aspose.pdf.SaveOptions;
import com.aspose.pdf.facades.PdfFileEditor;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * PDFTest
 * <p>
 * Creation Time: 2024-12-19 17:50:48
 *
 * @author Kevin Huang
 * @since 0.0.1
 */
public class PDFTest {
    public static void main(String[] args) throws Exception {
        String path = "./file/";
        if (null != args && args.length >= 1 && !args[0].trim().isEmpty()) {
            path = args[0].trim();
        }

        pdfToHtml(path + "word/out.pdf", path + "pdf/out.html");

        merge(List.of(path + "word/out.pdf", path + "word/out.pdf"), path + "pdf/out2.pdf");
    }

    public static void merge(List<String> files, String targetFile) {
        Document[] documents = files.stream()
                .map(Document::new)
                .toArray(Document[]::new);
        try (Document document = new Document()) {
            PdfFileEditor pdfFileEditor = new PdfFileEditor();
            pdfFileEditor.concatenate(documents, document);
            document.save(targetFile);
        }
    }

    public static void pdfToHtml(String sourceFile, String targetFile) throws Exception {
        long old = System.currentTimeMillis();
        System.out.println("开始转换");
        FileOutputStream os = new FileOutputStream(targetFile);
        HtmlSaveOptions options = new HtmlSaveOptions(HtmlDocumentType.Html5, true);
        options.setRasterImagesSavingMode(HtmlSaveOptions.RasterImagesSavingModes.AsEmbeddedPartsOfPngPageBackground);
        options.setFontSavingMode(HtmlSaveOptions.FontSavingModes.SaveInAllFormats);
        options.setPartsEmbeddingMode(HtmlSaveOptions.PartsEmbeddingModes.EmbedCssOnly);
        options.setSplitIntoPages(false);
        options.setCompressSvgGraphicsIfAny(false);
        options.setSaveShadowedTextsAsTransparentTexts(true);
        options.setConvertMarkedContentToLayers(true);
        options.setUseZOrder(true);
        options.setHtmlMarkupGenerationMode(HtmlSaveOptions.HtmlMarkupGenerationModes.WriteAllHtml);
        options.setSaveTransparentTexts(true);
        options.setSplitCssIntoPages(false);
        options.setCustomResourceSavingStrategy(new HtmlSaveOptions.ResourceSavingStrategy() {
            @Override
            public String invoke(SaveOptions.ResourceSavingInfo resourceSavingInfo) {
                // 实现自定义的资源保存逻辑
                String resourceUri = resourceSavingInfo.getSupposedFileName();
                String path = "./file/pdf/image/" + resourceSavingInfo.getSupposedFileName();
                try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                    fileOutputStream.write(resourceSavingInfo.getContentStream());
                    fileOutputStream.flush();
                    long now = System.currentTimeMillis();
                    System.out.println("保存文件" + "共耗时：" + ((now - old) / 1000.0) + "秒" + "  " + path);
                } catch (Exception ignored) {
                }
                return path;
            }
        });

        //这里保存的是Html资源
        options.setCustomHtmlSavingStrategy(new HtmlSaveOptions.HtmlPageMarkupSavingStrategy() {
            @Override
            public void invoke(HtmlSaveOptions.HtmlPageMarkupSavingInfo htmlPageMarkupSavingInfo) {
                long now = System.currentTimeMillis();
                System.out.println("invoke 共耗时：" + ((now - old) / 1000.0) + "秒" + "  " + htmlPageMarkupSavingInfo.getSupposedFileName());  //转化用时
                String path = "./file/pdf/html/" + htmlPageMarkupSavingInfo.getSupposedFileName();
                try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
                    InputStream inputStream = htmlPageMarkupSavingInfo.getContentStream();
                    byte[] buffer = new byte[4096]; // 创建缓冲区
                    int bytesRead;

                    // 将 InputStream 的内容写入 FileOutputStream
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                } catch (Exception ignored) {
                }
            }
        });

        com.aspose.pdf.Document doc = new com.aspose.pdf.Document(sourceFile);//加载源文件数据
        doc.save(os, options);//设置转换文件类型并转换
        os.close();
        long now = System.currentTimeMillis();
        System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
    }
}
