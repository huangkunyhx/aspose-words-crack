package word.v24_9;

import javassist.ClassPool;
import javassist.CtClass;

public class AsposeWords {
    private static final String path = "./word/v24_9/";

    public static void main(String[] args) throws Exception {
        ClassPool.getDefault().insertClassPath(path + "aspose-words-24.9-jdk17.jar"); //注意路径
        // 类文件1，从com.aspose.words.License类文件找到。
        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.words.zz71");
        clazz.getDeclaredMethod("zzkP").setBody("{return com.aspose.words.zzX1H.zzrZ;}");
        clazz.getDeclaredMethod("zzZbU").setBody("{}");
        clazz.getDeclaredMethod("zzYV0").setBody("{}");
        clazz.writeFile(path);

        // 类文件2，从类com.aspose.words.zz71的方法zzmP()进。
        CtClass clazz2 = ClassPool.getDefault().getCtClass("com.aspose.words.zzZ3X");
        clazz2.getDeclaredMethod("zzYV3").setBody("{System.out.println(\"do nothing. this.zzuz(); this.zzXm9();\");}");
        clazz2.getDeclaredMethod("zzX4p").setBody("{return com.aspose.words.zzXAO.zzqX;}");
        //clazz2.getDeclaredConstructors()[0].setBody("{System.out.println(\"do nothing at zzWS8.\");}");
        clazz2.writeFile(path);

        // 类文件3（关键点：删除水印）从类com.aspose.words.zzZ3X的方法zzXm9()进
        CtClass clazz3 = ClassPool.getDefault().getCtClass("com.aspose.words.zzYu2");
        clazz3.getDeclaredMethod("zzVUS").setBody("{zzYr9 = 29273535023874148L;}");
        clazz3.writeFile(path);
    }
}