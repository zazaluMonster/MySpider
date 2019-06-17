package Processor;

import java.io.File;
import java.util.List;

/**
 * 爬虫的处理核心，专门对爬取数据进行提炼，常用技术正则表达式
 *
 * 一般我们会将解析数据用两种方式暂时存储，一种是直接存在内存中也就是使用Connection集合存着，还有一种是导出到临时文件中
 */
public interface Processor<E> {


    File parseToFile(File downloadFile) throws Exception;

    List<E> parseToList(File downloadFile) throws Exception;

}
