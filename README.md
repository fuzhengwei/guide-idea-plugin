# IntelliJ IDEA 插件开发 - 教程

> 你好，我是小傅哥，[《重学Java设计模式》](https://item.jd.com/13218336.html) 图书作者，一线互联网 Java 工程师、架构师。[:pencil2: 虫洞栈，博主](https://bugstack.cn) 👉 如果你是刚入行、在外包、跨语言学习、想跳槽大厂、缺少学习动力等，可以阅读小傅哥的成长故事，这个系列包括了我的个人在外包到大厂的成长、跳槽的过程、互联网的学习经历 Go -> [关于小傅哥](https://bugstack.cn/md/about/me/2020-08-25-13%E5%B9%B4%E6%AF%95%E4%B8%9A%EF%BC%8C%E7%94%A8%E4%B8%A4%E5%B9%B4%E6%97%B6%E9%97%B4%E4%BB%8E%E5%A4%96%E5%8C%85%E8%B5%B0%E8%BF%9B%E4%BA%92%E8%81%94%E7%BD%91%E5%A4%A7%E5%8E%82.html)

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

- [学习说明](https://github.com/fuzhengwei/guide-idea-plugin#bookmark-%E5%AD%A6%E4%B9%A0%E8%AF%B4%E6%98%8E)
- [章节目录](https://github.com/fuzhengwei/guide-idea-plugin#pencil-%E7%AB%A0%E8%8A%82%E7%9B%AE%E5%BD%95)
- [章节源码](https://github.com/fuzhengwei/guide-idea-plugin#octocat-%E7%AB%A0%E8%8A%82%E6%BA%90%E7%A0%81)
- [问题交流](https://github.com/fuzhengwei/guide-idea-plugin#paw_prints-%E9%97%AE%E9%A2%98%E4%BA%A4%E6%B5%81)

## :bookmark: 学习说明

`我又要冲IDEA插件开发了！`

IDEA Plugin 插件开发可以帮助研发人员提升能效，解决一些实际场景中的共性问题。但最近在折腾IDEA插件开发的时候，市面的资料确实不多，也没有成体系完整的开发指导手册，所以就遇到了很多不知道就不会的事情，需要一点点查询搜索源码、验证API接口，最终把各项功能实现，当然在这个过程中也确实踩了不少坑！接下来在这个专栏会把一些关于 IDEA 插件开发用到的各项知识做成案例输出出来，帮助有需要的研发伙伴，一起建设 IDEA Plugin

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
- [x] [`难度【★★★☆☆】第 03 节：开发工具栏和Tab页，展示股票行情和K线`](https://bugstack.cn/md/assembly/idea-plugin/2021-11-18-%E3%80%8AIntelliJ%20IDEA%20%E6%8F%92%E4%BB%B6%E5%BC%80%E5%8F%91%E3%80%8B%E7%AC%AC%E4%B8%89%E8%8A%82%EF%BC%9A%E5%BC%80%E5%8F%91%E5%B7%A5%E5%85%B7%E6%A0%8F%E5%92%8CTab%E9%A1%B5%EF%BC%8C%E5%B1%95%E7%A4%BA%E8%82%A1%E7%A5%A8%E8%A1%8C%E6%83%85%E5%92%8CK%E7%BA%BF.html)
- [x] [`难度【★★★☆☆】第 04 节：扩展创建工程向导步骤，开发DDD脚手架`](https://bugstack.cn/md/assembly/idea-plugin/2021-11-24-%E3%80%8AIntelliJ%20IDEA%20%E6%8F%92%E4%BB%B6%E5%BC%80%E5%8F%91%E3%80%8B%E7%AC%AC%E5%9B%9B%E8%8A%82%EF%BC%9A%E6%89%A9%E5%B1%95%E5%88%9B%E5%BB%BA%E5%B7%A5%E7%A8%8B%E5%90%91%E5%AF%BC%E6%AD%A5%E9%AA%A4%EF%BC%8C%E5%BC%80%E5%8F%91DDD%E8%84%9A%E6%89%8B%E6%9E%B6.html)
- [x] [`难度【★★★☆☆】第 05 节：IDEA工程右键菜单，自动生成ORM代码`](https://bugstack.cn/md/assembly/idea-plugin/2021-12-08-%E3%80%8AIntelliJ%20IDEA%20%E6%8F%92%E4%BB%B6%E5%BC%80%E5%8F%91%E3%80%8B%E7%AC%AC%E4%BA%94%E8%8A%82%EF%BC%9AIDEA%E5%B7%A5%E7%A8%8B%E5%8F%B3%E9%94%AE%E8%8F%9C%E5%8D%95%EF%BC%8C%E8%87%AA%E5%8A%A8%E7%94%9F%E6%88%90ORM%E4%BB%A3%E7%A0%81.html)
- [x] [`难度【★★★☆☆】第 06 节：选定对象批量织入“x.set(y.get)”代码，自动生成vo2dto`](https://bugstack.cn/md/assembly/idea-plugin/2021-12-14-%E3%80%8AIntelliJ%20IDEA%20%E6%8F%92%E4%BB%B6%E5%BC%80%E5%8F%91%E3%80%8B%E7%AC%AC%E5%85%AD%E8%8A%82%EF%BC%9A%E4%BB%A5%E7%BB%87%E5%85%A5%E4%BB%A3%E7%A0%81%E7%9A%84%E6%96%B9%E5%BC%8F%EF%BC%8C%E8%87%AA%E5%8A%A8%E5%A4%84%E7%90%86vo2dto.html)


## :octocat: 章节源码

- 第 01 节：[guide-idea-plugin-create-project-by-platform](https://github.com/fuzhengwei/guide-idea-plugin-create-project-by-platform)、[guide-idea-plugin-create-project-by-gradle](https://github.com/fuzhengwei/guide-idea-plugin-create-project-by-gradle)
- 第 02 节：[guide-idea-plugin-tool-window](https://github.com/fuzhengwei/guide-idea-plugin-tool-window)
- 第 03 节：[guide-idea-plugin-tab](https://github.com/fuzhengwei/guide-idea-plugin-tab)
- 第 04 节：[guide-idea-plugin-scaffolding](https://github.com/fuzhengwei/guide-idea-plugin-scaffolding)
- 第 05 节：[guide-idea-plugin-orm](https://github.com/fuzhengwei/guide-idea-plugin-orm)
- 第 06 节：[guide-idea-plugin-vo2dto](https://github.com/fuzhengwei/guide-idea-plugin-vo2dto)

## :paw_prints: 问题交流

![](https://github.com/fuzhengwei/small-spring/blob/main/docs/assets/img/bugstack-md.png?raw=true)

<br/>
<div align="center">
    <a href="https://github.com/fuzhengwei/CodeGuide">关注小傅哥，你可以学到的更多！</a>
</div>
<br/>  

- **加群交流**

    本群的宗旨是给大家提供一个良好的技术学习交流平台，所以杜绝一切广告！由于微信群人满 100 之后无法加入，请扫描下方二维码先添加作者 “小傅哥” 微信(fustack)，备注：`Spring学习加群`。
    
    <img src="https://bugstack.cn/images/personal/fustack.png" width="180" height="180"/>

- **公众号(bugstack虫洞栈)**

    沉淀、分享、成长，专注于原创专题案例，以最易学习编程的方式分享知识，让自己和他人都能有所收获。目前已完成的专题有；Netty4.x实战专题案例、用Java实现JVM、基于JavaAgent的全链路监控、手写RPC框架、DDD专题案例、源码分析等。
    
    <img src="https://bugstack.cn/images/personal/qrcode.png" width="180" height="180"/>
