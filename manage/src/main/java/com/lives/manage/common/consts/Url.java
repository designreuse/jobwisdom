package com.lives.manage.common.consts;

/**
 * 所有的接口列表
 */
public interface Url {

	public static class User{
		/** 添加账户跟用户信息 */
		public static final String ADD = "/addUser";
		/**修改账户跟用户信息*/
		public static final String UPDATE = "/updateUser";
		/**删除账户跟用户信息*/
		public static final String DELETE = "/deleteUser";
		/**查询账户跟用户信息*/
		public static final String QUERY = "/queryUser";
		
		public static final String UPDATEPASSWORD = "/updatepassword";
		public static final String INITPASSWORD = "/initpassword";
		
		public static final String RECHARGE = "/recharge";
		
		
		
		
		
		
	}
	
	public static class Menu {
		/** 添加菜单 */
		public static final String ADD = "/addMenu";
		/**修改菜单*/
		public static final String UPDATE = "/updateMenu";
		/**查询菜单*/
		public static final String QUERY = "/queryMenu";
		
	}
	
	public static class Role {
		/** 添加角色*/
		public static final String ADD = "/addRole";
		/**修改角色*/
		public static final String UPDATE = "/updateRole";
		/**查询角色*/
		public static final String QUERY = "/queryRole";
		/**新增菜单*/
		public static final String ROLEMENU = "/roleMenu";
		
	}
	public static class Cust {
		/** 添加客户*/
		public static final String ADD = "/addCust";
		/**修改客户*/
		public static final String UPDATE = "/updateCust";
		/**查询客户*/
		public static final String QUERY = "/queryCust";
		/**查询客户*/
		public static final String DELETE = "/deleteCust";
		/**查询客户名称*/
		public static final String GETCUSTNAME = "/getCustName";
		/**查询公司名称*/
		public static final String GETCOMPANYNAME = "/getCompanyName";
		
		public static final String GETGOODSPRICE = "/getGoodsPrice";
		
		
		
	}
	
	public static class CustClue {
		/** 添加用户线索*/
		public static final String ADD = "/addCustClue";
		/**修改用户线索*/
		public static final String UPDATE = "/updateCustClue";
		/**修改用户线索所属人*/
		public static final String UPDATESS = "/updateClueSs";
		/**查询用户线索*/
		public static final String QUERY = "/queryCustClue";
		/**查询公海线索*/
		public static final String PUBLICQUERY = "/queryPublicInfo";
		
		public static final String DELETECLUE = "/deleteClue";
		
		
		
		
		
	}
	
	public static class Hchart {
		/** 查询不同版本月销售量（折线统计）*/
		public static final String EACHMONTHSALES = "/eachMonthSales";
		/**不同版本月销售 （柱状图统计）*/
		public static final String MONTHSALES = "/monthSales";
		/**不同版本月销售 金额（饼状图统计）*/
		public static final String MONTHSALESMONEY = "/monthSalesMoney";
		/**不同版本月销售 金额（饼状图统计）*/
		public static final String QUERYCOUNT = "/queryCount";
		
	}
	public static class Project {
		/** 添加项目*/
		public static final String ADD = "/addProject";
		/** 添加项目*/
		public static final String DELETE = "/deleteProject";
		/**修改项目信息*/
		public static final String UPDATE = "/updateProject";
		
		/**查询项目信息*/
		public static final String QUERY = "/queryProject";
		/**查询客户项目销售信息*/
		public static final String GETCUSTSALE = "/getCustSale";
		/**新增客户项目销售信息*/
		public static final String ADDCUSTSALE = "/addCustSale";
		/**获取项目名称*/
		public static final String GETGOODSNAME = "/getGoodsName";
		/**获取项目单价*/
		public static final String GETGOODSPRICE = "/getGoodsPrice";
		
		
		
		
		
	}
	public static class Homepage {
		/** 获取当月每天的销售量*/
		public static final String EACHDAYSALES = "/eachDaySales";
		/** 获取当月每天的销售量*/
		public static final String STATISTICS = "/statistics";
		/** 城市销售量排行*/
		public static final String CITYSALERANK = "/citySaleRank";
		/** 省份销售量排行*/
		public static final String PROVINCESALERANK = "/provinceSaleRank";
		/** 本月区域开户汇总*/
		public static final String MONTHSUMMARY = "/monthSummary";
		/** 本月个人开户汇总*/
		public static final String PERSSUMMARY = "/persSummary";
		/** 个人开户排行*/
		public static final String PERSSALERANK = "/persSaleRank";
		
		
		
		
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
		/**人员授权*/
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
		/**人员角色绑定*/
		public static final String userBuileRole = "/userBuileRole";
		public static final String queryUser = "/queryUser";
		public static final String realUserBuildRole = "/realUserBuildRole";
		public static final String getUserRole = "/getUserRole";
		/**bootstrap 跳转至权限展示页面*/
		public static final String sendBprivilege = "/sendBprivilege";
		/**bootstrap 人员授权*/
		public static final String bootstrapUserBuildPrivilege = "/bootstrapUserBuildPrivilege";
		/**bootstrap 角色权限绑定页面*/
		public static final String bootstraprolebuildprivilege = "/bootstraprolebuildprivilege";
		/**bootstrap user已经绑定的权限角色信息*/
		public static final String bootstrapUserHasBuildPrivilege = "/bootstrapUserHasBuildPrivilege";
		/**bootstrap role已经绑定的权限信息*/
		public static final String bootstrapRoleHasBuildPrivilege = "/bootstrapRoleHasBuildPrivilege";
		/**bootstrap 角色绑定权限*/
		public static final String bootstrapRoleBuildPrivilege = "/bootstrapRoleBuildPrivilege";
		/**bootstrap 权限新增修改页面*/
		public static final String bootstrapSendToPrivilege = "/bootstrapSendToPrivilege";
		/**bootstrap table 展示菜单权限信息*/
		public static final String showPrivilegeInfo = "/showPrivilegeInfo";
		/**bootstrap table 展示功能权限信息*/
		public static final String showPrivilegeGnInfo = "/showPrivilegeGnInfo";
		/**bootstrap table 展示元素权限信息*/
		public static final String showPrivilegeYsInfo = "/showPrivilegeYsInfo";
		
	}
	/**
	 * 业务员管理
	* @author chendb
	* @date 2015年10月30日 下午5:07:06
	 */
	public static class Salesman{
	    /**
         * 查询属于自己的业务员
         */
	    public static final String QUERYSALESMAN = "/querySalesman";
	    /**
         * 新增业务员
         */
	    public static final String ADDSALESMAN = "/addSalesman";
	    /**
	     * 修改业务员
	     */
	    public static final String UPDATESALESMAN = "/updateSalesman";
	    
	    
	}
}
