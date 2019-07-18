# Introduction

MySpider是使用Java语言实现的网络爬虫项目，它本来是我的一个个人爬虫练习项目，但是由于爬虫需求丰富，针对每次爬虫需求总感觉让我在重写整个项目的感觉，故为了提高开发效率，我开发了MySpider

# What Component does MySpider have?

1. Boot，用于MySpider的初始化配置
2. ScheduleQueue，需爬取的URL链接队列
3. Downloader，用于处理各类网络下载需求，保存源数据至本地
4. HttpHeadParser，用于处理HTTP报文头数据
5. Processor，用于处理源数据，爬取我们需要的信息，保存至结果文件内
6. DataService，提供数据持久化的服务，最基本的为数据的crud接口（默认采用mybatisDataService来使用mybatis进行数据库操作，你可以编写自定义的DataService来使用别的你想用的持久层框架）
7. DataObject，存放POJO类的地方
8. DatabaseAssist，若使用了数据库作为数据持久化工具，则必须将所有为了连接数据库而产生的相关辅助类全部放在DatabaseAssist包内，比如使用了mybatis，则建立mybatis子包，将mapper类和mapper.xml文件全部放置在这里。
8. Constants，运行时常量库
9. MySpider，一个MySpider代表一个网络爬虫的对象，可以调用其他组件的功能
10. MyLogger，封装第三方日志文件的调用接口，使得替换日志文件框架不会对项目本体代码造成任何影响（无痛替换）

 
 * UI，java GUI框架由于业界不火，故本程序暂无UI，后续会接入至个人Web项目中，到那时给予一个web ui界面
 * 除了Boot，DataObject，其他组件可以根据既定接口实现自定义组件
 * ~/resources，本地资源文件夹
 
 # What's in the resource folder
 
 1. properties，存储各类配置文件
 2. download，存储Download下载完毕的源数据
 3. ProcessorTmp，存储ProcessorTmp爬取的数据的临时文件
 4. sql，存放sql文件
 
 # What can you learn from your MySpider
 
 1. 如果你在寻找一个用于练手的初级Java爬虫项目，那么从MySpider开始吧! MySpider蕴含了爬虫应用的精髓，麻雀虽小五脏俱全
 2. Java网络编程在爬虫中的应用
 3. Java多线程开发在爬虫中的应用
 4. maven构建工具的基本使用
 5. ...balabala
 
 # How To Start
 
 我没有提供一个完备的jar文件，因为MySpider的具体功能可以由你自己实现。
 在MySpider中，我已经提供了一个完整的Demo程序（爬取中国青年网数据，一次爬取5页）
 你可以在使用eclipse或者idea使用maven项目选项进行导入，使用maven的compile指令编译本项目
 随后执行Main类的main方法，即可运行我提供的这个Demo程序
 
 [其他注意事项] 
 1. useThreads = true 则使用多线程启动
 2. 为MySpider添加DataService组件则会自动将数据保存至数据库而不是存至临时文件（注意先配置数据库，mybatis-config.xml）
 3. 若使用DataService，请确保数据库连接的线程安全，推荐使用ThreadLocal实现，我本人采用了限定连接对象在method scope内，因为其简单且满足目前需求
 
 [整合到Spring项目]
1. 我今天尝试将MySpider整合到了自己的Spring项目中去,发现dataService那一层组件很难整合到自己的Spring项目中，有非常高的耦合度，后续会剔除此模块，使得MySpider只提供纯粹的数据爬取，而不在乎数据持久化。
2. 数据持久化应该是使用了MySpider去爬取数据的具体项目本身的任务，而非MySpider的任务，所以我会在后续为MySpider提供一个全新的数据接口，可以在每次爬取完指定信息后，通过调用该接口可以获取到信息的集合list或者map
 
 [将MySpider整合到Spring项目中的demo项目,内容也很基础，欢迎新人star](https://github.com/zazaluMonster/MyBelfast) 
 
 
 # Join us
 
 所有组件可以自定义，所以欢迎你使用MySpider来完成自己的需求
 
 自定义的类请放在custom文件夹内，保持整洁
 
 # At last
 
 喜欢请star!(star多说不定会有后续更新哦...)
 
 
# LICENSE

[MIT](https://github.com/zazaluMonster/MySpider/blob/master/LICENSE)
