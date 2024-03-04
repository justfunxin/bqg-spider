package exporter;

import bean.ChapterBuffer;

import java.util.List;

public interface Exporter {

    void export(List<ChapterBuffer> chapters, String savePath);

}
