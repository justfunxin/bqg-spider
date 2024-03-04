package bean;

import lombok.Data;

/**
 * 从目录页解析出来的章节名以及章节链接
 */
@Data
public class Chapter {
    public String name;
    public String href;
    public int num = -1;
}
