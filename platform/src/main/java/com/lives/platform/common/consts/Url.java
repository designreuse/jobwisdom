package com.lives.platform.common.consts;


/**
 * 接口常量管理
 */
public interface Url {
	
	/** 人脸识别模块 */
	public static class Face{
		/** 添加face */
		public static final String ACTION_ADD = "face/action/add";
		/** 显示添加face的页面 */
		public static final String VIEW_ADD = "face/view/add";
		/** 根据faceset进行匹配搜索 */
		public static final String ACTION_SEARCH = "face/action/search";
		/** 显示搜索face的页面 */
		public static final String VIEW_SEARCH = "face/view/search";
	}
	
	public static class Index{
		/** 进入登录页面*/
		public static final String TOLOGINPAGE="index/login";
		/** 进入主页页面*/
		public static final String TOINDEXPAGE="index/toIndexPage";
	}
	
	public static class CouponsUrl {
		public static final String addType="/addType";
		/** 增加散客优惠劵*/
		public static final String addSanker = "addSanker";
		/** 查询得出所有的优惠券基础,进行分配*/
		public static final String serch = "serch";
		public static final String sendtoVip = "sendtoVip";
		public static final String serchSanker = "serchSanker";
	}
	
	public static class Project{
		/** 进入项目页面*/
		public static final String TOPROJECTPAGE = "/project/toProjectPage";
		/** 项目保存批量*/
		public static final String ADDPROJECTBATCH = "/project/addProjectBatch";
		/** 项目保存*/
		public static final String ADDPROJECT = "/project/addProject";
		/** 项目员工提成保存*/
		public static final String ADDPROJECTCOMMISSION = "/project/addProjectCommission";
		/**进入项目门店设置页面*/
		public static final String TOPROJECTRELATION = "/project/toProjectRelation";
		/**进入项目门会员设置页面*/
		public static final String TOPROJECTMEMBERS = "/project/toProjectMembers";
		/** 门店列表*/
		public static final String STORESLIST = "/project/storesList";
		/** 项目类别保存*/
		public static final String ADDPROJECTCLASSES = "/project/addProjectClasses";
		/** 项目类别删除*/
		public static final String DELETEPROJECTCLASSES = "/project/deleteProjectClasses";
		/**项目修改*/
		public static final String EDITPROJECT = "/project/editProject";
		/**根据项目id查询项目是否有设置分店*/
		public static final String QUERYPROJECTIDISSET = "/project/queryProjectIdIsSet";
		/** 项目删除*/
		public static final String DELETEPROJECT = "/project/deleteProject";
		
		/**项目会员折扣保存*/
		public static final String SAVEMEMBERS = "/project/saveMembers";
		/**获取会员等级列表*/
		public static final String VIPLEVELLIST = "/project/vipLevelList";
		
		/**删除员工提成类别*/
		public static final String DELETEEMPCOMMISSION = "project/deleteEmpCommission";
		/**删除会员等级类别*/
		public static final String DELETEMEMBERSLEVEL = "project/deleteMembersLevel";
		
		/**验证项目编号唯一性*/
		public static final String VALIDPROJECTNO = "project/validProjectNo";
	}
	
	public static class Vip{
		
		/** 进入会员基本信息添加界面 */
		public static final String VIPLOG = "/vipLog";
		/** 进入会员选填信息界面*/
		public static final String VIPINFO = "/vipInfo";
		/** 会员基本信息保存*/
		public static final String VIPLOGSUBMIT = "/vipLogSubmit";
		/** 会员选填信息保存*/
		public static final String VIPLOGINFOSUBMIT = "/vipLogInfoSubmit";
		/** 会员综合信息查询（单条）*/
		public static final String VIPLOGDTOSELECT = "/vipLogDtoSelect";
		/** 会员综合信息查询（所有）*/
		public static final String VIPLOGDTOALLSELECT = "/vipLogDtoAllSelect";
		/** 会员综合信息修改*/
		public static final String VIPLOGDTOUPDATE = "/vipLogDtoUpdate";
		/** 会员综合信息删除*/
		public static final String VIPLOGDTODELETE = "/vipLogDtoDelete";
		
