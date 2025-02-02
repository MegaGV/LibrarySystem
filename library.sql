/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2021-05-24 11:20:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` varchar(255) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `book_type` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `price` double(10,2) NOT NULL DEFAULT '0.00',
  `stock` int(11) NOT NULL DEFAULT '0',
  `total` int(11) NOT NULL DEFAULT '0',
  `detail` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('0157096e-b13f-4860-911e-20efbcd5f5df', '数字电子技术 : 第四版', 'T7', '(美) James Bignell, Robert Donovan', '科学出版社', '58.00', '2', '5', '本书共16章，重点介绍数制系统、布尔代数、逻辑门电路、加法器、常用集成电路、触发器、移位寄存器、计数器、数/模转换、译码器、多路复用器、多路选择器、三态门和存储器等内容及其应用。与同类书籍相比，本书具有简明扼要、叙述流畅等特点。另外，为了培养和提高学生实际分析和解决问题的能力，本书提供了大量的计算机仿真试验内容。为了适应数字技术的最新发展，本书增加了PLD和GAL编程及应用方面的内容。<br /> 本书可作为通信工程、电子工程、自动控制等专业数字电路技术课程的教材，也可供其他相关专业学生及科技人员使用。');
INSERT INTO `books` VALUES ('02810cef-82e5-44c8-b69b-222df8043d12', '哈佛商学院最受欢迎的领导课', 'F2', '(美) 罗伯特·史蒂文·卡普兰', '', '46.00', '1', '2', '《哈佛商学院最受欢迎的领导课》内容简介：我们往往有一种错觉，以为最优秀的领导者应该知道所有问题的答案。他们似乎天生就有准确的直觉，不可抵挡的魅力，高深的洞见，杰出的管理才能，以及激励他人的号召力。他们看上去如此自信，好像不费吹灰之力就能出色地领导众人，高效地管理团队。<br />现实却要复杂得多。事实的真相是，几乎所有成功的领导人，不管其天分有多高，都会经历一段困惑和不确定的时期——他们也会感到迷茫无助，忧心忡忡，无比孤独。而领导者是否能充分发挥潜能，关键就在于如何应对这段挣扎期。在卡普兰看来，回避困难只会让境况恶化，只有学会退后一步，问对关键问题，才能精确诊断现状，获得洞见，重整旗鼓，再度前进。<br />在《哈佛商学院最受欢迎的领导课》中，卡普兰提供了一种简洁明了而引人入胜的领导力框架，以帮助管理者改进领导效果。这个领导力框架由一系列关键问题搭建，包括愿景和要务、时间管理和工作授权等七个方面，领导者可以借助这个框架深入思考自己的工作，从而成功领导你的团队，掌控自己的职场命运。<br /><br />海报：');
INSERT INTO `books` VALUES ('02b68328-2337-41ca-969d-ba19326faecc', '雅思王阅读真题语料库', 'H3', '王陆, 凉月', '中国人民大学出版社', '28.00', '0', '2', '本书包括5章。第一章为讲述雅思阅读必须具备的常识。第二章为512个雅思阅读核心词汇速记，这是全书最重要内容。第三章阅读词根词缀衍生记忆法，是在512个核心词的基础上的拓展。第四章阅读快速反映核心词对，是协助考试做题。第五章是阅读场景机经词汇。考生识读即可。全书内容内容来自剑5—剑8真题阅读部分，经过反复筛选，对考生备考具有实际指导意义。');
INSERT INTO `books` VALUES ('03fb7786-6f82-496c-b157-adb8e904fbbf', '生物化学与分子生物学实验技术', 'Q5', '阿依木古丽, 蔡勇', '', '52.00', '3', '6', '');
INSERT INTO `books` VALUES ('045740ce-efb9-4a51-8e77-6970f47e8748', '机械电气控制与PLC应用', 'T8', '', '', '58.50', '2', '5', '');
INSERT INTO `books` VALUES ('05cf259f-62e5-4512-9e72-44973090e4af', '二战经典战役', 'E1', '《时刻关注》编委会', '中国铁道出版社', '39.80', '4', '4', '着重选取了第二次世界大战时期的经典战役，从正反两方面，以全新的角度诠释了这一期间的一系列战役内容。例如：闪击波兰、血战阿登、鹰击不列颠、血捍莫斯科、斯大林格勒保卫战、偷袭珍珠港、搏杀中途岛、梦断马奇诺……在这些战役中，各国*秀的军事天才在这些经典的战役后给后人留下了很多的启示，对第二次世界大战的结束及战后的世界格局也产生了重大的影响。本书将为您一一揭秘。');
INSERT INTO `books` VALUES ('0ba06ded-0612-419a-8b4e-ae0bdfc61729', '中国经济思想通史', 'F0', '赵靖', '北京大学出版社', '24.00', '4', '4', '');
INSERT INTO `books` VALUES ('0ecdf9b2-2549-4fe3-9a08-8ae9ff1b936d', '看不见的森林 : 林中自然笔记', 'S7', '(美) 戴维·乔治·哈斯凯尔', '商务印书馆', '46.00', '6', '6', '这是一本森林观测笔记。 在这本书里，一位生物学家以一年的时间为主线，在每次的观测中，为我们揭开藏森林一平方米地域里的秘密。 在这本完全原创的书里，生物学家戴维以一小片森林作为整个自然界的缩影，向我们生动地展示了这片森林和居住其中的栖息者的生活状况。 书的每一章都以一次简单的观察结果作为开头，比如藏在落叶层里的火蜥蜴，春天里野花的初次绽放。通过这些观察，戴维织就了一个生物生态网，向人们解释了把最小的微生物和最大的哺乳动物联系起来的科学观点，并描述了延续数千年甚至数百万年的生态系统。戴维每天都会在这片森林里漫步，梳理出各种以大自然为家的动植物间复杂而又微妙的关系，因此每一次寻访对于他来说，都象征着一个自然故事的缩影。 本书优美的语言使读者把阅读过程看作一次寻找大自然奥秘的盛大旅行，而作者则像导游，带领大家探索存在于我们脚下或者藏在我们后院里的奇妙世界。');
INSERT INTO `books` VALUES ('14315e47-c398-47d1-b297-2ac8401dc14e', 'Excel VBA程序开发自学宝典', 'T3', '罗刚君', '电子工业出版社', '79.00', '1', '1', '《Excel VBA程序开发自学宝典（第2版）》是VBA入门的经典教材，对VBA的基础理论、语法规则、代码优化、编写思路、开发函数与使用数组等都进行了详尽的理论阐述和案例演示，同时还搭配窗体与控件、正则表达式、字典、File System Object、API、类模块、脚本语言的应用，以及开发功能区、开发加载宏、封装代码等高级应用，力求完整地展示了VBA的功能与魅力。通读本书，您不仅能学到如何开发函数、编写程序，还能设计报表系统，独立开发Excel百宝箱式的大中型插件。<br />本书是畅销书《Excel VBA程序开发自学宝典》的升级版本。《Excel VBA程序开发自学宝典》第1版于2009年10月上市，基于Excel 2007版本撰写，曾多次销罄重印，并在当当网和亚马逊网连续三个季度居VBA类图书销量第一名。然而本着精益求精的原则，作者在售后服务QQ群和售后论坛中收集了大量的用户反馈信息，对图书进行改版，删除了部分实用性不大的内容，增加了正则表达式、字典的应用、开发功能区与封装代码等章节，增加了开发插件的案例，全书改用Excel 2010截图，并修正了第1版中无法兼容Excel 2010的部分代码与设计思路，促使教学内容更具通用性。<br /><br /> 注：本书实物有686页，目录有778页。第30章目录后面标有说明，本章及附录内容（687-778页）均在光盘内。');
INSERT INTO `books` VALUES ('2257cbed-e9f3-4207-a409-54879296169b', '空气动力学基础', 'V2', '曾明, 刘伟, 邹建军', '', '36.00', '6', '6', '');
INSERT INTO `books` VALUES ('288dd68f-d221-4bdd-92c5-f2852113e780', '操作系统：精髓与设计原理（原书第6版）', 'T0', '（美）斯托林斯', '机械工业出版社', '69.00', '3', '3', '本书是计算机界巨擘William Stallings的经典著作之一，其第4版曾获得美国计算机科学与工程类大奖，得到了全球计算机教育界和工程技术人员的好评。书中不仅全面地讲述了操作系统的基本概念、原理和方法，而且还以当代最流行的操作系统为例，全面清楚地展现了当代操作系统的本质和特点。作者针对近几年操作系统领域的*变化，对操作系统的设计原理进行深入的阐述，同时将其对操作系统整个领域全面而深入的理解呈现给读者。');
INSERT INTO `books` VALUES ('2a451f37-0c35-4ea7-b9a1-0510ae533f28', '等离子体弧熔融裂解 : 危险废弃物处理前沿技术', 'X7', '丁恩振, 丁家亮', '', '30.00', '0', '1', '');
INSERT INTO `books` VALUES ('386ea8ff-94f5-4557-adf6-b711035aaf57', '从零开始读懂心理学', 'B8', '文真明', '', '36.80', '2', '2', '');
INSERT INTO `books` VALUES ('3e9990ae-4ecf-4a7c-8572-2f977ab70a4d', '世界桥梁趣谈', 'U4', '唐寰澄', '北京出版社', '26.00', '6', '6', '');
INSERT INTO `books` VALUES ('3f0085f4-b6ac-41cd-8314-b3d5a358d315', '数据结构、算法与应用 C++语言描述（原书第2版）', 'T0', '(美)萨尼', '机械工业出版社', '79.00', '2', '2', '　本书共分三个部分。第一部分从第1章到第4章，旨在复习C++程序设计的概念以及程序性能的分析和测量方法。第二部分从第5章到第16章，研究数据结构，包括线性表、数组和矩阵、栈、队列、字典、二叉树、优先级队列、竞赛树、搜索树和图等。第三部分从第17章到第21章，研究常用算法，包括贪婪算法、分而治之算法、动态规划、回溯算法和分枝定界算法。本书有800多道练习题和50多个应用实例。内容广博，组织合理，论述清晰，循序渐进，而且对程序性能的分析和测量系统入微。本书不仅是数据结构和算法的经典教材，而且是计算机科学与工程领域的理想参考书。');
INSERT INTO `books` VALUES ('3f0314cf-10e9-468c-a32c-09010535e127', '马云 : 一路这样走来', 'K8', '李亮', '贵州人民出版社', '32.00', '1', '1', '《马云 一路这样走来》是一本详细记录马云载跌宕人生年创业精髓的倾情力作。本书结合马云的传奇经历，深刻剖析马云创业过程中的各个环节，讲述其成就事业之道为人处世之道。身材矮小的马云，却是这个世界上最大的互联网市场中的大人物。短短年间，他从英语教师成长为互联网大亨，并带领阿里巴巴成为全世界最大的电子商务平台的经历，折射出中国互联网的崛起和发展历程。 翻开《马云 一路走来》这本书，体悟马云的成功之道；反复阅读，吸收马云的人生智慧。');
INSERT INTO `books` VALUES ('451a512a-e76d-4880-ab19-75b3b247390b', 'C程序设计语言 第2版·新版', 'T0', '（美）克尼汉，（美）里奇', '机械工业出版社', '30.00', '2', '2', '本书是由C语言的设计者Brian W. Kernighan和Dennis M. Ritchie编写的一部介绍标准C语言及其程序设计方法的权威性经典著作。全面、系统地讲述了C语言的各个特性及程序设计的基本方法，包括基本概念、类型和表达式、控制流、函数与程序结构、指针与数组、结构、输入与输出、UNIX系统接口、标准库等内容。');
INSERT INTO `books` VALUES ('4c94fc31-6f27-44e7-8b88-6a2b77ad7474', '纪录片专题片概论', 'J9', '倪祥保, 邵雯艳', '', '25.00', '0', '1', '《纪录片专题片概论》从艺术本体出发，就纪录片和专题片伪艺术与真艺术的本体属性、唤起情感与表现情感、重结果与重过程、内容形式上的模式化与个性化以及对待受众的不同关系等诸多方面的差异展开详细、深入的探讨，通过比较进一步凸显专题片的特征，避免了以泛艺术的观点混淆专题片和纪录片的界定。');
INSERT INTO `books` VALUES ('60eb327f-2984-4109-b66b-eee67de33956', '数学建模基础案例', 'O1', '杜建卫, 王若鹏', '', '52.80', '0', '2', '');
INSERT INTO `books` VALUES ('654b4602-0c87-46c9-8d7c-21dffa819c2f', 'C++程序设计语言（第1—3部分）（原书第4版）', 'T0', '（美）本贾尼·斯特劳斯特鲁普', '机械工业出版社', '139.00', '3', '3', '《C 程序设计语言》（原书第4版）是C 领域最经典的参考书，介绍了C 11的各项新特性和新功能。全书共分四部分。第一部分（第1~5章）是引言，包括C 的背景知识，C 语言及其标准库的简要介绍；第二部分（第6~15章）介绍C 的内置类型和基本特性，以及如何用它们构造程序；第三部分（第16~29章）介绍C 的抽象机制及如何用这些机制编写面向对象程序和泛型程序；第四部分（第30~44章）概述标准库并讨论一些兼容性问题。由于篇幅问题，原书中文版分两册出版，分别对应原书的第一至三部分和第四部分。这一册为第一至三部分。');
INSERT INTO `books` VALUES ('67caccc5-e8fc-4884-9c11-189ab187d717', '刑事诉讼法', 'D9', '国家司法考试辅导教材编写组', '', '25.00', '1', '1', '');
INSERT INTO `books` VALUES ('6c03ad91-defd-47a3-8a0c-f603e54716be', 'test111', 'A1', '', '', '111.00', '1', '10', '');
INSERT INTO `books` VALUES ('8faec636-9699-4ade-bb94-f6393c8109be', '新榜样 : 新媒体运营实战指南', 'G2', '新榜编', '电子工业出版社', '50.00', '4', '4', '本书为“新榜”全新打造的新媒体运营实战指南，全书基于对当前各类公众号的对比分析，阐述互联网时代各类公众号运营之道。全书分为五个部分，分别为：如何让政务公众号接地气，如何让自媒体公众号重获新生，如何做垂直领域的特色公众号，新媒体深度人物，以及新媒体深度分析等。');
INSERT INTO `books` VALUES ('8fc97a0d-c562-4ca9-9393-a420fedb1e1c', '必然', 'N1', '(美) 凯文·凯利', '电子工业出版社', '58.00', '1', '1', '《必然》的作者凯文·凯利，被称为“硅谷精神之父”和“世界互联网教父”。前两部《失控》和《科技想要什么》在中国出版后，引起巨大反响。书中凯文·凯利对十二种必然的科技力量加以详细的阐述，并描绘出未来三十年这些趋势如何形成合力指引我们前行的方向。<br />作者凯文·凯利基于过往从业经历和对未来趋势的敏锐观察对十二个关键词“形成”“知化”“流动”“屏读”“使用”“共享”“过滤”“重混”“互动”“追踪”“提问”“开始”一进行了深入浅出的论断，并结合现实生活中种种生动的案例，给我们指明了科技新物种不断变迁的12条道路。它们每一个都是一种正在发生的趋势。同时，生活中的种种迹象也表明这些趋势将持续至少三十年。<br />书中对未来的展望趣味十足，充满画面感，比如书中说道“未来，你的薪水高低将取决于你能否和机器人默契配合。90%的同事将会是看不到的机器，而没有它们，你的大部分工作将无法完成。”今天我们对未来充满无限期待，用天马行空的想象力描述它，但真正伟大的东西远远超出我们的想象。未来令人是难以置信的，但是，我们须相信那些不可能之事，我们尚处在开始的开始。在不远的将来，我们会向哪些方向前行，必然而然。<br />——亚马逊编辑简单的华丽<br />《必然》不是一本科技著作，也不单纯是一本思想著作，而是一个人倾尽心力为未来30年人类命运做的一次掐算。读过《失控》的人都知道，凯文·凯利说对了此前的30年。<br />你愿意相信他所猜想的后30年吗？<br />你的创业创新在不在未来的风口上？<br />你的职业规划在不在未来的潮流中？<br />你的行动会不会得到那个“新物种”的认可？<br />吹一句牛——率先看到这本书的读者，就是比其他人领先完成一次思想升级。');
INSERT INTO `books` VALUES ('90053ede-caa9-427c-9977-3a8aecf279d5', '人的未来', 'C9', '(法) 德日进', '', '38.00', '5', '5', '《人的未来》收集了德日进探讨人的未来问题的主要论文。这些论文构成了一个强有力的整体，包含丰富、深刻的创见，使人读来觉得其作者是一位名副其实的未来学创始人。<br />人的未来的问题在德日进的著作中占有十分重要的地位。他的基本兴趣不在于艺术的未来、科学的未来、西方文明的未来，他的视觉是地质学家和古生物学家的视觉，也就是说，他致力于认识世界的结构和演化，不仅细致地分析种种最隐秘的自然现象，而且还以完全连贯的、可理解的方式探究这些现象之间的联系。');
INSERT INTO `books` VALUES ('926c621a-5065-46c5-8de5-c7cc62ed5e38', 'GitHub入门', 'T3', 'Peter Bell, Brent Beer', '中国电力出版社', '28.00', '2', '2', '');
INSERT INTO `books` VALUES ('98d7ca08-ad8f-4298-8f56-5567b45f98e9', '最好不相见, 最好不相念 : 闲品历代爱情往事', 'D6', '王臣', '现代出版社', '37.00', '4', '4', '畅销书作家王臣<br />“古典美文”系列作品<br />>>>><br />国内第一本全景式品读古典爱情往事的美文书。<br />畅销书作家王臣以淡婉清丽的笔触，<br />书写了一册清幽古雅的唯美古情书，<br />是古典文学品鉴之书当中难得一遇的雅丽上品之作。<br />>>>><br />全书挑选了三十五段中国古典文学当中极具代表性的爱情故事，<br />从卫子夫与汉武 帝到虞姬与项羽，<br />从卓文君与司马相如到白素贞与许仙，<br />从祝英台与梁山伯到聂小倩与宁采臣……<br />一一落于纸间，织成锦绣文章。<br />>>>><br />但是相思莫相负，牡丹亭上三生路。<br />爱是简单，爱是质朴，爱是深重。<br />愿，天下有情人终成眷属。让爱善终。');
INSERT INTO `books` VALUES ('9e4a096a-3957-4cde-9611-0b515df8d971', '科学的奥秘 : 现代科技重大发现与发明. 上', 'Z2', '(澳) 艾伦·R. 格兰维尔', '人民邮电出版社', '59.00', '7', '7', '');
INSERT INTO `books` VALUES ('9e9b9db8-7ba3-49a5-ba98-1a663de5ba52', '邓小平与中国特色社会主义', 'A8', '田克勤, 李婧', '中国人民大学出版社', '78.00', '1', '2', '本书以新时期马克思主义中国化的推进为主线，以中国特色社会主义为主题，以改革开放和社会主义现代化建设为实践背景，以邓小平同志在开创中国特色社会主义道路、形成中国特色社会主义理论体系、确立中国特色社会主义制度过程中所做出的杰出贡献为主要内容，力求深入阐述邓小平和邓小平理论的历史地位及其当代价值，充分体现党的十八大和习近平总书记系列重要讲话精神。');
INSERT INTO `books` VALUES ('a108a9f0-380f-4da3-8d4b-45d7831927ba', '走, 回民国住两天', 'I2', '沈伟东', '长江文艺出版社', '32.80', '3', '3', '这是一本民国时期的文化名人关于家园的散文随笔集。<br />跟随这本小书，我们又“回到民国”，好似进入一个悠长的历史雨巷，每一块青石板都闪动着身穿长袍、对襟衫、旗袍的人们的模糊倒影。<br />书中有阳光的江南冬日，你可以和丰子恺先生“把椅子靠在窗缘上，背着窗坐了看书，太阳光笼罩了我的上半身……”<br />书中有闲适的午后时光，朱自清诸先生侧耳倾听窗外淅淅沥沥，捉住呼噜声中老去的猫儿，摸两粒茴香豆喝一壶老酒，谈谈学问之道。<br />“回到民国”，品不同的景，识不同的人，看到他们别样的生活、悠闲的旧时光。<br />“回到民国”，可以为我们烦躁的心灵找安居之所，找到那个似曾失去又似曾存在的自己。');
INSERT INTO `books` VALUES ('aa207035-42da-4160-9184-db5fab877745', '英语口语王 : 想说就说', 'H3', '(美) Brandon Soloski', '', '36.00', '1', '1', '《英语口语王:想说就说》分为4部分：天天用短语，传情达意句，生活场景句，行业英语句。其中每一个地道的句子以最容易学习的方式表达出来，读者不但可以学的轻松还可以举一反三。每一个对话也可以真实全面的练习，在短时间内英语能力直窜高峰。每一个生活场景，读者可以体会到最贴近生活的实际情景和说话时的真切氛围。<br />7大行业英语：奥运市民英语、白领英语、宾馆英语、售货员英语、餐馆英语、出租车英语、警察英语。读者只要模仿其中的句子在工作中定能游刃有余。<br />《英语口语王:想说就说》特聘请美籍语言专家参加语音录制，以保证读者可以模仿地道的美音。');
INSERT INTO `books` VALUES ('b3c20d79-c6d6-4b03-a418-b0ae27f8aa3a', 'How the immune system works', 'R3', 'Sompayrac, Lauren', '', '35.80', '6', '6', '');
INSERT INTO `books` VALUES ('b99b1e18-8410-4217-b4e6-f4bfeab3e500', '计算机程序的构造和解释：原书第2版', 'T0', '', '机械工业出版社', '45.00', '2', '2', '本书1984年出版，成型于美国麻省理工学院(MIT)多年使用的一本教材，1996年修订为第2版。在过去的二十多年里，本书对于计算机科学的教育计划产生了深刻的影响。　　第2版中大部分重要程序设计系统都重新修改并做过测试，包括各种解释器和编译器。作者根据其后十余年的教学实践，还对其他许多细节做了相应的修改。　　本书自出版以来，世界各地已有100多所院校采用本书做教材，其中包括美国斯坦福大学、美国普林斯顿大学、英国牛津大学、日本东京大学等。');
INSERT INTO `books` VALUES ('c14b2068-a9a9-4ed3-a0bb-c0eaba63247d', '斯大林传', 'A7', '(英) 罗伯特·谢伟思', '华文出版社', '68.00', '1', '1', '罗伯特•谢伟思让读者无限接近斯大林。作者通过莫斯科尚未公开的档案、当事人的亲历证词以及私人收藏等，使斯大林在政治、经济、军事等不同领域，在革命时期和战争年代等不同时期的形象逐渐清晰。谢伟思分析了酗酒的父亲和虔诚的母亲，格鲁吉亚血统，宗教教育，以及马克思主义革命热情对斯大林造成的潜在影响，以此来阐释斯大林日后成为布尔什维主义激进分子、苏共领袖以及统治“暴君”的深刻内因，并进一步从斯大林的政治天赋、诗人气质中找到他崇尚权力、热衷报复、冷酷残忍的根源。<br />斯大林被神话了，也被歪曲了——这是罗伯特•谢伟思经过几十年研究得出的权威且引人深思的结论。');
INSERT INTO `books` VALUES ('c7e38a9d-3da8-4297-b061-49878570114a', '思维导图宝典', 'B8', '(英) 东尼·博赞', '化学工业出版社', '33.00', '2', '2', '《思维导图宝典》是一本在全球销量达千万的畅销书。书中的思维导图方法将发散性思维与开拓性笔记技巧结合在一起，被人称为“大脑瑞士军刀”，它的出现，在全球教育界和商界刮起了一场风暴。目前全球已有超过3亿人在使用它。<br />本书为最新修订版，特别更新了由博赞先生本人为国际培训课程所最新研发和改进的一系列实用训练，大量颇具启发性的图片，以及演示这种技巧的原创性思维导图。');
INSERT INTO `books` VALUES ('d8bc110b-f270-4f03-9636-e9a29ec1f15a', '人类宇宙', 'P1', '(英) 布赖恩·考克斯, (英) 安德鲁·科恩', '人民邮电出版社', '88.00', '6', '6', '我们在哪里？<br />我们孤独吗？<br />我们是谁？<br />我们为何在此？<br />我们的未来在何方？<br />人类的生命是非常奇异的存在。<br />岩石在一颗核爆火球的四周散落，形成球形表面。在黑暗的真空环境下，自然法则协力创造了人类，我们抬头仰望星空，不知道自己从哪里来。<br />自从人类在地球上出现以来，我们始终在寻找自己在宇宙中的位置。我们探寻天与地，以及人类生命的珍贵价值。但或许zui重要的是，我们不断推动自己做着全宇宙****的事，那就是我们不断提出问题。<br />在这本开创性的著作中，布赖恩?考克斯教授选取了人类试图用以了解自己本质和我们所生活的宇宙的一些重要问题。他将带领我们一起回顾人类解答这些问题的历程；通过人类思维的无尽飞跃，他探讨了我们当今知识的非凡深度，以及我们的好奇心引领我们前行的方向。他揭示了时间、物理和化学如何联手创造出一种对其自身的存在感到惊讶不已的生物，这种生物不仅无法抑制想要探索自己是从哪里来的渴望，也对其如何思考、未来会怎样、人类是否孤独产生了无穷的疑问。<br />《人类宇宙》是布赖恩?考克斯教授对人类的过去、现在和未来的个人看法——从宇宙起源到我们人类的zui终命运。这本书将带给你一个具有全新维度的世界，让你对人类生命有新的视角。');
INSERT INTO `books` VALUES ('dceb7857-c2e0-4757-9497-a35300fcff8d', '特工风云戴笠', 'K8', '潘望', '中央编译出版社', '32.00', '1', '1', '本书作为一本传记小说，从客观的角度出发，详细回顾了军统魔王戴笠的一生：从他的早年生活、黄埔发迹，到他青云直上、缔造军统，再到他大权在握、为所欲为，最终落得横遭猜忌、身死戴山。特工们的秘密世界，往往是人们最为陌生又最为好奇的，本书通过回顾那段历史，最终为读者打开了一扇通往军统秘密世界的大门，全面展现了戴笠这位军统缔造者的传奇人生。');
INSERT INTO `books` VALUES ('e99072f3-cd5a-4563-8c7d-30340368b4b7', '科学史', 'N0', '(英) W. C. 丹皮尔', '中国人民大学出版社', '49.80', '1', '1', '《科学史》主要讲述了科学、哲学和宗教在人类历史长河中各自的发生、发展历程，以及它们在各个历史时期所发生的相互纠葛。作者以科学技术的发展为主干，以哲学和宗教为枝叶，勾勒了一幅人类科学技术与人文景观齐头并进的美妙画卷。');
INSERT INTO `books` VALUES ('f2e718b4-bb3d-4924-a1cf-a0826ed6c11d', '圣女的救济', 'I0', '(日) 东野圭吾', '当代世界出版社', '25.00', '2', '2', '丈夫在家里遭毒杀，身为嫌疑犯的妻子却有着铜墙铁壁般的不在场证明。究竟是如何下毒的？被称之为神探伽利略的汤川教授再度挑战高难度的不可能犯罪，去证明在那惟有女性特有的不合逻辑思考下设定的骇人诡计，而答案竟是“虚数解”—— 从理论上而言可行，但从现实上而言却是决不可能的。贤惠温柔的妻子，为了挽回丈夫的爱，做出了“完全不可能”的事情……<br />《圣女的救济》是继《嫌疑犯X的献身》后伽利略系列长篇的第二部。');
INSERT INTO `books` VALUES ('f44b15b4-8b25-406e-b72d-06319c46911c', '邓小平智慧', 'A8', '萧诗美', '人民出版社', '56.00', '3', '3', '邓小平是中国改革开放的总设计师。他的各种政治设计、经济设计、文化设计、社会设计等，无不充满着智慧，其大智大勇令人叹为观止。举重若轻，绵里藏针，能屈能伸，原则的坚定性和策略的灵活性高度统一，等等，既是邓小平智慧的精华所在，也是新时期治国理政和为官做人的重要精神资源。本书主要从谋划和策略的角度对邓小平智慧进行了系统解读，并以深入浅出的语言对其博大精深和运用之妙进行了传神的刻画，包括：治乱智慧、改革智慧、经济智慧、政治智慧、军事智慧、统战智慧、外交智慧。全书史论结合，夹叙夹议，雅俗共赏，很好地做到了让伟人的思想和智慧以贴近现代生活的方式走进现代人的生活世界！');

-- ----------------------------
-- Table structure for book_reviews
-- ----------------------------
DROP TABLE IF EXISTS `book_reviews`;
CREATE TABLE `book_reviews` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `book_id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_reviews
-- ----------------------------
INSERT INTO `book_reviews` VALUES ('3e9990ae-4ecf-4a7c-8572-2f197ab70a4d', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '3e9990ae-4ecf-4a7c-8572-2f977ab70a4d', '桥', 'test', '2');
INSERT INTO `book_reviews` VALUES ('92ce0faa-3bde-4df1-32aa-61591774dbab', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '0ecdf9b2-2549-4fe3-9a08-8ae9ff1b936d', '不例外的法则', '但是，因为热爱自然便憎恶人类，这是不合逻辑的。人类是整体的一部分。真正爱这个世界，就也应该热爱人类的聪明才智和活泼嬉戏。自然界并不需要将人工制品清除出去才能变得美丽或是协调一致。没错，我们不该那么贪婪、不讲卫生、浪费成性、目光短浅。但是我们也不要把责任变成自我憎恶吧。归根结底，我们最大的缺点是对世界缺乏怜悯之心，甚至对自己也不例外。', '1');
INSERT INTO `book_reviews` VALUES ('92ce0faa-3bde-4df1-82aa-61591124dbab', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '0ecdf9b2-2549-4fe3-9a08-8ae9ff1b936d', '体热的增长量', '一切物体，包括动物身体在内，体积的增量是长度增量的立方倍；而一只动物全身所能生成的热量，与其身体大小是成正比的；因此，体热的增长量，也是身体长度增量的立方倍。而在热量流失时，表面积的增量只是长度增量的平方倍。小动物的体温下降速度之所以更快，是因为按照比例来说，它们的体表面积远远大于身体体积。', '1');
INSERT INTO `book_reviews` VALUES ('92ce0faa-3bde-4df1-82aa-61591224dbab', '7572249b-386a-4ef6-82bb-7cb9f2e06986', '0ecdf9b2-2549-4fe3-9a08-8ae9ff1b936d', '白尾鹿', '有一棵槭叶荚蒾顶端的枝葉已经被咬斷了，在枝幹上留下了一截截呈斜面的殘柄。把這些嫩枝吃掉的動物留下了三個腳印，由東向西分佈在曼荼羅地上。每一個腳印都是由兩個杏仁形狀的印子所組成，陷入落葉層約兩寸深。這是偶蹄動物的特徵。這塊曼荼羅地就想世界上絕大多數陸棲生物群落一樣，總會有某隻分蹄哺乳動物前來覓食。今天這只動物是白尾鹿。 ', '1');
INSERT INTO `book_reviews` VALUES ('92ce0faa-3bde-4df1-82aa-61591773dbab', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '0ecdf9b2-2549-4fe3-9a08-8ae9ff1b936d', '并不冲突', '我们中间每个人，体内都驻扎着一座层层叠叠的坛城，其复杂性与深度，丝毫不逊于一片过熟林，甚至有过之而无不及。观望自身，与观望世界并不冲突。', '0');
INSERT INTO `book_reviews` VALUES ('92ce0faa-3bde-4df1-82aa-61591774dbab', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '0ecdf9b2-2549-4fe3-9a08-8ae9ff1b936d', '山雀', '每只山雀的肥瘦，是处于饥饿与被捕食这两大风险之间的平衡。', '1');
INSERT INTO `book_reviews` VALUES ('ba05892f-4403-48f2-9c36-1de615d5f3fc', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '0157096e-b13f-4860-911e-20efbcd5f5df', '对数字电子技术d的书评', '此书是是教育部“高等教育面向21世纪教学内容和课程体系改革计划”的研究成果,是面向21世纪课程教材,同时也是教育部“九五”规划教材和“九五”重点教材。 在基本保持第三版理论体系的基础上,本次修订以较大篇幅增补了可编程逻辑器件的内容,并独立成一章。原来的“大规模集成电路”改为“半导体存储器”。', '1');

