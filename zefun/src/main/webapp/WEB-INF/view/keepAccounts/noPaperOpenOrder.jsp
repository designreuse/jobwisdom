<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/open_card_alert.css" type="text/css" />

<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right clearfix'>
			    <p><button onclick = "alertZzc()">点击进入开单</button><input type="text" placeholder="输入手牌号"><button>查找</button></p>
			     <!-- <div class="open_card_content">
				   <ul class="clearfix">
				     <li>
					 <img src="assets/images/money_close.png">
					   <p>手工牌号110</p>
					   <div class="customer">
					     <span>顾客：散户</span>
						 <em>开单时间：22:13</em>
					   </div>
					   <div class="customer_content">
					      <div class="customer_content_div">
					        <span class="left_click"><img src="assets/images/left_click.png"></span>
			              <div class="table_content clearfix">			   
						
							 <table>
							   <tr>
							    <td colspan="5">洗剪吹啊啊啊<span><img src="assets/images/architecture_edit.png"><em><img src="assets/images/architecture_delete.png"></em></span></td>
							  </tr> 
							  <tr>
							    <td>岗位</td>
							    <td>开始</td>
							    <td>结束</td>
							    <td>服务者</td>
								<td>操作</td>
							  </tr>
							  <tr>
							    <td>设</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>计</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td><em>+</em></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>助</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							</table>
							
							 <table>
							   <tr>
							    <td colspan="5">洗剪吹啊啊啊<span><img src="assets/images/architecture_edit.png"><em><img src="assets/images/architecture_delete.png"></em></span></td>
							  </tr> 
							  <tr>
							    <td>岗位</td>
							    <td>开始</td>
							    <td>结束</td>
							    <td>服务者</td>
								<td>操作</td>
							  </tr>
							  <tr>
							    <td>设</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>计</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td><em class="add">+</em></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>助</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							</table>
						   </div>	
						    
							<span class="right_click"><img src="assets/images/right_click.png"></span>
						 </div>
						 
						 <ul class="clearfix">
						   <li class="active2"></li>
						   <li></li>			   
						 </ul>
						 <div class="table_content_button">
						    <button>结算</button>
						 </div>
					   </div> 
					   
					 </li>
				 
				    <li>
					 <img src="assets/images/money_close.png">
					   <p>手工牌号110</p>
					   <div class="customer">
					     <span>顾客：散户</span>
						 <em>开单时间：22:13</em>
					   </div>
					   <div class="customer_content">
					      <div class="customer_content_div">
					        <span class="left_click"><img src="assets/images/left_click.png"></span>
			              <div class="table_content clearfix">			   
						
							 <table>
							   <tr>
							    <td colspan="5">洗剪吹啊啊啊<span><img src="assets/images/architecture_edit.png"><em><img src="assets/images/architecture_delete.png"></em></span></td>
							  </tr> 
							  <tr>
							    <td>岗位</td>
							    <td>开始</td>
							    <td>结束</td>
							    <td>服务者</td>
								<td>操作</td>
							  </tr>
							  <tr>
							    <td>设</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>计</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td><em>+</em></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>助</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							</table>
							
							 <table>
							   <tr>
							    <td colspan="5">洗剪吹啊啊啊<span><img src="assets/images/architecture_edit.png"><em><img src="assets/images/architecture_delete.png"></em></span></td>
							  </tr> 
							  <tr>
							    <td>岗位</td>
							    <td>开始</td>
							    <td>结束</td>
							    <td>服务者</td>
								<td>操作</td>
							  </tr>
							  <tr>
							    <td>设</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>计</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td><em class="add">+</em></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>助</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							</table>
						   </div>	
						    
							<span class="right_click"><img src="assets/images/right_click.png"></span>
						 </div>
						 
						 <ul class="clearfix">
						   <li class="active2"></li>
						   <li></li>			   
						 </ul>
						 <div class="table_content_button">
						    <button>结算</button>
						 </div>
					   </div> 
					   
					 </li>
					 
					 <li>
					 <img src="assets/images/money_close.png">
					   <p>手工牌号110</p>
					   <div class="customer">
					     <span>顾客：散户</span>
						 <em>开单时间：22:13</em>
					   </div>
					   <div class="customer_content">
					      <div class="customer_content_div">
					        <span class="left_click"><img src="assets/images/left_click.png"></span>
			              <div class="table_content clearfix">			   
						
							 <table>
							   <tr>
							    <td colspan="5">洗剪吹啊啊啊<span><img src="assets/images/architecture_edit.png"><em><img src="assets/images/architecture_delete.png"></em></span></td>
							  </tr> 
							  <tr>
							    <td>岗位</td>
							    <td>开始</td>
							    <td>结束</td>
							    <td>服务者</td>
								<td>操作</td>
							  </tr>
							  <tr>
							    <td>设</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>计</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td><em>+</em></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>助</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							</table>
							
							 <table>
							   <tr>
							    <td colspan="5">洗剪吹啊啊啊<span><img src="assets/images/architecture_edit.png"><em><img src="assets/images/architecture_delete.png"></em></span></td>
							  </tr> 
							  <tr>
							    <td>岗位</td>
							    <td>开始</td>
							    <td>结束</td>
							    <td>服务者</td>
								<td>操作</td>
							  </tr>
							  <tr>
							    <td>设</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>计</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td><em class="add">+</em></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>助</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							</table>
						   </div>	
						    
							<span class="right_click"><img src="assets/images/right_click.png"></span>
						 </div>
						 
						 <ul class="clearfix">
						   <li class="active2"></li>
						   <li></li>			   
						 </ul>
						 <div class="table_content_button">
						    <button>结算</button>
						 </div>
					   </div> 
					   
					 </li>
					 
					 <li>
					 <img src="assets/images/money_close.png">
					   <p>手工牌号110</p>
					   <div class="customer">
					     <span>顾客：散户</span>
						 <em>开单时间：22:13</em>
					   </div>
					   <div class="customer_content">
					      <div class="customer_content_div">
					        <span class="left_click"><img src="assets/images/left_click.png"></span>
			              <div class="table_content clearfix">			   
						
							 <table>
							   <tr>
							    <td colspan="5">洗剪吹啊啊啊<span><img src="assets/images/architecture_edit.png"><em><img src="assets/images/architecture_delete.png"></em></span></td>
							  </tr> 
							  <tr>
							    <td>岗位</td>
							    <td>开始</td>
							    <td>结束</td>
							    <td>服务者</td>
								<td>操作</td>
							  </tr>
							  <tr>
							    <td>设</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>计</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td><em>+</em></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>助</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							</table>
							
							 <table>
							   <tr>
							    <td colspan="5">洗剪吹啊啊啊<span><img src="assets/images/architecture_edit.png"><em><img src="assets/images/architecture_delete.png"></em></span></td>
							  </tr> 
							  <tr>
							    <td>岗位</td>
							    <td>开始</td>
							    <td>结束</td>
							    <td>服务者</td>
								<td>操作</td>
							  </tr>
							  <tr>
							    <td>设</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>计</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td><em class="add">+</em></td>
								<td><i>结束</i></td>
							  </tr>
							  <tr>
							    <td>助</td>
							    <td>22:12</td>
							    <td>23:12</td>
							    <td>1110<a href="javascript:;">改</a></td>
								<td><i>结束</i></td>
							  </tr>
							</table>
						   </div>	
						    
							<span class="right_click"><img src="assets/images/right_click.png"></span>
						 </div>
						 
						 <ul class="clearfix">
						   <li class="active2"></li>
						   <li></li>					   
						 </ul>
						 <div class="table_content_button">
						    <button>结算</button>
						 </div>
					   </div> 
					   
					 </li>
				   </ul>
				 </div> -->
			  </div> 
        </div>
  </div>
</div>	

<div class="zzc" name = "openOrderZzc" style="display:none">
  <div class="zzc_open_card_alert">
     <p>开单</p>
     <div class="zzc_open_card_alert_content">
	    <p>
		  <span>手牌号<input type="text" name = "handOrderCode" style="width: 40px"></span>
		  <span>会员开单<input type="text" name = "memberId"></span>
		  <em>散客<i>男<input type="radio" name="sex" value = "男" checked></i><i>女<input type="radio" name="sex" value = "女"></i></em>
		  <em style="width:120px" class="order_top">预约开单<input type="checkbox"></em>
		</p>
	  <div class="open_card_alert_state_show">	
	    <div class="open_card_alert_state">
		  <ul class="clearfix">
		    <c:forEach items="${positionInfoShiftMahjongDtoList}" var="positionInfoShiftMahjongDto" varStatus="status">
		       <li  <c:if test="${status.index == 0}">class="active"</c:if> onclick = "choosePositionShiftMahjong(this, ${positionInfoShiftMahjongDto.positionId })">${positionInfoShiftMahjongDto.positionName }</li>
		    </c:forEach>
		  </ul>
		  <c:forEach items="${positionInfoShiftMahjongDtoList}" var="positionInfoShiftMahjongDto" varStatus="status">
		      <div class="open_card_alert_state_content <c:if test="${status.index != 0}">hide</c:if>" positionId = "${positionInfoShiftMahjongDto.positionId }">
		          <c:forEach items="${positionInfoShiftMahjongDto.shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="shiftMahjongStatus">
		              <div class="open_card_alert_state_content_style" ${shiftMahjongDto.shiftMahjongId }>
				        <p>${shiftMahjongDto.shiftMahjongName }<span style="border:none!important">指定<input type="checkbox" name = "isAssign"></span></p>
						<div class="open_card_alert_state_number">
				           <ul class="clearfix">
				             <c:forEach items="${shiftMahjongDto.shiftMahjongEmployeeList}" var="shiftMahjongEmployee" varStatus="employeeStatus">
				                <li <c:if test="${shiftMahjongEmployee.state == 0}">class="working"</c:if>
				                    <c:if test="${shiftMahjongEmployee.state == 1}">class="free"</c:if>
				                    <c:if test="${shiftMahjongEmployee.state == 2}">class="rest"</c:if>
				                    <c:if test="${shiftMahjongEmployee.state == 4}">class="off_point"</c:if>
				                    name = "activeLi" shiftMahjongEmployeeId = "${shiftMahjongEmployee.shiftMahjongEmployeeId }">
				                    <i>${shiftMahjongEmployee.shiftMahjongOrder }</i>
				                    <em>${shiftMahjongEmployee.employeeCode } ${shiftMahjongEmployee.name }</em>
				                    <a href="javascript:;"></a>
				                </li>
				             </c:forEach>
					    </div>
					 </div>
		          </c:forEach>
				  <div class="open_state">
				   <span><em style="background:#21d9db"></em>空闲</span>
				   <span><em style="background:#e11e23"></em>工作</span>
				   <span><em style="background:#e7a3ef"></em>点客</span>
				   <span><em style="background:#eede9f"></em>暂休</span>
				  </div>
		      </div>
		  </c:forEach>
		  
		</div>
	    <div class="open_card_alert_state_button">
		  <button onclick="submits()">开单</button>
		  <button>取消</button>
		</div>
	 </div>
 
       </div> 
   
      <div class="card_number" style="display:none">
	    <img src="<%=basePath%>images/open_card_close.png">
	    <p>深灰色为不可选状态</p>
	    <ul class="clearfix">
	       <li>100</li>
		   <li>101</li>
		   <li>102</li>
		   <li>103</li>
		   <li>104</li>
		   <li>105</li>
		   <li>106</li>
		   <li>107</li>
		   <li>108</li>
		   <li>109</li>
		   <li>110</li>
		   <li>111</li>
		   <li>112</li>
		   <li>113</li>
		   <li>114</li>
		   <li>115</li>
		   <li>116</li>
		   <li>117</li>
		   <li>118</li>
		   <li>119</li>
		   <li>120</li>
		   <li>121</li>
		   <li>123</li>
		   <li>124</li>
		   <li>125</li>
		   <li>126</li>
		   <li>127</li>
		   <li>128</li>
		   <li>129</li>
		   <li>130</li>
		   <li>131</li>
		   <li>132</li>
		   <li>133</li>
		   <li>134</li>
		   <li>135</li>
		   <li>136</li>
		   <li>137</li>
		   <li>138</li>
		   <li>139</li>
		   <li>140</li>
		   <li>141</li>
		   <li>142</li>
		   <li>143</li>
		   <li>144</li>
		   <li>145</li>
		   <li>146</li>
		   <li>147</li>
		   <li>148</li>
		   <li>149</li>
		</ul>
	  </div>
   
      <div class="order_open_card" style="display:none">
	    <div class="order_open_card_close"><img src="<%=basePath%>images/open_card_close.png"></div>
	    <p>2016／06/23 16:57</p>
		 <div class="order_open_card_ clearfix">
			<div class="order_open_card_content">
			   <div class="order_open_card_content_left">
				  <div class="img">
					<img src="assets/images/seo_pic.png">
				  </div>
				  <div class="img_right">
					 <div>实打实的</div>
					 <p>1313131313113</p>
					 <span>收银预约</span>
				  </div>
			   </div>
				<div class="order_open_card_content_right">
				   <p>啊啊啊啊啊啊系列</p>
				   <span>预约时间16:30 已过期</span>
				   <div>110 名啊字</div>
				   <div>美发发型师</div>
			   </div>
			</div>
			
			<div class="order_open_card_content">
			   <div class="order_open_card_content_left">
				  <div class="img">
					<img src="assets/images/seo_pic.png">
				  </div>
				  <div class="img_right">
					 <div>实打实的</div>
					 <p>1313131313113</p>
					 <span>收银预约</span>
				  </div>
			   </div>
				<div class="order_open_card_content_right">
				   <p>啊啊啊啊啊啊系列</p>
				   <span>预约时间16:30 已过期</span>
				   <div>110 名啊字</div>
				   <div>美发发型师</div>
			   </div>
			</div>
			
			
				<div class="order_open_card_content">
			   <div class="order_open_card_content_left">
				  <div class="img">
					<img src="assets/images/seo_pic.png">
				  </div>
				  <div class="img_right">
					 <div>实打实的</div>
					 <p>1313131313113</p>
					 <span>收银预约</span>
				  </div>
			   </div>
				<div class="order_open_card_content_right">
				   <p>啊啊啊啊啊啊系列</p>
				   <span>预约时间16:30 已过期</span>
				   <div>110 名啊字</div>
				   <div>美发发型师</div>
			   </div>
			</div>
			
		 </div>
         
        		 
	  </div>
   </div>
</div>

<script type="text/javascript" src="<%=basePath %>js/keepAccounts/noPaperOpenOrder.js"></script>
</body>
</html>