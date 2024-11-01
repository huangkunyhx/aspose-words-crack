package ocr.v24_8;

import javassist.ClassPool;
import javassist.CtClass;

import java.io.InputStream;

/**
 * ocr.v24_8.AsposeOcr
 * <p>
 * Creation Time: 2024-10-31 14:50:09
 *
 * @author Kevin Huang
 * @since 0.0.1
 */
public class AsposeOcr {
    private static final String path = "./ocr/v24_8/";

    public static void main(String[] args) throws Exception {
        ClassPool.getDefault().insertClassPath(path + "aspose-ocr-24.8.0.jar");

        // 类文件1，从com.aspose.ocr.License类文件找到。
        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.words.n");
//        clazz.getDeclaredMethod("f", new CtClass[]{ClassPool.getDefault().getCtClass("java.io.InputStream")})
//        clazz.getDeclaredMethod("zzXj5").setBody("{return com.aspose.words.zzVSw.zzYzu;}");
//        clazz.getDeclaredMethod("f").setBody("{}");
//        clazz.getDeclaredMethod("f").setBody("{}");
//        clazz.writeFile();
//
//        // 类文件2，从类com.aspose.words.zzWst的方法zzmP()进。
//        CtClass clazz2 = ClassPool.getDefault().getCtClass("com.aspose.words.zzWTm");
//        clazz2.getDeclaredMethod("zzW8W").setBody("{System.out.println(\"do nothing. this.zzDB(); this.zzWub();\");}");
//        clazz2.getDeclaredMethod("zzZnt").setBody("{return com.aspose.words.zzWKI.zzW6T;}");
//        //clazz2.getDeclaredConstructors()[0].setBody("{System.out.println(\"do nothing at zzWS8.\");}");
//        clazz2.writeFile();
//
//        // 类文件3（关键点：删除水印）从类com.aspose.words.zzWTm的方法zzWub()进。
//        CtClass clazz3 = ClassPool.getDefault().getCtClass("com.aspose.words.zzXSe");
//        clazz3.getDeclaredMethod("zzXRF").setBody("{zzYPQ = 29273535023874148L;}");
//        clazz3.writeFile();
    }
}