-- ----------------------------
-- Table structure for borrow_records
-- ----------------------------
DROP TABLE IF EXISTS `borrow_records`;
CREATE TABLE `borrow_records` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `book_id` varchar(255) NOT NULL,
  `borrow_date` datetime NOT NULL,
  `return_date` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_records
-- ----------------------------
INSERT INTO `borrow_records` VALUES ('0459f09f-d679-452a-8f42-4e444f5ee1ca', 'ba05892f-4403-48f2-9c36-1de685d5f3fc', '0157096e-b13f-4860-911e-20efbcd5f5df', '2021-04-23 00:38:48', '2021-04-23 08:10:30', '2');
INSERT INTO `borrow_records` VALUES ('0aa5d45f-df38-4be0-bb06-9b436c805f7c', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '02b68328-2337-41ca-969d-ba19326faecc', '2021-04-23 08:08:27', '2021-04-23 08:10:34', '2');
INSERT INTO `borrow_records` VALUES ('11e6c956-c2b9-4372-b99e-69233af7547d', 'ba05892f-4403-48f2-9c36-1de685d5f3fc', '02810cef-82e5-44c8-b69b-222df8043d12', '2021-04-23 00:38:52', '2021-05-07 10:51:42', '2');
INSERT INTO `borrow_records` VALUES ('1462813a-b9d7-46b1-aecf-54ba91db5fd9', '92ce0faa-3bde-4df1-82aa-69991773dbab', '03fb7786-6f82-496c-b157-adb8e904fbbf', '2020-10-14 09:15:25', '2020-10-14 09:45:39', '2');
INSERT INTO `borrow_records` VALUES ('154797e1-1b04-4bc6-94ad-f197e6f01397', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '02b68328-2337-41ca-969d-ba19326faecc', '2020-10-22 17:46:59', '2020-10-22 22:48:09', '2');
INSERT INTO `borrow_records` VALUES ('31985c53-413b-491a-9ae7-ea17dd47c7e9', '92ce0faa-3bde-4df1-82aa-69991773dbab', 'c14b2068-a9a9-4ed3-a0bb-c0eaba63247d', '2020-10-22 22:43:45', '2020-10-22 22:47:48', '2');
INSERT INTO `borrow_records` VALUES ('3bea07a8-a54c-436d-97e6-fb108f6bf150', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '045740ce-efb9-4a51-8e77-6970f47e8748', '2021-05-22 09:42:25', '2021-06-21 09:42:25', '0');
INSERT INTO `borrow_records` VALUES ('3cb13a08-5502-4195-bd0c-5ac83ebbeaaa', '92ce0faa-3bde-4df1-82aa-69991773dbab', '0157096e-b13f-4860-911e-20efbcd5f5df', '2020-10-14 09:13:32', '2020-10-14 09:47:04', '2');
INSERT INTO `borrow_records` VALUES ('3cbf51a1-067e-4deb-bc5e-16c22425b46f', '92ce0faa-3bde-4df1-82aa-69991773dbab', '045740ce-efb9-4a51-8e77-6970f47e8748', '2020-10-21 21:02:51', '2020-10-21 21:12:49', '2');
INSERT INTO `borrow_records` VALUES ('3e56a784-7d06-44b7-bf23-e93a8bb7ad41', 'ba05892f-4403-48f2-9c36-1de685d5f3fc', '02b68328-2337-41ca-969d-ba19326faecc', '2021-04-23 00:38:53', '2021-05-23 00:38:53', '0');
INSERT INTO `borrow_records` VALUES ('415217d3-b2e4-4140-a0fb-0d4599d1f90b', 'ba05892f-4403-48f2-9c36-1de685d5f3fc', '0157096e-b13f-4860-911e-20efbcd5f5df', '2021-04-23 00:38:46', '2021-05-23 00:38:46', '0');
INSERT INTO `borrow_records` VALUES ('42234475-a519-4555-b91f-cd2519cf42bb', '92ce0faa-3bde-4df1-82aa-69991773dbab', '045740ce-efb9-4a51-8e77-6970f47e8748', '2020-10-12 10:01:23', '2020-10-14 10:03:51', '2');
INSERT INTO `borrow_records` VALUES ('4f151746-4559-4a51-a7f1-615a7004d18b', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '02b68328-2337-41ca-969d-ba19326faecc', '2021-05-22 09:42:23', '2021-06-21 09:42:23', '0');
INSERT INTO `borrow_records` VALUES ('5122cc6c-9f03-434c-bfc5-2ad0971ee8e2', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '045740ce-efb9-4a51-8e77-6970f47e8748', '2020-10-22 17:47:02', '2021-04-23 00:34:01', '2');
INSERT INTO `borrow_records` VALUES ('5a481cbf-9b63-478d-bf00-f3f857036c61', '92ce0faa-3bde-4df1-82aa-69991773dbab', '02810cef-82e5-44c8-b69b-222df8043d12', '2020-10-14 09:15:09', '2020-10-14 09:52:07', '2');
INSERT INTO `borrow_records` VALUES ('694f0a0b-aa93-459e-a42d-9e368a713c3f', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '02810cef-82e5-44c8-b69b-222df8043d12', '2021-05-22 09:42:20', '2021-06-21 09:42:20', '0');
INSERT INTO `borrow_records` VALUES ('6e3274cb-c7e4-4bf1-8e81-9390b063f3a8', '92ce0faa-3bde-4df1-82aa-69991773dbab', '0157096e-b13f-4860-911e-20efbcd5f5df', '2020-10-21 21:02:41', '2021-04-23 08:11:00', '2');
INSERT INTO `borrow_records` VALUES ('741721f1-cb7f-4d5b-9755-6edc0cdb5674', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '02810cef-82e5-44c8-b69b-222df8043d12', '2020-10-22 17:46:59', '2021-05-20 09:39:11', '2');
INSERT INTO `borrow_records` VALUES ('7b0419df-c920-417e-a726-702f924c0e3f', '92ce0faa-3bde-4df1-82aa-69991773dbab', '045740ce-efb9-4a51-8e77-6970f47e8748', '2020-10-12 09:15:31', '2020-10-14 09:53:28', '2');
INSERT INTO `borrow_records` VALUES ('7ff4f3be-e087-4df7-b066-6c569fad2a3e', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '03fb7786-6f82-496c-b157-adb8e904fbbf', '2020-10-22 17:47:00', '2020-11-21 17:47:00', '0');
INSERT INTO `borrow_records` VALUES ('897ad761-1161-434e-8ccd-bcc061b77ffd', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '0157096e-b13f-4860-911e-20efbcd5f5df', '2021-05-22 09:42:19', '2021-06-21 09:42:19', '0');
INSERT INTO `borrow_records` VALUES ('8ca67071-ba53-4c58-b8df-2c4273a297c5', '92ce0faa-3bde-4df1-82aa-69991773dbab', '02b68328-2337-41ca-969d-ba19326faecc', '2020-09-14 09:15:19', '2020-10-14 09:54:26', '2');
INSERT INTO `borrow_records` VALUES ('936ac57e-cbdb-4119-a56b-f21bb42acc6d', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '0157096e-b13f-4860-911e-20efbcd5f5df', '2020-10-22 17:46:58', '2021-05-20 09:39:17', '2');
INSERT INTO `borrow_records` VALUES ('b4ca075c-2880-4202-8d12-7c389f2476e8', '92ce0faa-3bde-4df1-82aa-69991773dbab', '045740ce-efb9-4a51-8e77-6970f47e8748', '2020-10-21 21:12:36', '2020-11-20 21:12:36', '0');
INSERT INTO `borrow_records` VALUES ('bd335fcb-a2c6-4dbe-817a-972963a5978b', '92ce0faa-3bde-4df1-82aa-69991773dbab', '02b68328-2337-41ca-969d-ba19326faecc', '2020-10-21 21:02:47', '2020-10-22 17:12:38', '2');
INSERT INTO `borrow_records` VALUES ('be4cec78-c48f-49ca-8f05-5728dabeb91d', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '03fb7786-6f82-496c-b157-adb8e904fbbf', '2021-05-22 09:42:21', '2021-06-21 09:42:21', '0');
INSERT INTO `borrow_records` VALUES ('bf939ccc-9896-4300-95a8-55e92c56ec3c', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '6c03ad91-defd-47a3-8a0c-f603e54716be', '2021-05-07 10:54:37', '2021-06-06 10:54:37', '0');
INSERT INTO `borrow_records` VALUES ('c6d4430e-3f53-45b0-a4f8-782c0fceb04b', '92ce0faa-3bde-4df1-82aa-69991773dbab', '045740ce-efb9-4a51-8e77-6970f47e8748', '2020-09-13 11:27:37', '2020-10-13 11:27:37', '1');
INSERT INTO `borrow_records` VALUES ('c80b3913-1b17-48e4-80e2-9be1f595e075', 'ba05892f-4403-48f2-9c36-1de685d5f3fc', '0157096e-b13f-4860-911e-20efbcd5f5df', '2021-04-23 00:38:48', '2021-05-23 00:38:48', '0');
INSERT INTO `borrow_records` VALUES ('dd3a7b86-fb74-45e4-b43f-2da8579a8c33', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '03fb7786-6f82-496c-b157-adb8e904fbbf', '2021-04-23 08:08:31', '2021-05-23 08:08:31', '0');
INSERT INTO `borrow_records` VALUES ('df92e91b-889a-4ff1-ba79-8f012841e05d', '92ce0faa-3bde-4df1-82aa-69991773dbab', '045740ce-efb9-4a51-8e77-6970f47e8748', '2020-10-20 17:43:29', '2020-10-21 21:12:23', '2');
INSERT INTO `borrow_records` VALUES ('f6b0a7dc-b4ad-417d-80f6-5447e4dc960d', '92ce0faa-3bde-4df1-82aa-69991773dbab', '9e9b9db8-7ba3-49a5-ba98-1a663de5ba52', '2020-10-22 22:43:19', '2020-11-21 22:43:19', '0');
INSERT INTO `borrow_records` VALUES ('fb270345-0216-418a-80ef-1b73ea7011af', '92ce0faa-3bde-4df1-82aa-69991773dbab', 'c14b2068-a9a9-4ed3-a0bb-c0eaba63247d', '2020-10-22 16:41:47', '2020-10-22 16:43:20', '2');

