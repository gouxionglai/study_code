<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="messageDiv" class="messageDiv" style="display: none;">

    <div class="actionMessage">
        <c:if test="${ actionMessage != null}">
            ${actionMessage}
        </c:if>
    </div>

</div>

<form action="demo/upload" method="post" enctype="multipart/form-data">
    <p>上传：<input type="file" name="file" /></p>
    <p>sex: <input type="text" name="sex" /></p>
    <input type="submit" value="Submit" />
</form>