package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileUtil {

    public static void writeText(String iUtf8Str, String filePath) {
        writeText(iUtf8Str, filePath, "UTF-8");
    }

    // 写入指定编码，速度快点
    public static void writeText(String iStr, String filePath, String oFileEncoding) {
        boolean bAppend = false;
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, bAppend), oFileEncoding));
            bw.write(iStr);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list(); // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                if (!deleteDir(new File(dir, children[i]))) {
                    return false;
                }
            }
        } // 目录此时为空，可以删除
        boolean bDeleted = false;
        try {
            bDeleted = dir.delete();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return bDeleted;
    }
}
