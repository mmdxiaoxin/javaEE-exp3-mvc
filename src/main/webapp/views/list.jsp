<%--
  Created by IntelliJ IDEA.
  User: mmdxiaoxin
  Date: 2023/11/10
  Time: 14:22
  显示图书列表的页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书信息详细列表</title>
    <style>
        td, th {
            border: 1px solid #ccc;
        }

        #tdBut {
            text-align: center;
        }

        #showTab {
            border: 1px solid #ccc;
            width: 800px;
            margin: auto;
            border-collapse: collapse;
        }

        #pageSize, #goPageNum {
            width: 60px;
        }

        #showHeader {
            background-color: #ffe8cc;
            font-size: 60px;
        }

        tr[class='odd'] {
            background-color: #DDA0DD;
        }

    </style>
</head>
<body>
<table id="showTab">
    <tr>
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
                <a href="${pageContext.request.contextPath}/book-ctrl?action=edit&id=${book.id}">修改</a>
                <a href="${pageContext.request.contextPath}/book-ctrl?action=delete&id=${book.id}">删除</a>
            </th>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8" id="tdBut">
            <!-- οnclick="javascript:window.location.href  点击后跳转到 -->
            <input type="button" value="继续添加"
                   onclick="javascript:window.location.href='${pageContext.request.contextPath}/BookServlet?type=goAdd'">&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/book-ctrl?action=list&pageNum=${result.firstPage}&pageSize=${result.pageSize}">首页</a>
            <a href="${pageContext.request.contextPath}/book-ctrl?action=list&pageNum=${result.prePage}&pageSize=${result.pageSize}">上一页</a>
            <a href="${pageContext.request.contextPath}/book-ctrl?action=list&pageNum=${result.nextPage}&pageSize=${result.pageSize}">下一页</a>
            <a href="${pageContext.request.contextPath}/book-ctrl?action=list&pageNum=${result.lastPage}&pageSize=${result.pageSize}">尾页</a>
            总共${result.totalSize}条，共${result.pageNum}/${result.totalPages}页，每页显示<input id="pageSize"
                                                                                                onchange="changePageSize(this.value)"
                                                                                                type="number"
                                                                                                value="${result.pageSize}">条
            跳转到<input id="goPageNum" type="number" value="${result.pageNum}">页<input type="button" value="go"
                                                                                         onclick="goPageNum('${result.pageSize}')">
        </td>
    </tr>
</table>
</body>
<script>
    function changePageSize(val) {
        window.location.href = "/exp3/BookServlet?type=list&pageSize=" + val;
    }

    function goPageNum(val) {
        //获取id为goPageNum的input里面的数值
        const num = document.getElementById("goPageNum").value;
        window.location.href = "/exp3/BookServlet?type=list&pageSize=" + val + "&pageNum=" + num;
    }

</script>
</html>

