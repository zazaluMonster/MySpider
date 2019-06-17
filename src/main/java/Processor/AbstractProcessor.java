package Processor;

import java.io.File;
import java.util.List;

public abstract class AbstractProcessor<E> implements Processor<E> {
    protected String name;

    @Override
    public File parseToFile(File downloadFile) throws Exception {
        throw new Exception("不支持解析数据至新文件");
    }

    @Override
    public List<E> parseToList(File downloadFile) throws Exception {
        throw new Exception("不支持解析数据至Java集合");
    }

}
