<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>错误提示</title>
  <style>
    html{
      font-size:16px
    }
    @media screen and (max-width:320px){html{font-size:8px;}
    }
    @media (min-width:321px) and (max-width:640px){html{font-size:9.2px;}
    }
    @media (max-width:641px) and (min-width:720px){html{font-size:13px;}}

    @media screen and (min-width:768px){html{font-size:16px;}}

    body,html{
      background-color: #fff;
      height: 100%;
    }
    .notfind{
      width: 100%;
      position: absolute;
      top:50%;
      left:50%;
      -webkit-transform: translate(-50%, -50%);
      -moz-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
      -o-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
      text-align: center;
    }
    .notfind img{
      width: 80%;
    }
    .notfind a{
      background-color: #fff;
      border: 1px solid #1c828d;
      border-radius: 20px;
      width:10rem;
      height:5.625rem;
      text-align: center;
      display: inline-block;
      font-size: 2rem;
      margin-top: 3rem;
      line-height: 5.625rem;
      color:#1c828d ;
      text-decoration: none;
      padding: 0 40px;
    }
    .notfind span{
      display: inline-block;
      color: #92c6c6;
      font-size: 2.5rem;
    }
  </style>
</head>
<body>
<div class="notfind">
  <img src="<%=picPath %>zefun/error/500.png"/>
  <c:choose>
    <c:when test="${empty tip }">
        <span>出错啦，返回主页再试试吧</span>
    </c:when>
    <c:otherwise>
        <span>${tip }</span>
    </c:otherwise>
  </c:choose>
  <br/>
  <a href="javascript:back();">回到主页</a>
</div>
<script type="text/javascript">
function back(){
	if (WeixinJSBridge) {
		WeixinJSBridge.call('closeWindow');
	}
	else {
		window.location.href = "<%=basePath%>";
	}
}
</script>
</body>
</html>