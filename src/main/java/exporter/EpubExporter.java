package exporter;

import bean.ChapterBuffer;
import util.FoxEpubWriter;

import java.io.File;
import java.util.List;

public class EpubExporter implements Exporter {
    @Override
    public void export(List<ChapterBuffer> chapters, String savePath) {
        savePath += ".epub";
        String bookName = savePath.substring(savePath.lastIndexOf("/") + 1);
        FoxEpubWriter foxEpubWriter = new FoxEpubWriter(new File(savePath), bookName);
        foxEpubWriter.setEpub(true);

        chapters.forEach(chapterBuffer -> {
            StringBuilder content = new StringBuilder();
            for (String line : chapterBuffer.content) {
                content.append("<p>");
                content.append("    ");
                content.append(line);
                content.append("</p>");
            }
            foxEpubWriter.addChapter(chapterBuffer.name, content.toString());
        });
        foxEpubWriter.saveAll();
        System.out.println("保存成功 : " + savePath);
    }
}
