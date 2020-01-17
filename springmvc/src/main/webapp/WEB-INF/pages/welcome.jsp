<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>welcome</title>

</head>
<body>
<h2>hello world!! welcome </h2>
<h3>message: ${message}</h3>
<%--<h3>errorMessage: ${errorMessage}</h3>--%>
<h3><fmt:formatDate value="${current_time}" pattern="yyyy-MM-dd HH:mm:ss"/>"</h3>
<h3>userID：${user.id}</h3>
<h3>userName：${user.name}</h3>
<h3>userSex：${user.sex}</h3>
<h3>userAge：${user.age}
<h3>userDate：${user.birth}</h3>
</body>

</html>