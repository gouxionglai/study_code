<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>出错了</title>
    <%--<link rel="shortcut icon" href="/img/favicon.ico" />--%>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            font-family: "微软雅黑", serif;
            text-decoration: none;
            list-style: none;
        }

        #Boss {
            width: 1200px;
            margin: 0 auto;
            margin-top: 100px;
            /*	background-color: #999999;*/
        }

        #logo {
            width: 760px;
            height: 100px;
            margin: 0 auto;
        }

        #text p {
            width: 970px;
            height: 450px;
            font-size: 36px;
            font-weight: 600;
            /*background-color: chocolate;*/
            text-align: center;
            line-height: 450px;
        }

        #botton {
            height: 250px;
            margin-left: 180px;
            margin-top: -180px;
        }

        #botton li {
            margin: 80px;
            float: left;
        }
        #botton li p{
            height: 28px;
        }
        #botton li img{
            width: 28px;
            height: 28px;
            float: left;
        }
        #botton li a{
            font-size: 18px;
            color: #a3a3a3;
            line-height: 28px;
            margin-left: 20px;
            float: left;
        }

        /*底部*/

        #footer {
            height: 110px;
            width: 100%;
            margin-top: 80px;
            /*background-color: #015aac;*/
        }

        #footer_box {
            height: 25px;
        }

        #footer p {
            text-align: center;
            line-height: 30px;
            font-size: 12px;
            color: #A3A3A3;
        }
    </style>
</head>
<body>
<div id="Boss">
    <div id="text">
        <p>抱歉，你所访问的页面出错了</p>
    </div>
</div>
<!--底部-->
<div id="footer">
    <div id="footer_box">
    </div>

</div>
</body>
</html>