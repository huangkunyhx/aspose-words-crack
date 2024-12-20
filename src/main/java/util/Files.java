package util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;

/**
 * util.Files
 * <p>
 * Creation Time: 2024-12-20 09:50:30
 *
 * @author Kevin Huang
 * @since 0.0.1
 */
public class Files {

    public static void deleteFile(String parentDir, Set<String> excludes) throws IOException {
        File[] files = new File(parentDir).listFiles(pathname -> !excludes.contains(pathname.getName()));
        if (null != files) {
            for (File file : files) {
                if (file.isFile()) {
                    FileUtils.delete(file);
                } else if (file.isDirectory()) {
                    FileUtils.deleteDirectory(file);
                }
            }
        }
    }

    public static void updateJar(String path, String jar, String crackedJar, Set<String> deleteFiles) throws Exception {
        Set<String> updateFiles = FileUtils.listFiles(new File(path), new String[]{"class"}, true)
                .stream()
                .map(File::getPath)
                .map(name -> name.replaceAll(path, ""))
                .collect(Collectors.toSet());

        JarFile jarFile = new JarFile(path + jar);
        try (JarOutputStream jos = new JarOutputStream(new FileOutputStream(path + crackedJar))) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (deleteFiles.contains(entry.getName())) {
                    System.out.println("Delete:-------" + entry.getName());
                } else if (updateFiles.contains(entry.getName())) {
                    System.out.println("Replace:-------" + entry.getName());
                    JarEntry jarEntry = new JarEntry(entry.getName());
                    jos.putNextEntry(jarEntry);
                    byte[] bytes = FileUtils.readFileToByteArray(new File(path + entry.getName()));
                    jos.write(bytes, 0, bytes.length);
                } else {
                    jos.putNextEntry(entry);
                    byte[] bytes = IOUtils.toByteArray(jarFile.getInputStream(entry));
                    jos.write(bytes, 0, bytes.length);
                }
            }
            jos.flush();
        }
        jarFile.close();
    }
}
