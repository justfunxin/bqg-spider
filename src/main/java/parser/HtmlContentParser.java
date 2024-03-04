package parser;

import bean.Chapter;
import bean.ChapterBuffer;
import util.NetUtil;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public abstract class HtmlContentParser implements ContentParser {

    @Override
    public List<Chapter> getChapters(String catalogUrl) throws IOException {
        String catalogHTML = NetUtil.getHtml(catalogUrl);
        return parseChapters(catalogUrl, catalogHTML);
    }

    protected abstract List<Chapter> parseChapters(String catalogUrl, String catalogHTML);

    @Override
    public ChapterBuffer adaptBookBuffer(Chapter chapter, int num) throws IOException {
        String html = NetUtil.getHtml(chapter.getHref());
        ChapterBuffer chapterBuffer = new ChapterBuffer();
        chapterBuffer.content = parseChapterContent(chapter, html);
        chapterBuffer.name = chapter.name;
        chapterBuffer.number = num;
        return chapterBuffer;
    }

    protected abstract List<String> parseChapterContent(Chapter chapter, String html);

    protected String cleanHtmlContent(String content) {
        return content.replaceAll("\n|\t|\r|&nbsp;|<br>|<br/>|<br/>|<p>|</p>|&gt;|&gt;", "").trim();
    }

    /**
     * 预留的一个清除乱码或者html格式的方法
     */
    protected String cleanContent(Chapter chapter, String content, Set<String> trimWords) {
        content = cleanHtmlContent(content);
        if (content == null) {
            return "";
        }
        if (equalsIgnoreWhitespace(chapter.name, content)) {
            return null;
        }
        if (trimWords != null && !trimWords.isEmpty()) {
            StringBuilder regexBuilder = new StringBuilder();
            for (String trimWord : trimWords) {
                // 保证正则表达式的正确性，避免特殊字符的影响
                regexBuilder.append(Pattern.quote(trimWord)).append("|");
            }
            // 移除最后一个 |
            regexBuilder.deleteCharAt(regexBuilder.length() - 1);
            String regex = "\\b(" + regexBuilder.toString() + ")\\b";
            content = content.replaceAll(regex, "");
        }
        return content;
    }

    public boolean equalsIgnoreWhitespace(String content1, String content2) {
        return content1.replaceAll("\\s+", "").equals(content2.replaceAll("\\s+", ""));
    }
}
