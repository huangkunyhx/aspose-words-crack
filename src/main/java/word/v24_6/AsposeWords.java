package word.v24_6;

import javassist.ClassPool;
import javassist.CtClass;

public class AsposeWords {
    private static final String path = "./word/v24_6/";

    public static void main(String[] args) throws Exception {
        ClassPool.getDefault().insertClassPath(path + "aspose-words-24.6-jdk17.jar"); //注意路径
        // 类文件1，从com.aspose.words.License类文件找到。
        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.words.zzWst");
        clazz.getDeclaredMethod("zzXj5").setBody("{return com.aspose.words.zzVSw.zzYzu;}");
        clazz.getDeclaredMethod("zz5D").setBody("{}");
        clazz.getDeclaredMethod("zz6t").setBody("{}");
        clazz.writeFile(path);

        // 类文件2，从类com.aspose.words.zzWst的方法zzmP()进。
        CtClass clazz2 = ClassPool.getDefault().getCtClass("com.aspose.words.zzWTm");
        clazz2.getDeclaredMethod("zzW8W").setBody("{System.out.println(\"do nothing. this.zzDB(); this.zzWub();\");}");
        clazz2.getDeclaredMethod("zzZnt").setBody("{return com.aspose.words.zzWKI.zzW6T;}");
        //clazz2.getDeclaredConstructors()[0].setBody("{System.out.println(\"do nothing at zzWS8.\");}");
        clazz2.writeFile(path);

        // 类文件3（关键点：删除水印）从类com.aspose.words.zzWS8W的方法zzW5m()进，大概是665行。
        CtClass clazz3 = ClassPool.getDefault().getCtClass("com.aspose.words.zzXSe");
        clazz3.getDeclaredMethod("zzXRF").setBody("{zzYPQ = 29273535023874148L;}");
        clazz3.writeFile(path);
    }
}