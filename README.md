# IntelliJ IDEA 插件开发

> 你好，我是小傅哥，[《重学Java设计模式》](https://item.jd.com/13218336.html) 图书作者，一线互联网 Java 工程师、架构师。[:pencil2: 虫洞栈，博主](https://bugstack.cn)，[:memo: 关于我](https://bugstack.cn/md/other/guide-to-reading.html) 

<br/>
<div align="center">
    <a href="https://bugstack.cn" style="text-decoration:none"><img src="https://bugstack.cn/images/system/CodeGuide-LOGO.png?raw=true" width="256px"></a>
</div>
<br/>

<div align="center">
	<a href="https://bugstack.cn/md/other/guide-to-reading.html"><img src="https://bugstack.cn/images/system/CodeGuide-Read.svg"></a>
	<a href="https://bugstack.cn/images/personal/qrcode.png"><img src="https://bugstack.cn/images/system/CodeGuide-WeiXinCode.svg"></a>
	<a href="https://bugstack.cn/md/knowledge/pdf/2021-01-26-Java%E9%9D%A2%E7%BB%8F%E6%89%8B%E5%86%8CPDF%E4%B8%8B%E8%BD%BD.html"><img src="https://bugstack.cn/images/system/CodeGuide-JavaPDF.svg"></a>
	<a href="https://mp.weixin.qq.com/s/VthCUlT8oAJqKOoq5_NzSQ"><img src="https://bugstack.cn/images/system/CodeGuide-Lottery.svg"></a>
	<a href="https://github.com/fuzhengwei/CodeGuide"><img src="https://badgen.net/github/stars/fuzhengwei/CodeGuide?icon=github&color=4ab8a1"></a>
</div>

<br/>
<div align="center">
    <table>
        <tr>
            <td align=“center">添加微信：fustack</td>
            <td align=“center">关注公众号：bugstack虫洞栈</td>
        </tr>
        <tr>
            <td align=“center"><img src="https://bugstack.cn/images/personal/fustack.png" width="128px"></td>
            <td align=“center"><img src="https://bugstack.cn/images/personal/qrcode.png" width="128px"></td>
        </tr>
    </table>
</div>
<br/>  

## ⛳ **目录**

- [学习说明]()
- [章节目录]()
- [章节源码]()
- [问题交流]()

## :bookmark: 学习说明

`我又要冲IDEA插件开发了！`

![](https://bugstack.cn/assets/images/middleware/middleware-5-2.png)

- 开发方式：在官网的描述中，创建IDEA插件工程的方式有两种分别是，IntelliJ Platform Plugin 模版创建和 Gradle 构建方式。
- 框架入口：一个 IDEA 插件开发完，要考虑把它嵌入到哪，比如是从 IDEA 窗体的 Edit、Tools 等进入配置还是把窗体嵌入到左、右工具条还是IDEA窗体下的对话框。
- UI：思考的是窗体需要用到什么语言开发，没错，用的就是 Swing、Awt 的技术能力。
- API：在 IDEA 插件开发中，一般都是围绕工程进行的，那么基本要从通过 IDEA 插件 JDK 开发能力中获取到工程信息、类信息、文件信息等。
- 外部功能：这一个是用于把插件能力与外部系统结合，比如你是需要把拿到的接口上传到服务器，还是从远程下载文件等等。

## :pencil: 章节目录

- [x] [`难度【★☆☆☆☆】第 00 节：IDEA 插件怎么开发？`](https://bugstack.cn/md/assembly/idea-plugin/2021-08-27-%E6%8A%80%E6%9C%AF%E8%B0%83%E7%A0%94%EF%BC%8CIDEA%20%E6%8F%92%E4%BB%B6%E6%80%8E%E4%B9%88%E5%BC%80%E5%8F%91%EF%BC%9F.html) 
- [x] [`难度【★★☆☆☆】第 01 节：两种方式创建插件工程`](https://bugstack.cn/md/assembly/idea-plugin/2021-10-18-%E3%80%8AIntelliJ%20IDEA%20%E6%8F%92%E4%BB%B6%E5%BC%80%E5%8F%91%E3%80%8B%E7%AC%AC%E4%B8%80%E8%8A%82%EF%BC%9A%E4%B8%A4%E7%A7%8D%E6%96%B9%E5%BC%8F%E5%88%9B%E5%BB%BA%E6%8F%92%E4%BB%B6%E5%B7%A5%E7%A8%8B.html)  
- [x] [`难度【★★☆☆☆】第 02 节：使用配置和侧边栏窗体，阅读本地文档`](https://bugstack.cn/md/assembly/idea-plugin/2021-11-03-%E3%80%8AIntelliJ%20IDEA%20%E6%8F%92%E4%BB%B6%E5%BC%80%E5%8F%91%E3%80%8B%E7%AC%AC%E4%BA%8C%E8%8A%82%EF%BC%9A%E9%85%8D%E7%BD%AE%E7%AA%97%E4%BD%93%E5%92%8C%E4%BE%A7%E8%BE%B9%E6%A0%8F%E7%AA%97%E4%BD%93%E7%9A%84%E4%BD%BF%E7%94%A8.html)  
- [ ] [`难度【★★★☆☆】第 03 节：开发工具栏和Tab页，展示股票行情和K线`](#)

## :octocat: 章节源码

- 第 01 节：[guide-idea-plugin-create-project-by-platform](https://github.com/fuzhengwei/guide-idea-plugin-create-project-by-platform)、[guide-idea-plugin-create-project-by-gradle](https://github.com/fuzhengwei/guide-idea-plugin-create-project-by-gradle)
- 第 02 节：[guide-idea-plugin-tool-window](https://github.com/fuzhengwei/guide-idea-plugin-tool-window)
- 第 03 节：[开发中](#)

## :paw_prints: 问题交流

![](https://github.com/fuzhengwei/small-spring/blob/main/docs/assets/img/bugstack-md.png?raw=true)

<br/>
<div align="center">
    <a href="https://github.com/fuzhengwei/CodeGuide/wiki">关注小傅哥，你可以学到的更多！</a>
</div>
<br/>  

- **加群交流**

    本群的宗旨是给大家提供一个良好的技术学习交流平台，所以杜绝一切广告！由于微信群人满 100 之后无法加入，请扫描下方二维码先添加作者 “小傅哥” 微信(fustack)，备注：`Spring学习加群`。
    
    <img src="https://bugstack.cn/images/personal/fustack.png" width="180" height="180"/>

- **公众号(bugstack虫洞栈)**

    沉淀、分享、成长，专注于原创专题案例，以最易学习编程的方式分享知识，让自己和他人都能有所收获。目前已完成的专题有；Netty4.x实战专题案例、用Java实现JVM、基于JavaAgent的全链路监控、手写RPC框架、DDD专题案例、源码分析等。
    
    <img src="https://bugstack.cn/images/personal/qrcode.png" width="180" height="180"/>