-- ----------------------------
-- Table structure for borrow_status
-- ----------------------------
DROP TABLE IF EXISTS `borrow_status`;
CREATE TABLE `borrow_status` (
  `id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_status
-- ----------------------------
INSERT INTO `borrow_status` VALUES ('0', '借出中');
INSERT INTO `borrow_status` VALUES ('1', '已超时');
INSERT INTO `borrow_status` VALUES ('2', '已归还');

-- ----------------------------
-- Table structure for classifications
-- ----------------------------
DROP TABLE IF EXISTS `classifications`;
CREATE TABLE `classifications` (
  `id` int(11) NOT NULL,
  `classify_char` varchar(255) NOT NULL,
  `classify_num` int(11) DEFAULT NULL,
  `classify_books` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classifications
-- ----------------------------
INSERT INTO `classifications` VALUES ('0', 'A', null, '马克思主义、列宁主义、毛泽东思想、邓小平理论');
INSERT INTO `classifications` VALUES ('1', 'B', null, '哲学、宗教');
INSERT INTO `classifications` VALUES ('2', 'C', null, '社会科学总论');
INSERT INTO `classifications` VALUES ('3', 'D', null, '政治、法律');
INSERT INTO `classifications` VALUES ('4', 'E', null, '军事');
INSERT INTO `classifications` VALUES ('5', 'F', null, '经济');
INSERT INTO `classifications` VALUES ('6', 'G', null, '文化、科学、教育、体育');
INSERT INTO `classifications` VALUES ('7', 'H', null, '语言、文字');
INSERT INTO `classifications` VALUES ('8', 'I', null, '文学');
INSERT INTO `classifications` VALUES ('9', 'J', null, '艺术');
INSERT INTO `classifications` VALUES ('10', 'K', null, '历史、地理');
INSERT INTO `classifications` VALUES ('11', 'N', null, '自然科学总论');
INSERT INTO `classifications` VALUES ('12', 'O', null, '数理科学与化学');
INSERT INTO `classifications` VALUES ('13', 'P', null, '天文学、地球科学');
INSERT INTO `classifications` VALUES ('14', 'Q', null, '生物科学');
INSERT INTO `classifications` VALUES ('15', 'R', null, '医药、卫生');
INSERT INTO `classifications` VALUES ('16', 'S', null, '农业科学');
INSERT INTO `classifications` VALUES ('17', 'T', null, '工业技术');
INSERT INTO `classifications` VALUES ('18', 'U', null, '交通运输');
INSERT INTO `classifications` VALUES ('19', 'V', null, '航空、航天');
INSERT INTO `classifications` VALUES ('20', 'X', null, '环境科学、安全科学');
INSERT INTO `classifications` VALUES ('21', 'Z', null, '综合性图书');
INSERT INTO `classifications` VALUES ('22', 'A', '1', '马克思、恩格斯著作');
INSERT INTO `classifications` VALUES ('23', 'A', '2', '列宁著作');
INSERT INTO `classifications` VALUES ('24', 'A', '3', '斯大林著作');
INSERT INTO `classifications` VALUES ('25', 'A', '4', '毛泽东著作');
INSERT INTO `classifications` VALUES ('26', 'A', '5', '马克思、恩格斯、列宁、斯大林、毛泽东、邓小平著作汇编');
INSERT INTO `classifications` VALUES ('27', 'A', '7', '马克思、恩格斯、列宁、斯大林、毛泽东、邓小平生平和传记');
INSERT INTO `classifications` VALUES ('28', 'A', '8', '马克思主义、列宁主义、毛泽东思想、邓小平理论的学习和研究');
INSERT INTO `classifications` VALUES ('29', 'B', '0', '哲学理论');
INSERT INTO `classifications` VALUES ('30', 'B', '1', '世界哲学');
INSERT INTO `classifications` VALUES ('31', 'B', '2', '中国哲学');
INSERT INTO `classifications` VALUES ('32', 'B', '8', '思维科学');
INSERT INTO `classifications` VALUES ('33', 'C', '0', '社会科学理论与方法论');
INSERT INTO `classifications` VALUES ('34', 'C', '1', '社会科学现状及发展');
INSERT INTO `classifications` VALUES ('35', 'C', '9', '社会学');
INSERT INTO `classifications` VALUES ('36', 'D', '6', '中国政治');
INSERT INTO `classifications` VALUES ('37', 'D', '9', '法律');
INSERT INTO `classifications` VALUES ('38', 'E', '1', '世界军事');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `discuss_id` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `publish_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1098918e-1f21-4438-972b-be917c6g529c', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '1098918e-1f21-4438-972b-be917c6b529c', '一切物体，包括动物身体在内，体积的增量是长度增量的立方倍；而一只动物全身所能生成的热量，与其身体大小是成正比的；因此，体热的增长量，也是身体长度增量的立方倍。而在热量流失时，表面积的增量只是长度增量的平方倍。小动物的体温下降速度之所以更快，是因为按照比例来说，它们的体表面积远远大于身体体积。', '2021-05-14 11:02:39');
INSERT INTO `comments` VALUES ('2ff03fbc-c4e4-4c9c-9c92-841d78e6f3a3', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '1098919e-1f21-4438-972b-be917c6b529c', '我这里先推荐一本《数字电子技术 : 第四版》', '2021-05-14 09:59:23');
INSERT INTO `comments` VALUES ('2ff03fqs-c4e4-4c9c-9c92-841d78e6f3a3', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '1098919e-1f21-4438-972b-be917c6b529c', '《C++程序设计语言（第1—3部分）（原书第4版）》这本书对于入门是个不错的选择', '2021-05-14 10:04:51');
INSERT INTO `comments` VALUES ('c2852b0a-a7fc-48a9-a99d-1cfb0131bdaf', '92ce0faa-3bde-4df1-82aa-69991773dbab', '1098920e-1f21-4438-972b-be917c6b529c', '先去前台说明一下吧', '2021-05-24 11:15:18');

-- ----------------------------
-- Table structure for discusses
-- ----------------------------
DROP TABLE IF EXISTS `discusses`;
CREATE TABLE `discusses` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `publish_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discusses
-- ----------------------------
INSERT INTO `discusses` VALUES ('1098915e-1f21-4438-972b-be917c6b529c', 'ba05892f-4403-48f2-9c36-1de685d5f3fc', '欢迎来到讨论区', '欢迎来到讨论区，请合规发言，遵守相关法律法规秩序', '2021-05-10 19:35:54');
INSERT INTO `discusses` VALUES ('1098916e-1f21-4438-972b-be917c6b529c', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '新人建议', '欢迎各位来到讨论区，下列为一些新人需要注意的点和建议', '2021-05-11 15:51:12');
INSERT INTO `discusses` VALUES ('1098917e-1f21-4438-972b-be917c6b529c', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '求书籍推荐', '对计算机基础有一定的兴趣，想求各位推荐一些图书供学习', '2021-05-12 16:00:56');
INSERT INTO `discusses` VALUES ('1098918e-1f21-4438-972b-be917c6b529c', '7572249b-386a-4ef6-82bb-7cb9f2e06986', '图书推荐《看不见的森林 : 林中自然笔记》', '近几天读了这本书，觉得很不错，特地推荐给大家', '2021-05-13 16:03:30');
INSERT INTO `discusses` VALUES ('1098919e-1f21-4438-972b-be917c6b529c', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '【书籍推荐】推荐阅读', '大家可以把自己推荐的一些书籍评论在本帖下方供其他同学学习参考', '2021-05-14 08:16:34');
INSERT INTO `discusses` VALUES ('1098920e-1f21-4438-972b-be917c6b529c', '92ce0faa-3bde-4df1-82aa-69991773dbab', '关于借阅的一点疑问', '不小心把借来的书弄丢了，请问该怎么办', '2021-05-11 16:31:53');

-- ----------------------------
-- Table structure for fine_records
-- ----------------------------
DROP TABLE IF EXISTS `fine_records`;
CREATE TABLE `fine_records` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `borrow_id` varchar(255) NOT NULL,
  `return_id` varchar(255) NOT NULL,
  `days` int(11) NOT NULL,
  `fine` double(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fine_records
-- ----------------------------
INSERT INTO `fine_records` VALUES ('0acfe137-17d4-4f01-81c2-5e12d398bc90', '92ce0faa-3bde-4df1-82aa-69991773dbab', 'bd335fcb-a2c6-4dbe-817a-972963a5978b', '719d288e-62a2-43d0-b829-8e18e5b4fe73', '0', '0.00');
INSERT INTO `fine_records` VALUES ('9e8b2aff-829d-4ad9-a7f7-09dbc8ffa8c9', '92ce0faa-3bde-4df1-82aa-69991773dbab', '8ca67071-ba53-4c58-b8df-2c4273a297c5', '1098915e-1f21-4438-972b-be917c6b529c', '30', '8.40');

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `receive_date` datetime NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES ('0157096e-b13f-4860-911e-20efbad5f5df', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '欢迎使用本系统', '欢迎使用本中山图书馆社区系统，有任何问题与建议可以在讨论区中提出', '2021-05-01 10:32:56', '1');
INSERT INTO `messages` VALUES ('0157096e-b13f-4860-911e-20efbed6f7df', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '借阅到期提醒', '您借阅的书籍《数字电子技术 : 第四版》已超过归还日期，请及时归还', '2021-05-01 10:56:33', '0');
INSERT INTO `messages` VALUES ('0157096e-b13f-4860-911e-20efbff6f7df', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '借阅到期提醒', '您借阅的书籍《雅思王阅读真题语料库》已超过归还日期，请及时归还', '2021-05-02 10:56:33', '0');
INSERT INTO `messages` VALUES ('0157096e-b13f-4860-911e-20efbff6f8df', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '借阅到期提醒', '您借阅的书籍《看不见的森林 : 林中自然笔记》已超过归还日期，请及时归还', '2021-05-03 10:56:33', '0');
INSERT INTO `messages` VALUES ('0168155e-b13f-4860-911e-20efbff6f8df', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '借阅到期提醒', '您借阅的书籍《Excel VBA程序开发自学宝典》已超过归还日期，请及时归还', '2021-05-04 10:56:33', '0');
INSERT INTO `messages` VALUES ('0169195e-b13f-4860-911e-20efbff6f8df', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '借阅到期提醒', '您借阅的书籍《C程序设计语言 第2版·新版》已超过归还日期，请及时归还', '2021-05-05 10:56:33', '0');
INSERT INTO `messages` VALUES ('32100fb2-401d-4ce3-a2a7-3d8b45f8a448', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '书评审核提醒', '您为书籍《世界桥梁趣谈》撰写的书评《桥》已发起重审，请耐心等待', '2021-05-24 11:14:05', '0');
INSERT INTO `messages` VALUES ('84420851-a3b4-4f74-8068-6b1f95f51b1c', '33989c2c-3e6b-432e-93b9-ce411b15576a', '欢迎使用本系统', '欢迎使用本中山图书馆社区系统，有任何问题与建议可以在讨论区中提出', '2021-05-24 11:13:06', '1');
INSERT INTO `messages` VALUES ('8b63d06f-082d-4403-96dd-b12a174786c6', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '书评审核提醒', '您为书籍《世界桥梁趣谈》撰写的书评《桥》已通过审核', '2021-05-24 11:14:04', '0');
INSERT INTO `messages` VALUES ('942d7157-0363-4705-a166-650491562ab0', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '书评审核提醒', '您为书籍《世界桥梁趣谈》撰写的书评《桥》已发起重审，请耐心等待', '2021-05-24 11:14:03', '0');
INSERT INTO `messages` VALUES ('b5409d8a-6a60-4174-ba02-c90d1e8edbd3', '92ce0faa-3bde-4df1-82aa-69991773dbab', '新回复提醒', '您的帖子《关于借阅的一点疑问》有新的回复了，请前往查看', '2021-05-24 11:15:18', '0');
INSERT INTO `messages` VALUES ('ef79d04e-3614-42f8-b95a-ec31afa835c0', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '书评审核提醒', '您为书籍《世界桥梁趣谈》撰写的书评《桥》未能通过审核', '2021-05-24 11:14:05', '0');

-- ----------------------------
-- Table structure for message_status
-- ----------------------------
DROP TABLE IF EXISTS `message_status`;
CREATE TABLE `message_status` (
  `id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_status
-- ----------------------------
INSERT INTO `message_status` VALUES ('0', '未读');
INSERT INTO `message_status` VALUES ('1', '已读');

-- ----------------------------
-- Table structure for return_records
-- ----------------------------
DROP TABLE IF EXISTS `return_records`;
CREATE TABLE `return_records` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `borrow_id` varchar(255) NOT NULL,
  `return_date` datetime NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of return_records
-- ----------------------------
INSERT INTO `return_records` VALUES ('1098915e-1f21-4438-972b-be917c6b529c', '92ce0faa-3bde-4df1-82aa-69991773dbab', '8ca67071-ba53-4c58-b8df-2c4273a297c5', '2020-10-14 09:54:26', '1');
INSERT INTO `return_records` VALUES ('116053c6-bdd3-4bb5-939e-e9beb9d10faf', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '0aa5d45f-df38-4be0-bb06-9b436c805f7c', '2021-04-23 08:10:34', '0');
INSERT INTO `return_records` VALUES ('18249b6c-1e25-4dc2-86c0-05da8091bc12', '92ce0faa-3bde-4df1-82aa-69991773dbab', 'fb270345-0216-418a-80ef-1b73ea7011af', '2020-10-22 16:43:20', '0');
INSERT INTO `return_records` VALUES ('2fc298d5-d9ea-4207-9697-7b1e91d0aa85', '92ce0faa-3bde-4df1-82aa-69991773dbab', '7b0419df-c920-417e-a726-702f924c0e3f', '2020-10-14 09:53:28', '0');
INSERT INTO `return_records` VALUES ('3d01da07-f13d-4ade-a8f2-57a208eab76e', '92ce0faa-3bde-4df1-82aa-69991773dbab', '6e3274cb-c7e4-4bf1-8e81-9390b063f3a8', '2021-04-23 08:11:00', '0');
INSERT INTO `return_records` VALUES ('43dde036-1a94-467c-ac44-4c4862d4b037', '92ce0faa-3bde-4df1-82aa-69991773dbab', '5a481cbf-9b63-478d-bf00-f3f857036c61', '2020-10-14 09:52:07', '1');
INSERT INTO `return_records` VALUES ('5b29721e-876a-4c62-b510-a8d9313cd2c7', '92ce0faa-3bde-4df1-82aa-69991773dbab', '3cbf51a1-067e-4deb-bc5e-16c22425b46f', '2020-10-21 21:12:49', '0');
INSERT INTO `return_records` VALUES ('5b6fb93e-753d-43b8-8db0-7f86671f1c1f', '92ce0faa-3bde-4df1-82aa-69991773dbab', 'df92e91b-889a-4ff1-ba79-8f012841e05d', '2020-10-21 21:12:23', '0');
INSERT INTO `return_records` VALUES ('70d8b136-53af-4751-9246-af38f68adcf3', '92ce0faa-3bde-4df1-82aa-69991773dbab', '42234475-a519-4555-b91f-cd2519cf42bb', '2020-10-14 10:03:06', '1');
INSERT INTO `return_records` VALUES ('719d288e-62a2-43d0-b829-8e18e5b4fe73', '92ce0faa-3bde-4df1-82aa-69991773dbab', 'bd335fcb-a2c6-4dbe-817a-972963a5978b', '2020-10-22 17:12:38', '1');
INSERT INTO `return_records` VALUES ('760d3728-a665-46d8-ac8b-0cb3dcb553c2', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '741721f1-cb7f-4d5b-9755-6edc0cdb5674', '2021-05-20 09:39:11', '0');
INSERT INTO `return_records` VALUES ('76cb80ec-6e58-4831-a62c-ce66d17eadac', '92ce0faa-3bde-4df1-82aa-69991773dbab', '1462813a-b9d7-46b1-aecf-54ba91db5fd9', '2020-10-14 09:45:39', '0');
INSERT INTO `return_records` VALUES ('9740a26b-ffbb-4a43-81f8-2d5c3a71a3a4', '92ce0faa-3bde-4df1-82aa-69991773dbab', '3cb13a08-5502-4195-bd0c-5ac83ebbeaaa', '2020-10-14 09:47:04', '0');
INSERT INTO `return_records` VALUES ('c4c841b8-f88f-486b-9f13-14b945f26d83', '92ce0faa-3bde-4df1-82aa-69991773dbab', '31985c53-413b-491a-9ae7-ea17dd47c7e9', '2020-10-22 22:47:48', '0');
INSERT INTO `return_records` VALUES ('c62d1bac-a40e-48bb-9ddf-e0c987c53499', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '5122cc6c-9f03-434c-bfc5-2ad0971ee8e2', '2021-04-23 00:34:01', '0');
INSERT INTO `return_records` VALUES ('cd5c10a1-3e35-4c79-a9ec-1788f038c26d', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '154797e1-1b04-4bc6-94ad-f197e6f01397', '2020-10-22 22:48:09', '0');
INSERT INTO `return_records` VALUES ('dfc52305-a977-4aaf-bfbd-c53b125fda09', 'ba05892f-4403-48f2-9c36-1de685d5f3fc', '0459f09f-d679-452a-8f42-4e444f5ee1ca', '2021-04-23 08:10:30', '0');
INSERT INTO `return_records` VALUES ('e214bcca-d127-4e81-b20c-9923d33a1cba', 'ba05892f-4403-48f2-9c36-1de685d5f3fc', '11e6c956-c2b9-4372-b99e-69233af7547d', '2021-05-07 10:51:42', '0');
INSERT INTO `return_records` VALUES ('e56ace01-0fbe-440f-addc-d83810a512b8', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '936ac57e-cbdb-4119-a56b-f21bb42acc6d', '2021-05-20 09:39:17', '0');

-- ----------------------------
-- Table structure for return_status
-- ----------------------------
DROP TABLE IF EXISTS `return_status`;
CREATE TABLE `return_status` (
  `id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of return_status
-- ----------------------------
INSERT INTO `return_status` VALUES ('0', '按时归还');
INSERT INTO `return_status` VALUES ('1', '超时归还');

-- ----------------------------
-- Table structure for review_status
-- ----------------------------
DROP TABLE IF EXISTS `review_status`;
CREATE TABLE `review_status` (
  `id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of review_status
-- ----------------------------
INSERT INTO `review_status` VALUES ('0', '待审核');
INSERT INTO `review_status` VALUES ('1', '已过审');
INSERT INTO `review_status` VALUES ('2', '未过审');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', '普通用户');
INSERT INTO `role` VALUES ('1', '管理员');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  `borrowed` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '0',
  `balance` double(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1e70b144-614a-4a5d-badb-9bbdc4e3c180', 'aaa123', '5b4f52d6aa7de0a10a9cecb91ef51454', '李四', '0', '3', '0', '3.20');
INSERT INTO `users` VALUES ('2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', 'abc', '1632cab5305ea5363422b3afb8089f43', '123456', '0', '5', '2', '0.00');
INSERT INTO `users` VALUES ('33989c2c-3e6b-432e-93b9-ce411b15576a', 'test2', '77fd9405c0f0e2e49349bc43f93bd2b7', 'ttttt', '0', '0', '0', '0.00');
INSERT INTO `users` VALUES ('7572249b-386a-4ef6-82bb-7cb9f2e06986', 'bbb', 'e1ec7d80057ed6a94908f5625dc991a9', '王五', '0', '0', '0', '25.20');
INSERT INTO `users` VALUES ('92ce0faa-3bde-4df1-82aa-69991773dbab', 'aaa', 'afecb53f1f5315531ba8661a93d7c400', '张三', '0', '3', '0', '31.81');
INSERT INTO `users` VALUES ('9f6ea0ec-984a-4887-bbf5-36ac8bac7154', 'test', '2f2e16e81bd2939cb46166c30ade1038', 'test1', '0', '0', '0', '0.00');
INSERT INTO `users` VALUES ('a26214da-4c14-4637-9470-daff75b1c34f', 'ccc', 'e036b035fefbd2197bcd26eb272640ad', '赵六', '0', '0', '0', '15.10');
INSERT INTO `users` VALUES ('a2dfe9db-47ed-464d-9b38-9414bca85612', 'ddd', '887644eae2b82d048d5697d2cee96db4', '大宝', '0', '0', '1', '-1.00');
INSERT INTO `users` VALUES ('ba05892f-4403-48f2-9c36-1de685d5f3fc', 'admin', '1266d4731b7705ef700e5327de1fa925', '管理员', '1', '3', '0', '10.00');
INSERT INTO `users` VALUES ('eb5e2b5a-f165-4071-a934-2df0b3c9af61', 'test1', 'b7885a1ed2b32fae0e6e518f1a403a8a', 'test1', '0', '0', '3', '0.00');

-- ----------------------------
-- Table structure for user_booklist
-- ----------------------------
DROP TABLE IF EXISTS `user_booklist`;
CREATE TABLE `user_booklist` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `list_name` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  `books` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_booklist
-- ----------------------------
INSERT INTO `user_booklist` VALUES ('1462583a-b9d7-46b1-aecf-47ba91db5fd9', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', '计算机入门', '推荐了部分计算机入门需要看的书籍', '0157096e-b13f-4860-911e-20efbcd5f5df,02810cef-82e5-44c8-b69b-222df8043d12,02b68328-2337-41ca-969d-ba19326faecc,03fb7786-6f82-496c-b157-adb8e904fbbf,045740ce-efb9-4a51-8e77-6970f47e8748,');
INSERT INTO `user_booklist` VALUES ('1462813a-b9d7-46b1-aecf-47ba91db5fd9', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '我的书单', '我的第一个书单', '028cef-82e5-44c8-b69b-222df8043d12,0157096e-b13f-4860-911e-20efbcd5f5df');
INSERT INTO `user_booklist` VALUES ('1462813a-b9d7-46b1-aecf-47ca13db5fd9', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '空书单', '', '');
INSERT INTO `user_booklist` VALUES ('1489253a-b9d7-46b1-aecf-47ba91db5fd9', '2ff03fbc-c4e4-4c9c-9c92-841d78e6f2b3', 'C++入门', 'C++入门所需要学习的内容', '05cf259f-62e5-4512-9e72-44973090e4af,0ba06ded-0612-419a-8b4e-ae0bdfc61729,0ecdf9b2-2549-4fe3-9a08-8ae9ff1b936d,14315e47-c398-47d1-b297-2ac8401dc14e,2257cbed-e9f3-4207-a409-54879296169b');
INSERT INTO `user_booklist` VALUES ('9a89a47b-c7e3-4551-a8e9-0828568a1e9c', '7572249b-386a-4ef6-82bb-7cb9f2e06986', '经典书目合集', '收录个人比较喜欢的经典图书，欢迎访问', '8faec636-9699-4ade-bb94-f6393c8109be,3f0314cf-10e9-468c-a32c-09010535e127.386ea8ff-94f5-4557-adf6-b711035aaf57');
INSERT INTO `user_booklist` VALUES ('9a89a47b-c7e3-4551-a8e9-0828758a1e9c', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', '满书单', '', '0157096e-b13f-4860-911e-20efbcd5f5df,02810cef-82e5-44c8-b69b-222df8043d12,02b68328-2337-41ca-969d-ba19326faecc,03fb7786-6f82-496c-b157-adb8e904fbbf,045740ce-efb9-4a51-8e77-6970f47e8748,05cf259f-62e5-4512-9e72-44973090e4af,0ba06ded-0612-419a-8b4e-ae0bdfc61729,0ecdf9b2-2549-4fe3-9a08-8ae9ff1b936d,14315e47-c398-47d1-b297-2ac8401dc14e,2257cbed-e9f3-4207-a409-54879296169b');
INSERT INTO `user_booklist` VALUES ('cd1a6031-1676-496f-bfb0-80e40c022e13', '1e70b144-614a-4a5d-badb-9bbdc4e3c180', 'MyBookList', 'MyBookList is here', '0157096e-b13f-4860-911e-20efbcd5f5df');

-- ----------------------------
-- Table structure for user_status
-- ----------------------------
DROP TABLE IF EXISTS `user_status`;
CREATE TABLE `user_status` (
  `id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_status
-- ----------------------------
INSERT INTO `user_status` VALUES ('0', '正常');
INSERT INTO `user_status` VALUES ('1', '欠费');
INSERT INTO `user_status` VALUES ('2', '达到上限');
INSERT INTO `user_status` VALUES ('3', '冻结');

-- ----------------------------
-- Procedure structure for borrow_checked
-- ----------------------------
DROP PROCEDURE IF EXISTS `borrow_checked`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrow_checked`()
BEGIN
	UPDATE borrow_records
	SET `status` = 1
	WHERE
		TO_DAYS(now()) - TO_DAYS(return_date) >= 0
	AND `status` = 0 ;
END
;;
DELIMITER ;

-- ----------------------------
-- Event structure for checked
-- ----------------------------
DROP EVENT IF EXISTS `checked`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `checked` ON SCHEDULE EVERY 1 SECOND STARTS '2020-10-14 11:36:06' ON COMPLETION PRESERVE DISABLE DO call borrow_checked()
;;
DELIMITER ;
