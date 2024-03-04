# 笔趣阁爬虫

## 解析器：
- 解析[笔趣阁](https://www.xbiquge.bz)
- 自定义解析器：实现`ContentParser`接口，并注册到`FastDownloader`中

## 导出器：
- 导出txt格式：`TxtExporter`
- 导出Epub格式：`EpubExporter`
- 自定义导出格式：实现`Exporter`接口，并注册到`FastDownloader`中


## 使用方法：
```java
@Test
public void test() {
    FastDownloader fastDownloader = new FastDownloader("D:/book");
    fastDownloader.download("万相之王", "https://www.xbiquge.bz/book/53099/", "txt");
}
```
