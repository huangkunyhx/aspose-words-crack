package pdf.v24_10;

import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.io.FileUtils;
import util.Files;

import java.io.File;
import java.util.Set;

/**
 * AsposePdf
 * <p>
 * Creation Time: 2024-12-19 17:13:26
 *
 * @author Kevin Huang
 * @since 1.
 */
public class AsposePdf {
    private static final String path = "./file/pdf/v24_10/";
    private static final String jar = "aspose-pdf-24.10-jdk17.jar";
    private static final String crackedJar = "aspose-pdf-24.10-jdk17-cracked.jar";

    public static void main(String[] args) throws Exception {
        Files.deleteFile(path, Set.of(jar + ".bak"));
        FileUtils.copyFile(new File(path + jar + ".bak"), new File(path + jar));

        ClassPool.getDefault().insertClassPath(path + jar); //注意路径

        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.pdf.ADocument");
        clazz.getDeclaredMethod("lI", new CtClass[]{clazz, CtClass.intType}).setBody("{return false;}");
        clazz.getDeclaredMethod("lt").setBody("{return true;}");
        clazz.writeFile(path);

        Files.updateJar(path, jar, crackedJar, Set.of(
                "META-INF/7DD91000.SF",
                "META-INF/7DD91000.RSA"
        ));
        Files.deleteFile(path, Set.of(jar + ".bak", crackedJar));
    }
}
