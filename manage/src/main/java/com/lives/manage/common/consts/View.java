package com.lives.manage.common.consts;

public interface View {
	public static final class User{
		public static String ADD = "user/add";
		public static String QUERY = "user/query";
		public static String QUERYINFO = "user/queryinfo";
		public static String USERDETAIL = "user/userdetails";
		public static String RECHARGE = "user/recharge";
		
	}
	public static final class Role{
		public static String ADD = "role/add";
	}
	public static final class Menu{
		public static String ADD = "menu/add";
	}
	public static final class Cust{
		public static String QUERY = "cust/query";
		public static String ADD = "cust/addcust";
		public static String UPDATE = "cust/updatecust";
	}
	public static final class CustClue{
		public static String QUERY = "custclue/query";
		public static String PUBLICQUERY = "custclue/publicquery";
	}
	/***/
	public static final class Hchart{
		public static String QUERY = "hchart/query";
	}
	public static final class Project{
		public static String QUERY = "salesproject/setproject";
		public static String SALESPROJECT = "salesproject/salesproject";
	}
	
	public static final class Homepage{
		public static String QUERY = "homepage/homepage";
		public static String QUERYAERARANK = "homepage/aerarank";
		public static String QUERYPERSRANK = "homepage/persrank";
		public static String QUERYACCOUNT = "homepage/account";
		
	}
	
	/**公告列表*/
	public static final class SystemAffiche{
		public static String QUERY = "affiche/selectTable";
	}
	
	public static final class Salesman{
        public static String QUERY = "salesman/salesmaninfo";
    }
	
	
}