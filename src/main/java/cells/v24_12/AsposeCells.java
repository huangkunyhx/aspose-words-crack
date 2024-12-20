package cells.v24_12;

import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.io.FileUtils;
import util.Files;

import java.io.File;
import java.util.Set;

/**
 * AsposeCells
 * <p>
 * Creation Time: 2024-12-20 15:57:12
 *
 * @author Kevin Huang
 * @since 0.0.1
 */
public class AsposeCells {
    private static final String path = "./file/excel/v24_12/";
    private static final String jar = "aspose-cells-24.12.jar";
    private static final String crackedJar = "aspose-cells-24.12-cracked.jar";

    public static void main(String[] args) throws Exception {
        Files.deleteFile(path, Set.of(jar + ".bak"));
        FileUtils.copyFile(new File(path + jar + ".bak"), new File(path + jar));

        ClassPool.getDefault().insertClassPath(path + jar);

        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.cells.License");
        clazz.getMethod("setLicense", "(Ljava/lang/String;)V")
                .setBody("{ org.w3c.dom.Document document = null;com.aspose.cells.u7i u7i = new com.aspose.cells.u7i();u7i.a(document); }");
        clazz.writeFile(path);

        CtClass u7i = ClassPool.getDefault().get("com.aspose.cells.u7i");
        u7i.getMethod("a", "(Ljava/lang/String;)I").setBody("{ return 1; }");
        u7i.getMethod("a", "(Lorg/w3c/dom/Document;)V").setBody("{ com.aspose.cells.License.a = \"123\";a = this;com.aspose.cells.y2d.f(-1);com.aspose.cells.a04.a(); }");
        u7i.writeFile(path);

        Files.updateJar(path, jar, crackedJar, Set.of(
                "META-INF/7DD91000.SF",
                "META-INF/7DD91000.RSA"
        ));
        Files.deleteFile(path, Set.of(jar + ".bak", crackedJar));
    }
}
