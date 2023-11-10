<%--
  Created by IntelliJ IDEA.
  User: mmdxiaoxin
  Date: 2023/11/10
  Time: 14:22
  登录页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<!-- action 属性规定当提交表单时，向/UsersLoginServlet发送表单数据。使用Post传输的数据，可以通过设置编码的方式正确转化中文-->
<form action="${pageContext.request.contextPath}/UsersLoginServlet" method="post">
    <table id="login in">
        <tr>
            <td class="label">登录名:</td>
            <td class="inputContent"><label>
                <input type="text" name="username">
            </label></td>
        </tr>
        <tr>
            <td class="label">登录密码:</td>
            <td class="inputContent"><label>
                <input type="password" name="password">
            </label></td>
        </tr>
        <tr id="loginbut">
            <td colspan="2">
                <!-- <input type="submit"> 定义为一个提交按钮。提交按钮会把表单数据发送到服务器。 -->
                <input type="submit" value="登录">
                <!-- 这是表单重置按钮,点击后会将重置按钮所在的表单中填写的内容清空 -->
                <input type="reset" value="取消">
            </td>
        </tr>
        <tr id="loginbut1">
            <td colspan="2">
                <!-- <span>标签是行内元素标签，不分行 -->
                <span>&nbsp
                    <!-- 还没设置 -->
                    ${message}
                </span>
            </td>
        </tr>
    </table>
</body>
</html>

