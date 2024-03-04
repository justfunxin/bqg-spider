package source;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import parser.JsoupContentParser;

import java.util.Arrays;
import java.util.List;

/**
 * 笔趣阁  https://www.xbiquge.bz/
 */
public class Biquge extends JsoupContentParser {

    public Biquge() {
        super("笔趣阁", "www.xbiquge.bz");
    }

    @Override
    protected List<Element> getChapterElements(Document document) {
        return document.selectXpath("//div[@id='list']/dl/dt[2]/following-sibling::dd/a");
    }

    @Override
    protected List<String> getContents(Document document) {
        Element element = document.selectXpath("//div[@id='content']").first();
        return Arrays.asList(element.html().split("<br>"));
    }

    @Override
    protected long getSkipLines() {
        return 1;
    }
}