		public static final String GETVIPLEVELLIST = "/getVipLevelList";
	}
	
    public static class Dommission{
		
		/** 进入提成添加界面 */
		public static final String DOMMISSIONLOG = "/dommissionLog";
		/** 提成信息保存*/
		public static final String DOMMISSIONSUBMIT = "/dommissionSubmit";
		/** 提成信息查询（单条）*/
		public static final String DOMMISSIONSELECT = "/dommissionSelect";
		/** 提成信息修改*/
		public static final String DOMMISSIONUPDATE = "/dommissionUpdate";
		/** 提成信息删除*/
		public static final String DOMMISSIONDELETE = "/dommissionDelete";
		/** 动态查询数据
		 * 项目类型、 项目id、门店id、员工id
		 * */
		public static final String DOMMISSIONSELECTBYPROPERTY = "/dommissionselectbyproperty";
		
		public static final String DOMMISSIONALLSUBMIT = "/dommissionAllSubmit";

	}
    
    public static class Employee{
    	
    	/** 进入员工信息添加界面 */
		public static final String EMPLOYEELOG = "/employeeLog";
		/** 岗位设置界面*/
		public static final String EMPLOYEE = "/employee/employee";
		
		/** 员工信息+地址信息保存 */
		public static final String EMPLOYEESUBMIT = "/employeeSubmit";
		/** 员工信息+地址信息查询（单条）*/
		public static final String EMPLOYEEDTOSELECT = "/employeeDtoSelect";
		/** 员工信息+地址信息查询（所有）*/
		public static final String EMPLOYEEDTOALLSELECT = "/employeeDtoAllSelect";
		/** 员工信息+地址信息修改*/
		public static final String EMPLOYEEDTOUPDATE = "/employeedtoUpdate";
		/** 员工信息+地址信息删除*/
		public static final String EMPLOYEEDTODELETE = "/employeedtoDelete";
		/** 员工信息+地址信息动态查询*/
		public static final String EMPLOYEEDTOSELECTBYPROPERTY = "/employeeDtoSelectByProperty";

		/** 获取岗位列表*/
		public static final String GETPOSITIONLIST = "/getPositionList";
		/** 员工列表*/
		public static final String GETEMPLOYEES = "/getEmployees";
		/** 保存岗位信息*/
		public static final String SAVEPOSTMESSAGE = "/savePostMessage";
		/** 查询所有岗位信息*/
		public static final String SELECTPOSTMESSAGE = "/selectPostMessage";
		/** 修改岗位信息*/
		public static final String UPDATEPOSTMESSAGE = "/updatePostMessage";
		/** 删除岗位信息*/
		public static final String DELETEPOSTMESSAGE = "/deletePostMessage";
		/** 根据岗位信息id查询职位*/
		public static final String SELECTBYPOSTMESSAGEID = "/selectByPostMessageId";
		/** 保存职位信息*/
		public static final String SAVEPOSITION = "/savePosition";
		
		public static final String DELETEPOSITION = "/deletePosition";
		
		public static final String UPDATEPOSITION = "/updatePosition";
		
	}
    
    public static class VipLevel{
    	
    	/** 进入会员等级添加界面 */
		public static final String VIPLEVELLOG = "/vipLevelLog";
		/** 会员等级保存*/
		public static final String VIPLEVELSUBMIT = "/vipLevelSubmit";
		/** 会员等级查询（单条）*/
		public static final String VIPLEVELSELECT = "/vipLevelSelect";
		/** 会员等级修改*/
		public static final String VIPLEVELUPDATE = "/vipLevelUpdate";
		/** 会员等级删除*/
		public static final String VIPLEVELDELETE = "/vipLevelDelete";
    	
    }
    
    public static class StoresManage{
    	
    	/** 进入区域门店管理添加界面 */
		public static final String STORESMANAGELOG = "/storesManageLog";
		/** 区域门店管理保存*/
		public static final String STORESMANAGESUBMIT = "/storesManageSubmit";
    	/** 区域关系新增*/
		public static final String STORESMANAGERELATIONCOPY = "/storesManageRelationCopy";
    }
    
