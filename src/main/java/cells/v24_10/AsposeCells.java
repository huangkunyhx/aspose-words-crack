package cells.v24_10;

import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.io.FileUtils;
import util.Files;

import java.io.File;
import java.util.Set;

/**
 * AsposeCells
 * <p>
 * Creation Time: 2024-12-20 16:33:36
 *
 * @author Kevin Huang
 * @since 0.0.1
 */
public class AsposeCells {
    private static final String path = "./file/excel/v24_10/";
    private static final String jar = "aspose-cells-24.10.jar";
    private static final String crackedJar = "aspose-cells-24.10-cracked.jar";

    public static void main(String[] args) throws Exception {
        Files.deleteFile(path, Set.of(jar + ".bak"));
        FileUtils.copyFile(new File(path + jar + ".bak"), new File(path + jar));

        ClassPool.getDefault().insertClassPath(path + jar);

        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.cells.License");
        clazz.getMethod("setLicense", "(Ljava/lang/String;)V")
                .setBody("{ org.w3c.dom.Document document = null;com.aspose.cells.u1y u1y = new com.aspose.cells.u1y();u1y.a(document); }");
        clazz.writeFile(path);

        CtClass u1y = ClassPool.getDefault().get("com.aspose.cells.u1y");
        u1y.getMethod("a", "(Ljava/lang/String;)I").setBody("{ return 1; }");
        u1y.getMethod("a", "(Lorg/w3c/dom/Document;)V").setBody("{ com.aspose.cells.License.a = \"123\";a = this;com.aspose.cells.w0i.a(); }");
        u1y.writeFile(path);

        Files.updateJar(path, jar, crackedJar, Set.of(
                "META-INF/7DD91000.SF",
                "META-INF/7DD91000.RSA"
        ));
        Files.deleteFile(path, Set.of(jar + ".bak", crackedJar));
    }
}
