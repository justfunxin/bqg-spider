package exporter;

import bean.ChapterBuffer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TextExporter implements Exporter {

    @Override
    public void export(List<ChapterBuffer> chapters, String savePath) {
        savePath += ".txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(savePath))))) {
            chapters.forEach(chapter -> {
                try {
                    //格式化
                    //章节名+换行+空行
                    bufferedWriter.write(chapter.name);
                    bufferedWriter.write("\n\n");
                    for (String line : chapter.content) {
                        //4个空格+正文+换行+空行
                        bufferedWriter.write("    ");
                        bufferedWriter.write(line);
                        bufferedWriter.write("\n\n");
                    }
                    //章节结束空三行，用来分割下一章节
                    bufferedWriter.write("\n\n\n");
                } catch (IOException e) {
                    System.out.println("写入出错 ： " + chapter + ", " + e.getMessage());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("保存完成 ： " + savePath);
    }
}
