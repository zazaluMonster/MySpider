# Introduction

MySpider是使用Java语言实现的网络爬虫项目，它本来是我的一个个人爬虫练习项目，但是由于爬虫需求丰富，针对每次爬虫需求总感觉让我在重写整个项目的感觉，故为了提高开发效率，我开发了MySpider

# What Component does MySpider have?

1. Boot，用于MySpider的初始化配置
2. ScheduleQueue，需爬取的URL链接队列
3. Downloader，用于处理各类网络下载需求，保存源数据至本地
4. HttpHeadParser，用于处理HTTP报文头数据
5. Processor，用于处理源数据，爬取我们需要的信息，保存至结果文件内
6. DataStorage，将爬取数据持久化（比如保存至mysql，mongodb，ElasticSearch）
7. MySpiderStart，程序入口
8. Constants，运行时常量库
9. MyLogger，封装第三方日志文件的调用接口，使得替换日志文件框架不会对项目本体代码造成任何影响（无痛替换）
10. DataObject，爬取的数据其抽象成的Java对象

 
 * UI，java GUI框架由于业界不火，故本程序暂无UI，后续会接入至个人Web项目中，到那时给予一个web ui界面
 * 除了Boot，DataObject，其他组件可以根据既定接口实现自定义组件
 * ~/resources，本地资源文件夹
 
 # What's in the resource folder
 
 1. properties，存储各类配置文件
 2. download，存储Download下载完毕的源数据
 
 # What can you learn from your MySpider
 
 1. 如果你在寻找一个用于练手的初级Java爬虫项目，那么从MySpider开始吧! MySpider蕴含了爬虫应用的精髓，麻雀虽小五脏俱全
 2. Java网络编程在爬虫中的应用
 3. Java多线程开发在爬虫中的应用
 4. maven构建工具的基本使用
 5. ...balabala
 
 # How To Start
 
 执行Main类的man方法
 
 # Join us
 
 所有组件可以自定义，所以欢迎你使用MySpider来完成自己的需求
 
 # At last
 
 喜欢请star!(star多说不定会有后续更新哦...)