     public static class Position{
    	
    	/** 进入职位信息添加界面 */
		public static final String POSITIONLOG = "/positionLog";
		/** 职位信息保存*/
		public static final String POSITIONSUBMIT = "/positionSubmit";
		/** 会员等级查询（单条）*/
		public static final String POSITIONSELECT = "/positionSelect";
		/** 会员等级修改*/
		public static final String POSITIONUPDATE = "/positionUpdate";
		/** 会员等级删除*/
		public static final String POSITIONDELETE = "/positionDelete";
		/** 根据门店查会员等级*/
		public static final String SELECTPOSITIONBYSTORESIDLIST = "/selectPositionByStoresIdList";
    }
     
    public static class Shift{
    	
    	/** 进入订单添加界面 */
		public static final String SHIFTLOG = "/shiftLog";
		
		public static final String SHIFT = "/shift";
		
		public static final String SETSHIFT = "/setshift";
		/** 保存岗位信息*/
		public static final String SAVEPOST ="/savePost";
		/** 删除岗位信息*/
		public static final String DELETEPOST ="/deletePost";
		/** 修改岗位信息*/
		public static final String UPDATEPOST ="/updatePost";
		
		public static final String SELECTALLPOST ="/selectAllPost";
		
		public static final String SELECTALLPOSTMESSAGE ="/selectAllPostMessage";
		
    }
    
    public static class Payment{
    	public static final String ACTION_RETAIL = "/payment/action/retail";
    	
    	public static final String VIEW_RETAIL = "/payment/view/retail";
    	
    	public static final String SELECTAUTOPROJECT ="/payment/action/selectAutoProject";
		/** 根据项目查找员工*/
		public static final String SELECTAUTOEMPLOYEE = "/payment/action/selectAutoEmployee";
		/** 保存订单信息*/
		public static final String SAVEORDERINFO = "/payment/action/saveOrderInfo";
		/** 结账*/
		public static final String GETPROJECTEMPLOYEERELATION = "/payment/action/getProjectEmployeerelation";
		/** 校验签单授权码是否存在 */
		public static final String CHECKOUTQDCODE = "/payment/action/checkoutQDcode";
    }
    
    public static class StaffEmployeeSetting{
    	//初始化岗位信息页面
    	public static final String STAFFSETTING = "/StaffEmployeeSetting/action/staffSetting";
    	//初始化员工信息页面
    	public static final String EMPLOYEESETTING = "/StaffEmployeeSetting/action/employeeSetting";
    	//新增、修改岗位信息
    	public static final String OPERATIONPOSTMESSAGE = "/StaffEmployeeSetting/action/operationPostMessage";
    	//删除岗位信息
    	public static final String DELETEPOSTMESSAGE = "/StaffEmployeeSetting/action/deletePostMessage";
    	//查询职位信息
        public static final String SELECTBYPOSTMESSAGEID = "/StaffEmployeeSetting/action/selectByPostMessageId";
        //新增、修改职位信息
        public static final String OPERATIONPOSITION = "/StaffEmployeeSetting/action/operationPosition";
        //删除职位信息
        public static final String DELETEPOSITION = "/StaffEmployeeSetting/action/deletePosition";
        //保存员工信息
        public static final String SAVAEMPLOYEE = "/StaffEmployeeSetting/action/saveEmployee";
        //查询员工列表
        public static final String EMPLOYEESELECT = "/StaffEmployeeSetting/action/employeeSelect";
        //修改员工信息
        public static final String EMPLOYEEUPDATE = "/StaffEmployeeSetting/action/employeeUpdate";
        //删除员工信息
        public static final String DELETEEMPLOYEEID = "/StaffEmployeeSetting/action/deleteEmployeeId";
    }
    
