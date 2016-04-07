
/**时间比较大小*/
function validateDate(str1, str2){
	if(str1.trim().length == 0){
		return false;
	}
	if(str2.trim().length == 0){
		return false;
	}
	var start = str1.replace(/-/g,"/");
	var date1 = new Date(start);
	var end = str2.replace(/-/g,"/");
	var date2 = new Date(end);
	if ((date2-date1)>=0){
		return true;
	}else{
		return false;
	}
}

var str = new Date(); 
var date = str.getFullYear()+'-'+(str.getMonth()+1)+'-'+str.getDate();
/**校验表单*/
function validate(){
	if(validateDate($("input[name='holdTime']").val(), $("input[name='endTime']").val()) == false){
		dialog("请注意召开时间,填写有误");
		return false;
	}
	if(validateDate(date, $("input[name='registrationEndTime']").val()) == false){
		dialog("请注意报名时间,填写有误");
		return false;
	}
	if(validateDate($("input[name='registrationEndTime']").val(), $("input[name='holdTime']").val()) == false){
		dialog("报名时间不可大于召开时间");
		return false;
	}
	if($("input[name='conferenceName']").val().trim().length == 0){
		dialog("会议名称不可为空");
		return false;
	}
	if($("input[name='address']").val().trim().length == 0){
		dialog("地点不可为空");
		return false;
	}
	if($("input[name='linkName']").val().trim().length == 0){
		dialog("联系人不可为空");
		return false;
	}
	var pattern = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	var linkPhone = $("input[name='linkPhone']").val().trim();
	if(linkPhone.length == 0 || !pattern.test(linkPhone)){
		dialog("请正确填写联系人电话");
		return false;
	}
	var reg = new RegExp("^[0-9]*$");
	var peopleCount = $("input[name='peopleCount']").val().trim();
	if(peopleCount.length == 0 || !reg.test(peopleCount)){
		dialog("请正确填写人员数量设定");
		return false;
	}
	var mainAward = $("input[name='mainAward']").val().trim();
	if(mainAward.length == 0 || !reg.test(mainAward)){
		dialog("请正确填写一级奖励");
		return false;
	}
	var branchAward = $("input[name='branchAward']").val().trim();
	if(branchAward.length == 0 || !reg.test(branchAward)){
		dialog("请正确填写二级奖励");
		return false;
	}
	var registrationAmount = $("input[name='registrationAmount']").val().trim();
	if(registrationAmount.length == 0 || !reg.test(registrationAmount)){
		dialog("请正确填写报名费用");
		return false;
	}
}

