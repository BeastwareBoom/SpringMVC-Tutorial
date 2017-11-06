#Spring.MVC-A.Tutorial 案例应用

##第五章 数据绑定 form标签

##第六章 Converter和Formatter/Registrar

##第七章 Validator验证器 & JSR303Validator 
JSR303是一套正式的java规范，需要实现，通过标注类型嵌入约束，而不需要编写验证器
如：@Size表示该属性值必须在指定范围内，@Size(min=1,max=10)，本例来自Hibernate Validartor库

##第八章 EL表达式语言
EL表达式语言是JSP2.0的最重要特性之一，可以用来访问应用程序数据。
JSTL，JSP标准标签库。

EL隐式对象：pageContext（当前JSP页面的PageContext），initParam，param，header
以下四个对象均为Map，属性名称作为key：
    |applicationScope，ServletContext中的所有属性，用于获取应用程序范围级变量的值
    |sessionScope，HttpSession中的所有属性
    |requestScope，当前HttpServletRequest中的所有属性
    |pageScope，页面范围内的所有属性

在以上四个对象中作为属性的对象，称为有界对象。

pageContext所有属性:
request->HttpServletRequest
reponse->HttpServletReponse
out->JspWriter
session->HttpSession
application->ServletContext
config->ServletConfig
page->HttpJspPage
exception->Throwable

##第九章 JSTL语言
JavaServer Pages Standard Tag Language，JSTL是标准标签库，通过多个标签来暴露其行为。

几个重要标签：c:if,c:forEach,fmt:formatDate

9.8 函数（略）

##第十章 国际化

##第十一章 上传文件
文件上传需要依赖commons-fileupload和commons-io组件，在配置文件中配置：
CommonsMultipartResolver
MultipartFile接口，两个关键方法：
String getOriginalFileName();//获取源文件名称
void transferTo(String destination);//将上传文件保存至指定目录下

todo: 11-9: