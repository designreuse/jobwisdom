<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vip注册</title>
</head>
<body>
     <center>
       <h1>员工注册</h1>
          <form action="storesManageSubmit" method=post>
            <table border="1" bgcolor="#0099CC">
              <tr>
                 <td>        区域名称:
                    <input type="text" name="storesManageName">
                 </td>
              </tr>
              <tr>
                 <td>        总店id:
                    <input type="text" name="hqStoresId">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        石夏店：
                     <input type="checkbox" name="checkboxname" value="001001">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        岗夏店：
                     <input type="checkbox" name="checkboxname" value="001002">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        华强路店：
                     <input type="checkbox" name="checkboxname" value="001003">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        红树林店：
                     <input type="checkbox" name="checkboxname" value="001004">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        老街店：
                     <input type="checkbox" name="checkboxname" value="001005">
                 </td>
              </tr><tr valign="middle">
                 <td>        白石洲店：
                     <input type="checkbox" name="checkboxname" value="001006">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        世界之窗店：
                     <input type="checkbox" name="checkboxname" value="001007">
                 </td>
              </tr>
              <tr>
                 <td>
                      <input type=submit value=提交>
                 </td>
              </tr>
              <tr>
                 <td>
                     <a href="/platform/storesManageRelationCopy?storesManageId=1&storesAccountsId=3">复制区域门店</a>
                 </td>
              </tr>
              <tr>
                 <td>
                     <a href="/platform/storesManageRelationUpdate?storesManageId=1&storesAccountsId=3">修改帐号区域门店管理</a>
                 </td>
              </tr>
           </table>
     </form>
     <br/>
     <form action="storesManageRelationUpdate" method=post>
            <table border="1" bgcolor="#0099CC">
              <tr>
                 <td>        区域名称:
                    <input type="text" name="storesManageName">
                 </td>
              </tr>
              <tr>
                 <td>        总店id:
                    <input type="text" name="hqStoresId">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        石夏店：
                     <input type="checkbox" name="checkboxname" value="001001">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        岗夏店：
                     <input type="checkbox" name="checkboxname" value="001002">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        华强路店：
                     <input type="checkbox" name="checkboxname" value="001003">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        红树林店：
                     <input type="checkbox" name="checkboxname" value="001004">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        老街店：
                     <input type="checkbox" name="checkboxname" value="001005">
                 </td>
              </tr><tr valign="middle">
                 <td>        白石洲店：
                     <input type="checkbox" name="checkboxname" value="001006">
                 </td>
              </tr>
              <tr valign="middle">
                 <td>        世界之窗店：
                     <input type="checkbox" name="checkboxname" value="001007">
                 </td>
              </tr>
              <tr>
                 <td>
                      <input type=submit value=提交>
                 </td>
              </tr>
           </table>
     </form><br/>
     
     区域:
     <select>
     	<option>广东区</option>
     	<option>深圳区</option>
     	<option>湖南区区</option>
     </select>
   门店：
   <select>
     	<option>门店1店长账户</option>
     	<option>门店2店长账户</option>
     	<option>门店3店长账户</option>
     	<option>门店4店长账户</option>
     </select>
</center>
</body>
</html>