<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <button onclick="showMessage()">按钮</button>
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
