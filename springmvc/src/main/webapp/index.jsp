<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">--%>

    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
<div id="progressDialogDivId" style="display: none; position: absolute;">
    <img alt="image" src="img/loaders/4.gif">
</div>
<div id="openModal" style="display: none;"></div>

<h2>Hello World!</h2>
<!--指定提交的时候编码方式-->
<form action="demo/test2" method="post" accept-charset="UTF-8">
    <p>name: <input type="text" name="name" /></p>
    <p>sex: <input type="text" name="sex" /></p>
    <p>age: <input type="text" name="age" /></p>
    <p>date: <input type="text" name="date" /></p>
    <input type="submit" value="Submit" />
</form>

<a href="demo/testCookie">test cookie</a>
<br/>

<a href="demo/testModelAttribute">test ModelAttribute</a>
<br/>

<button onclick="showMessage()">按钮</button>
<br/>
<button onclick="redirectPage('demo/to_upload')">跳转上传</button>
<br/>

<div id="main-content">
    <div class="container">
        <div class="row">
            <div id="content" class="col-lg-12">
                <jsp:include page="WEB-INF/pages/index_content.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </div>
    <div class="footer-tools">
                            <span class="go-top">
                                <i class="fa fa-chevron-up"></i> Top
                            </span>
    </div>
</div>
</body>

<script>
    function showMessage() {
        // alert("嘿嘿嘿");
        $.ajax({
            url: "demo/testAjax1",  //请求地址
            method: "post", //不写的话默认是get
            data: {name:"张三三",age:21,sex:0},    //多个参数用逗号隔开，里面是key:value的形式
            datatype: "json",  //预期返回的数据格式
            success:function(data){     //data代表服务器返回的数据
                //完成处理数据
                alert(data);
            }
        });
    }
    
</script>
</html>