	public static class Privilege{
		/**用户新增*/
		public  static final String addUser = "/addUser";
		/**角色新增*/
		public  static final String addRole = "/addRole";
		/**授权*/
		public static final String setRoleToUser = "/setRoleToUser";
		/**拉去人员信息*/
		public static final String getUsers = "/getUsers";
		public static final String getRoleUserLs = "/getRoleUserLs";
		/**跳转至角色新增页面*/
		public static final String sendRole = "/sendRole";
		/**跳转至权限新增页面*/
		public static final String sendPrivilege = "/sendPrivilege";
		/**获取所有角色*/
		public static final String getRole = "/getRole";
		/**删除角色*/
		public static final String deleteRole = "/deleteRole";
		/**更新角色*/
		public static final String updateRole = "/updateRole";
		public static final String addPrivilege = "/addPrivilege/{type}";
		public static final String getPrivilege = "/getPrivilege";
		/**获取权限,进行角色配置*/
		public static final String getAllPrivileges = "/getAllPrivileges";
		/**获取二级菜单*/
		public static final String getAllPrivilegesSecond = "/getAllPrivilegesSecond";
		/**获取功能权限*/
		public static final String getAllPrivilegesGn = "/getAllPrivilegesGn";
		/**获取元素权限*/
		public static final String getAllPrivilegesYs = "/getAllPrivilegesYs";
		/**角色权限绑定*/
		public static final String buildRole = "/buildRole";
		public static final String getHashPrivileges = "/getHashPrivileges";
		/**Tree*/
		public static final String sendPeoplePrivilege = "/sendPeoplePrivilege";
		/**获得树形*/
		public static final String getTree = "/getTree";
		public static final String getTree2 = "/getTree2";
		public static final String getTree3 = "/getTree3";
		/**跳转角色权限绑定*/
		public static final String roleBuildPrivlege = "/roleBuildPrivlege";
		/**角色权限绑定*/
		public static final String buildRolePrivlege = "/buildRolePrivlege";
		/**人员权限绑定-跳转设定*/
		public static final String setPrivilegeToUser = "/setPrivilegeToUser";
		/**人员权限绑定*/
		public static final String buildUserRolePrivlege = "/buildUserRolePrivlege";
		/**跳转至权限修改页面*/
		public static final String sendUpdatePrivilege = "/sendUpdatePrivilege";
		 /**修改权限*/
		public static final String updatePrivilege = "/updatePrivilege";
		/**删除权限*/
		public static final String deletePrivilege = "/deletePrivilege";
		
	}
	
	public static class Members{
		/**进入会员列表页面*/
		public static final String TOMEMBERSLIST = "/members/toMembersList";
		/**进入新增会员页面*/
		public static final String TOADDMEMBERS = "/members/toAddMembers";
		/**保存会员信息*/
		public static final String SAVEMEMBRES = "/members/saveMembres";
		/**手机号码验证*/
		public static final String VALIDMOBILE = "/members/validMobile";
		/**会员卡类型选择*/
		public static final String MEMBERSTYPESEL = "/members/membersTypeSel";
		/**类型选择*/
		public static final String TYPESELECT = "/members/typeSelect";
		/**直接推介人信息查询*/
		public static final String RECOMMENDINFO = "/members/getRecommendInfo";
	}
	/**
	 * 会员规则
	* @author 高国藩
	* @date 2015年7月16日 上午9:55:48 
	*
	 */
	public static class MemberCard{
		public static final String memberCard = "/memberCard";
		public static final String save = "/save";
		public static final String deleteCard = "/memberCard/deleteCard";
		public static final String updateCard = "/memberCard/updateCard";
		public static final String realyUpdate = "/memberCard/realyUpdate";
		public static final String selectVipCardsByPage = "/selectVipCardsByPage";
		public static final String cutInfo = "/cutInfo";
		public static final String PresentInfo = "/PresentInfo";
		public static final String RecommendInfo = "/RecommendInfo";
		public static final String selectCutInfo = "/selectCutInfo";
		public static final String selectPresentInfo = "/selectPresentInfo";
		public static final String selectRecommendInfo = "/selectRecommendInfo";
		public static final String updateCutInfos = "/updateCutInfos";
		public static final String updatePresentInfos = "/updatePresentInfos";
		public static final String updateRecommendInfos = "/updateRecommendInfos";
	}
	  
}
