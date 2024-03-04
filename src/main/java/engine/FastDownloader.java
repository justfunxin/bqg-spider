package engine;

import bean.ChapterBuffer;
import exporter.EpubExporter;
import exporter.Exporter;
import exporter.TextExporter;
import lombok.Setter;
import parser.ContentParser;
import source.Biquge;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 快速下载指定格式小说的框架
 */
public class FastDownloader {

    @Setter
    private int threadCount = 300;

    private String saveFolder;

    private static final Map<String, ContentParser> ParserMap = new HashMap<>();
    private static final Map<String, Exporter> ExportMap = new HashMap<>();

    static {
        ParserMap.put("www.xbiquge.bz", new Biquge());

        ExportMap.put("txt", new TextExporter());
        ExportMap.put("epub", new EpubExporter());
    }

    public FastDownloader(String saveFolder) {
        this.saveFolder = saveFolder;
        File file = new File(saveFolder);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                System.out.println("保存目录创建失败");
            }
        }
    }

    public void download(String bookName, String catalogUrl, String format) {
        try {
            String catalogHost = new URL(catalogUrl).getHost();
            ContentParser parser = ParserMap.get(catalogHost);
            if (parser == null) {
                throw new RuntimeException("不支持该网站:" + catalogHost);
            }
            Exporter exporter = ExportMap.get(format);
            if (exporter == null) {
                throw new RuntimeException("不支持的输出类型:" + format);
            }
            Downloader downloader = new Downloader(catalogUrl, parser, threadCount);
            List<ChapterBuffer> chapters = downloader.download();
            String filePath = saveFolder + File.separator + bookName;
            exporter.export(chapters, filePath);
        } catch (Exception e) {
            System.out.println("下载失败：" + e.getMessage());
        }
    }

}
