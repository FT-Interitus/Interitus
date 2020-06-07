package de.ft.interitus.data.user;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void zipFiles(String ziparchiv,String... srcFiles) throws IOException {
        //List<String> srcFiles = Arrays.asList("test1.txt", "test2.txt");
        FileOutputStream fos = new FileOutputStream(ziparchiv);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }
    public static void zipFiles(String ziparchiv,File... srcFiles) throws IOException {
        //List<String> srcFiles = Arrays.asList("test1.txt", "test2.txt");
        FileOutputStream fos = new FileOutputStream(ziparchiv);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (File srcFile : srcFiles) {
            File fileToZip = srcFile;
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }
}
