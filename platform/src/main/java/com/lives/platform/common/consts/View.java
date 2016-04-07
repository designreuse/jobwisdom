package com.lives.platform.common.consts;

public interface View {
	
	/**
	 * 人脸模块相关页面
	* @author 张进军
	* @date Jul 2, 2015 2:57:02 PM
	 */
	public static final class Face{
		/** 添加face页面 */
		public static final String ADD = "face/add";
		/** 搜索face页面 */
		public static final String SEARCH = "face/search";
	}
	
	public static final class Index{
		/** 登录页面*/
		public static String LOGINPAGE = "/login";
		/**主页*/
		public static String INDEXPAGE = "/templateIndex";
	}
	
	public static final class Project{
		
		public static final String COUPONSLIST="couponslist";
		
		/** 项目页面*/
		public static String PROJECT = "project/project";
		/**项目门店页面*/
		public static String PROJECTSTROE = "project/projectStore";
		/**项目会员页面*/
		public static String PROJECTMEMBERS = "project/projectMembers";
		
		/** 模版页面*/
		public static String PROJECTPLATE = "project/projectPlate";
		/** 项目模版操作页面*/
		public static String PROJECTPLATEOPTION = "project/projectPlateAddOrUpdate";
		
		/** 项目类别页面*/
		public static String PROJECTCLASS = "project/projectClass";
		/** 项目类别操作页面*/
		public static String PROJECTCLASSOPTION = "project/projectClassAddOrUpdate";
		
	}
	
	public static final class Payment{
		public static final String RETAIL = "payment/retail";
	}
	
	public static final class StaffEmployeeSetting{
		public static final String STAFFSETTING = "staff_employee_setting/staff_setting";
		
		public static final String EMPLOYEESELECT = "staff_employee_setting/employee_select";
		
		public static final String EMPLOYEESETTING = "staff_employee_setting/employee_setting";
	}
	
	public static final class Members{
		/**会员列表*/
		public static final String MEMBERSLIST = "members/membersList";
		/**会员新增*/
		public static final String MEMBERSADD = "members/membersAdd";
	}
	
	public static final class MemberCard{
		public static final String sendMemberCard = "memberCard/memberCard";
	}
	
}