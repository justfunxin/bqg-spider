package parser;

import bean.Chapter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;
import java.util.stream.Collectors;

public abstract class JsoupContentParser extends HtmlContentParser {

    private Set<String> trimWordSet;

    public JsoupContentParser(String... trimWords) {
        trimWordSet = new HashSet<>(Arrays.asList(trimWords));
    }

    @Override
    protected List<Chapter> parseChapters(String catalogUrl, String catalogHTML) {
        Document document = Jsoup.parse(catalogHTML);
        List<Chapter> chapters = new ArrayList<>();
        for (Element element : getChapterElements(document)) {
            Chapter chapter = new Chapter();
            chapter.name = element.text();
            chapter.href = getChapterUrl(catalogUrl, element);
            chapters.add(chapter);
        }
        return chapters;
    }

    protected String getChapterUrl(String catalogUrl, Element element) {
        return catalogUrl + element.attr("href");
    }

    @Override
    protected List<String> parseChapterContent(Chapter chapter, String html) {
        Document document = Jsoup.parse(html);
        return getContents(document).stream()
                .skip(getSkipLines())
                .map(x -> cleanContent(chapter, x, trimWordSet))
                .filter(s -> s != null && !s.trim().isEmpty())
                .collect(Collectors.toList());
    }

    protected abstract List<Element> getChapterElements(Document document);

    protected long getSkipLines() {
        return 0;
    }

    protected abstract List<String> getContents(Document document);
}
