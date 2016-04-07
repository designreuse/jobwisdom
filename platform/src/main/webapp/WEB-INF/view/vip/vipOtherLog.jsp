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
          <form action="vipLogInfoSubmit" method=post>
            <table border="1" bgcolor="#0099CC">
              <tr>
                 <td>        年龄:
                    <input type="text" name="age">
                 </td>
                 <td>        消费频率:
                    <input type="text" name="consumptionFrequency">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        学历：
                     <input type="text" name="education">
                 </td>
                 <td>        消费时段：
                     <input type="text" name="consumptionTime">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        职业：
                     <input type="text" name="occupation">
                 </td>
                 <td>        消费偏好：
                     <input type="text" name="consumptionPreference">
                 </td>
              </tr>
               <tr valign="middle">
                 <td>        职位：
                     <input type="text" name="position" >
                 </td>
                 <td>        家庭状况：
                     <input type="text" name="familyStatus">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>       月收入：
                     <input type="text" name="monthlyIncome" >
                 </td>
                 <td>       资产状况：
                     <input type="text" name="assetsStatus" >
                 </td>
              </tr>
              <tr>
                 <td>
                      <input type=submit value=提交>
                 </td>
              </tr>
           </table>
     </form>
</center>
</body>
</html>