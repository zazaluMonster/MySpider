package Boot;

import java.net.URL;

/**
 * 用于初始化数据
 */
public interface Boot {


    void beforeBoot();

    void boot();

    void afterBoot();

}
