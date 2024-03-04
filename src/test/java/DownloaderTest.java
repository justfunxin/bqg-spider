import engine.FastDownloader;
import exporter.Exporter;
import exporter.TextExporter;
import org.junit.Before;
import org.junit.Test;
import source.Biquge;

public class DownloaderTest {

    private FastDownloader fastDownloader;

    @Before
    public void setUp() {
        fastDownloader = new FastDownloader("D:/book");
    }

    @Test
    public void test1() {
        fastDownloader.download("斗破苍穹之无上之境", "https://www.xbiquge.bz/book/109/", "txt");
    }

    @Test
    public void test12() {
        fastDownloader.download("万相之王", "https://www.xbiquge.bz/book/53099/", "epub");
    }
}
