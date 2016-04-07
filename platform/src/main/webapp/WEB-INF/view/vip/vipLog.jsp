<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vip注册</title>
</head>
<body>
     <center>
       <h1>会员注册</h1>
          <form action="vipLogSubmit" method=post>
            <table border="1" bgcolor="#0099CC">
              <tr>
                 <td>        姓名:
                    <input type="text" name="vipName">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        手机号码：
                     <input type="text" name="vipMobile">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        性别：
                 </td>
                 <td>
                                                               男<input type="radio" name="sex" value="1">
                                                               女<input type="radio" name="sex" value="2">
                 </td>
              </tr>
               <tr valign="middle">
                 <td>        生日：
                     <input type="text" name="birthday" >
                 </td>
                 <td>        是否农历：
                     <input type="checkbox" name="isLunarCal" value="1">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        会员类型：
                     <input type="text" name="vipLevelId" >
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        系统卡号：
                     <input type="text" name="sysCardNum" >
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        会员卡号：
                     <input type="text" name="cardNo" >
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        卡金额：
                     <input type="text" name="cardAmount" >
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        支付密码：
                     <input type="password" name="cardPassword" >
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        确认密码：
                     <input type="password" name="confirmCardPassword" >
                 </td>
              </tr>
              <tr>
                 <td>
                      <input type=submit value=提交>
                 </td>
              </tr>
              <tr>
                 <td>
                     <a href="/platform/vipLogDtoSelect?vipId=1">单个查询</a>
                 </td>
              </tr>
              <tr>
                 <td>
                     <a href="/platform/vipLogDtoAllSelect">查询所有</a>
                 </td>
              </tr>
              <tr>
                 <td>
                     <a href="/platform/vipLogDtoUpdate?vipId=1">修改</a>
                 </td>
              </tr>
              <tr>
                 <td>
                     <a href="/platform/vipLogDtoDelete?vipId=1&vipCardId=1">删除</a>
                 </td>
              </tr>
           </table>
     </form>
   
</center>
</body>
</html>