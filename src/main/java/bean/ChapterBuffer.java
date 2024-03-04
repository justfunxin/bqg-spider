package bean;

import lombok.Data;

import java.util.List;

/**
 * 每一章节的内容
 */
@Data
public class ChapterBuffer {
    public int number;//章节在小说中的顺序，最后排序需要用到
    public String name;//章节名字
    public List<String> content;//章节内容 按行分
}
