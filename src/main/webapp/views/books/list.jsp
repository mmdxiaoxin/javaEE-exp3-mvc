<%--
  Created by IntelliJ IDEA.
  User: mmdxiaoxin
  Date: 2023/11/10
  Time: 14:22
  显示图书列表的页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 这是导入jstl（jsp标准标记库）的语句，导入之后，你就可以使用jstl中的标签了 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书信息详细列表</title>
    <!-- 连接css文件，href为文件路径,{pageContext.request.contextPath}是JSP取得绝对路径的意思 -->
    <!-- rel="stylesheet" 描述了当前页面与href所指定文档的关系.即说明的是,href连接的文档是一个新式表 -->
    <link rel="stylesheet" href="./css/book.css">
    <!-- 连接JavaScript文件，src为文件路径 -->
    <script type="text/javascript" src="./js/book.js"></script>
</head>
<body>
<!-- 和css文件链接 -->
<table id="showTab">
    <tr>
        <!-- colspan属性代表所占列数 -->
        <th colspan="8" id="showHeader">图书信息详细列表</th>
    </tr>
    <tr>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>出版日期</th>
        <th>页数</th>
        <th>价格</th>
        <th>内容摘要</th>
        <th>操作</th>
    </tr>
    <!-- forEach标签用于对一个集合对象中的元素进行循环迭代操作，或者按指定的次数重复迭代执行标签体中的内容。 -->
    <!-- 变量book为result.content的值，stat.index输出行号，从0开始,varStatus属性可以方便我们实现一些与行数相关的功能-->
    <c:forEach items="${result.content}" var="book" varStatus="stat">
        <tr ${stat.index%2==0 ? 'class=odd' : ''}>
            <th>${book.name}</th>
            <th>${book.author}</th>
            <th>${book.publish}</th>
            <th>${book.publishdate}</th>
            <th>${book.page}</th>
            <th>${book.price}</th>
            <th>${book.content}</th>
            <th>
                <!-- href 属性的值可以是任何有效文档的相对或绝对 URL，包括片段标识符和 JavaScript 代码段。如果用户选择了 <a> 标签中的内容，
                   那么浏览器会尝试检索并显示 href 属性指定的 URL 所表示的文档，或者执行 JavaScript 表达式、方法和函数的列表。 -->
                <a href="${pageContext.request.contextPath}/BookServlet?type=getById&id=${book.id}">修改</a>
                <a href="${pageContext.request.contextPath}/BookServlet?type=deleteById&id=${book.id}">删除</a>
            </th>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8" id="tdBut">
            <!-- οnclick="javascript:window.location.href  点击后跳转到 -->
            <input type="button" value="继续添加"
                   onclick="javascript:window.location.href='${pageContext.request.contextPath}/BookServlet?type=goAdd'">&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/BookServlet?type=list&pageNum=${result.firstPage}&pageSize=${result.pageSize}">首页</a>
            <a href="${pageContext.request.contextPath}/BookServlet?type=list&pageNum=${result.prePage}&pageSize=${result.pageSize}">上一页</a>
            <a href="${pageContext.request.contextPath}/BookServlet?type=list&pageNum=${result.nextPage}&pageSize=${result.pageSize}">下一页</a>
            <a href="${pageContext.request.contextPath}/BookServlet?type=list&pageNum=${result.lastPage}&pageSize=${result.pageSize}">尾页</a>
            总共${result.totalSize}条，共${result.pageNum}/${result.totalPages}页，每页显示<input id="pageSize"
                                                                                                onchange="changePageSize(this.value)"
                                                                                                type="number"
                                                                                                value="${result.pageSize}">条
            跳转到<input id="goPageNum" type="number" value="${result.pageNum}">页<input type="button" value="go"
                                                                                         onclick="goPageNum('${result.pageSize}')">
        </td>
        </td>
    </tr>

</table>
</body>
</html>

