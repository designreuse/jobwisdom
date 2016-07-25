package com.zefun.web.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.ExcelUtil;
import com.zefun.common.utils.ExcleUtils;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EmployeeLevel;
import com.zefun.web.entity.EmployeeObjective;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.RoleInfo;
import com.zefun.web.entity.ShiftMahjongProjectStep;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.UserAccount;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeLevelMapper;
import com.zefun.web.mapper.EmployeeObjectiveMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.RoleInfoMapper;
import com.zefun.web.mapper.ShiftMahjongProjectStepMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.UserAccountMapper;
import com.zefun.web.mapper.WechatEmployeeMapper;

import net.sf.json.JSONArray;

/**
 * 人员信息
 * 
 * @author 陈端斌
 * @date 2015年8月7日 下午5:21:00
 *
 */
@Service
public class EmployeeService {
	
	/** 轮牌项目步骤关系映射 */
	@Autowired
	private ShiftMahjongProjectStepMapper shiftMahjongProjectStepMapper;
	
	/**
	 * 员工信息
	 */
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	/**
	 * 岗位信息
	 */
	@Autowired
	private PositioninfoMapper positioninfoMapper;
	/**
	 * 部门
	 */
	@Autowired
	private DeptInfoMapper deptInfoMapper;
	/**
	 * 轮牌接口
	 */
	@Autowired
	ShiftMahjongService shiftMahjongService;
	/**
	 * 角色接口
	 */
	@Autowired
	private RoleInfoMapper roleInfoMapper;
	/**
	 * 账户接口
	 */
	@Autowired
	private UserAccountMapper userAccountMapper;
	/**
	 * 职位
	 */
	@Autowired
	private EmployeeLevelMapper employeeLevelMapper;
	/**
	 * Redis
	 */
	@Autowired
	private RedisService redisService;

	/** 目标 */
	@Autowired
	private EmployeeObjectiveMapper employeeObjectiveMapper;

	/** 员工微信映射操作对象 */
	@Autowired
	private WechatEmployeeMapper wechatEmployeeMapper;
	
	/** 门店*/
	@Autowired
	private StoreInfoMapper storeInfoMapper;

	/**
	 * 查询某个店铺的职位信息 默认返回该门店最前面10条数据
	 * 
	 * @author 陈端斌
	 * @date 2015年8月7日 下午1:59:44
	 * @param params
	 *            参数
	 * @return ModelAndView
	 */
	public ModelAndView queryEmployeeInfo(Map<String, Object> params) {
		// Page<EmployeeDto> page=selectPageForEmployee(params, 1, 100);
		ModelAndView mav = new ModelAndView("employee/employee/employee");

		// 获取人员 选择推荐人要用的下拉框
		Integer storeId = Integer.parseInt(params.get("storeId").toString());
		List<EmployeeInfo> recommendList = employeeInfoMapper.getRecommendlist(storeId);
		mav.addObject("recommendList", recommendList);

		// 获取部门列表
		List<DeptInfo> deptlist = deptInfoMapper.getDeptInfo(storeId);
		mav.addObject("deptlist", deptlist);

		// 获取岗位信息一起返回到页面
		PositionInfo positionInfo = new PositionInfo();
		positionInfo.setStoreId(storeId);
		List<PositionInfo> positionlist = positioninfoMapper.queryposition(positionInfo);
 		mav.addObject("positionlist", positionlist);
		
		List<EmployeeLevel> employeeLevels = employeeLevelMapper.selectAllByStoreId(storeId);
		mav.addObject("employeeLevels", JSONArray.fromObject(employeeLevels));
		
		List<EmployeeDto> employeeDtoList = employeeInfoMapper.selectAllEmployeeByCondition(params);
		mav.addObject("employeeDtoList", employeeDtoList);
		
		// 获取人员角色
		List<RoleInfo> rolelist = roleInfoMapper.selectAllRoles();
		mav.addObject("rolelist", rolelist);
		
		List<Map<String, Object>> storeDtoList = new ArrayList<>();
		
		StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
		
		Map<String, Object> storeMap = new HashMap<>();
		storeMap.put("dateId", storeId);
		storeMap.put("name", storeInfo.getStoreName());
		storeMap.put("dateType", 0);
		List<Map<String, Object>> deptDtoList = new ArrayList<>();
		
//		List<DeptInfoDto> list =positioninfoMapper.getDetpInfo(storeId);
//		//组装组织架构树形图数据
//		if (list != null && list.size() > 0) {
//			for (DeptInfoDto deptInfoDto : list) {
//				Integer deptId = deptInfoDto.getDeptId();
//				String name = deptInfoDto.getDeptName();
//				Map<String, Object> deptMap = new HashMap<>();
//				deptMap.put("dateId", deptId);
//				deptMap.put("name", name);
//				deptMap.put("dateType", 1);
//				if (deptInfoDto.getPositionInfo()!= null && deptInfoDto.getPositionInfo().size() > 0) {
//					List<PositionInfoDto> positionInfos = deptInfoDto.getPositionInfo();
//					List<Map<String, Object>> positionDtoList = new ArrayList<>();
//					for (PositionInfoDto positionInfoDto : positionInfos) {
//						Integer positionId = positionInfoDto.getPositionId();
//						String positionName = positionInfoDto.getPositionName();
//						Map<String, Object> positionMap = new HashMap<>();
//						positionMap.put("dateId", positionId);
//						positionMap.put("name", positionName);
//						positionMap.put("dateType", 2);
//						
//						if (positionInfoDto.getEmployeeLeve()!= null && positionInfoDto.getEmployeeLeve().size() > 0) {
//							List<EmployeeLevel> employeeLevels = positionInfoDto.getEmployeeLeve();
//							List<Map<String, Object>> levelDtoList = new ArrayList<>();
//							for (EmployeeLevel employeeLevel : employeeLevels) {
//								Integer levelId = employeeLevel.getLevelId();
//								String levelName = employeeLevel.getLevelName();
//								Map<String, Object> levelMap = new HashMap<>();
//								levelMap.put("dateId", levelId);
//								levelMap.put("name", levelName);
//								levelMap.put("dateType", 3);
//								levelDtoList.add(levelMap);
//							}
//							positionMap.put("children", levelDtoList);
//						}
//						positionDtoList.add(positionMap);
//					}
//					deptMap.put("children", positionDtoList);
//				}
//				deptDtoList.add(deptMap);
//			}
//		}
		storeMap.put("children", deptDtoList);
		
		storeDtoList.add(storeMap);
		
		mav.addObject("storeDtoListStr", JSONArray.fromObject(storeDtoList).toString());
		
		return mav;
	}

	/**
	 * 翻页功能
	 * 
	 * @author 陈端斌
	 * @date 2015年8月7日 上午10:55:59
	 * @param params
	 *            参数
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * @return BaseDto
	 */
	public BaseDto listAction(Map<String, Object> params, int pageNo, int pageSize) {
		Page<EmployeeDto> page = selectPageForEmployee(params, pageNo, pageSize);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
	}

	/**
	 * 查询员工
	* @author 老王
	* @date 2016年5月28日 下午6:54:58 
	* @param dateType 部门 类型
	* @param dateId 标识
	* @return BaseDto
	 */
	public BaseDto selectEmployeeBydateType (Integer dateType, Integer dateId) {
		Map<String, Integer> params =  new HashMap<>();
		if (dateType == 0) {
			params.put("storeId", dateId);
		}
		else if (dateType == 1) {
			params.put("deptId", dateId);
		}
		else if (dateType == 2) {
			params.put("positionId", dateId);
		}
		else {
			params.put("levelId", dateId);
		}
		List<EmployeeDto> employeeDtoList = employeeInfoMapper.selectAllEmployeeByCondition(params);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, employeeDtoList);
	}
	
	/**
	 * 
	 * @author chendb
	 * @date 2015年8月11日 上午11:20:47
	 * @param params
	 *            参数
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * @return Page<EmployeeDto>
	 */
	private Page<EmployeeDto> selectPageForEmployee(Map<String, Object> params, int pageNo, int pageSize) {
		Page<EmployeeDto> page = new Page<EmployeeDto>();
		page.setParams(params);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		List<EmployeeDto> list = employeeInfoMapper.queryEmployeeInfo(page);
		page.setResults(list);
		return page;
	}

	/**
	 * 新增人员资料信息
	 * 
	 * @author 陈端斌
	 * @date 2015年8月10日 下午4:49:20
	 * @param employeeDto
	 *            参数
	 * @return int
	 */
	@Transactional
	public int addEmployee(EmployeeDto employeeDto) {
		// 判断编码是否已经存在了
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employeeCode", employeeDto.getEmployeeCode());
		map.put("storeId", employeeDto.getStoreId());
		EmployeeInfo info = employeeInfoMapper.queryEmployeeCode(map);
		if (info != null) {
			if (info.getEmployeeId() != null) {
				// 已经被引用了
				return 1;
			}
		}
		// 设置默认性别
		/* Object sex = map.get("sex"); */
		String sex = employeeDto.getSex();
		if (sex == null || sex == "") {
			employeeDto.setSex("男");
			;
		}
		// 设置默认头像
		String headImg = employeeDto.getHeadImage();
		if (headImg == null || StringUtils.isEmpty(headImg)) {
			if ("男".equals(sex)) {
				employeeDto.setHeadImage("pc/defaulf_male.png");
				// map.put("headImage", "pc/defaulf_male.png");
			} 
			else {
				employeeDto.setHeadImage("pc/defaulf_female.png");
				// map.put("headImage", "pc/defaulf_female.png");
			}
		}
		employeeInfoMapper.insert(employeeDto);
		// 初始密码 和颜值
		String password = StringUtil.md5(StringUtil.md5("123456"));
		String hash = StringUtil.encryptPwd(password);
		password = hash.split(":")[0];
		String salt = hash.split(":")[1];
		UserAccount userAccount = new UserAccount();
		userAccount.setUserPwd(password);
		userAccount.setPwdSalt(salt);
		userAccount.setUserId(employeeDto.getEmployeeId());
		userAccount.setUserName(employeeDto.getPhone());
		userAccount.setRoleId(employeeDto.getRoleId());
		userAccount.setStoreId(employeeDto.getStoreId());
		// 判断账号是否已经被用了
		UserAccount userAccount1 = new UserAccount();
		// 第一次新增的手机号码就是他的账号
		userAccount1.setUserName(employeeDto.getPhone());
		userAccount1.setStoreId(employeeDto.getStoreId());
		userAccount.setCreateTime(DateUtil.getCurTime());
		int count = userAccountMapper.countUserName(userAccount1);
		if (count > 0) {
			// 删除掉那个员工资料
			employeeInfoMapper.deleteByPrimaryKey(employeeDto.getEmployeeId());
			return 2;
		}
		userAccountMapper.insert(userAccount);
		String recommendId = "";
		if (employeeDto.getRecommendId() != null) {
			recommendId = employeeDto.getRecommendId().toString();
		}
		// 判断是否有介绍人
		if (recommendId.length() > 0) {
			// 人员关系录入
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("referrerId", employeeDto.getRecommendId());
			map1.put("employeeId", employeeDto.getEmployeeId());
			map1.put("createTime", employeeDto.getCreateTime());
			map1.put("lastOperatorId", employeeDto.getLastOperatorId());
			employeeInfoMapper.employeeRecommend(map1);
		}
		// 新增時候調用老王轮牌接口
		/*
		 * JSONObject jsonBean = JSONObject.fromObject(map); EmployeeDto bean =
		 * (EmployeeDto) JSONObject.toBean(jsonBean, EmployeeDto.class);
		 */
		shiftMahjongService.addShiftMahjongEmployee(employeeDto);

		// 设置初始目标
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String objectiveMonth = dateFormat.format(new Date());
		EmployeeObjective objectiveDto = new EmployeeObjective();
		objectiveDto.setEmployeeId(employeeDto.getEmployeeId());
		objectiveDto.setObjectiveMonth(objectiveMonth);
		objectiveDto.setCreateTime(DateUtil.getCurTime());
		employeeObjectiveMapper.insertSelective(objectiveDto);

		redisService.hset(App.Redis.WECHAT_EMPLOYEEID_TO_DEPT, employeeDto.getEmployeeId(), employeeDto.getDeptId());
		return 0;
	}

	/**
	 * 修改人员信息
	 * 
	 * @author chendb
	 * @date 2015年8月14日 下午3:59:06
	 * @param employeeDto
	 *            参数
	 * @return int
	 */
	@Transactional
	public int updateEmployee(EmployeeDto employeeDto) {
		// 判断编码是否已经存在了
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("employeeCode", employeeDto.getEmployeeCode());
		map1.put("storeId", employeeDto.getStoreId());
		// 判断编码是否已经存在了
		EmployeeInfo info = employeeInfoMapper.queryEmployeeCode(map1);
		if (info != null) {
			if (info.getEmployeeId() != null) {
				int id = info.getEmployeeId();
				int id1 = employeeDto.getEmployeeId();
				if (id != id1) {
					// 已经被引用了
					return 1;
				}
			}
		}
		String recommendId = "";
		if (employeeDto.getRecommendId() != null) {
			recommendId = employeeDto.getRecommendId().toString();
		}
		if (recommendId == "" || recommendId.length() < 1) {
			recommendId = null;
			employeeDto.setRecommendId(null);
		}
		if (employeeDto.getIdentityCard() == null) {
			employeeDto.setIdentityCard("");
		}
		employeeInfoMapper.updateByPrimaryKey(employeeDto);
		// 修改人员账号角色
		UserAccount userInfo = new UserAccount();
		userInfo.setUserId(employeeDto.getEmployeeId());
		userInfo.setRoleId(employeeDto.getRoleId());
		userInfo.setUserName(employeeDto.getPhone());
		userAccountMapper.updateUserRole(userInfo);
		// 先删除之前的介绍人
		int employeeId = employeeDto.getEmployeeId();
		employeeInfoMapper.deleteRecommend(employeeId);
		// 判断是否有介绍人
		if (recommendId != null) {
			// 人员关系录入
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("referrerId", employeeDto.getRecommendId());
			map2.put("employeeId", employeeDto.getEmployeeObjective());
			map2.put("createTime", employeeDto.getCreateTime());
			map2.put("lastOperatorId", employeeDto.getLastOperatorId());
			employeeInfoMapper.employeeRecommend(map2);
		}
		// 新增時候調用老王轮牌接口
		/*
		 * JSONObject jsonBean = JSONObject.fromObject(map); EmployeeDto bean =
		 * (EmployeeDto) JSONObject.toBean(jsonBean, EmployeeDto.class);
		 */
		shiftMahjongService.updateShiftMahjongEmployee(employeeDto);
		// String dept = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_DEPT,
		// map.get("employeeId").toString());
		redisService.hset(App.Redis.WECHAT_EMPLOYEEID_TO_DEPT, employeeDto.getEmployeeId(), employeeDto.getDeptId());

		return 0;
	}

	/**
	 * 新增培训经历
	 * 
	 * @author chendb
	 * @date 2015年8月12日 下午6:27:35
	 * @param employeeId
	 *            员工标识
	 * @param maplist
	 *            参数集合
	 * @return int
	 */
	@Transactional
	public int addpx(Integer employeeId, List<Map<String, Object>> maplist) {
		// 先清除之前的数据
		employeeInfoMapper.deletepx(employeeId);
		if (maplist.size() > 0) {
			for (int i = 0; i < maplist.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map = maplist.get(i);
				employeeInfoMapper.addpx(map);
			}
		}
		return 0;
	}

	/**
	 * 查询培训信息
	 * 
	 * @author chendb
	 * @date 2015年8月17日 上午10:19:57
	 * @param employeeId
	 *            员工标识
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> querypx(Integer employeeId) {
		List<Map<String, Object>> list = employeeInfoMapper.querypx(employeeId);
		return list;
	}

	/**
	 * 新增工作经历
	 * 
	 * @author chendb
	 * @date 2015年8月13日 上午9:39:54
	 * @param employeeId
	 *            员工标识
	 * @param maplist
	 *            集合
	 * @return int
	 */
	@Transactional
	public int addgz(Integer employeeId, List<Map<String, Object>> maplist) {
		// 先清除之前的数据
		employeeInfoMapper.deletegz(employeeId);
		if (maplist.size() > 0) {
			for (int i = 0; i < maplist.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map = maplist.get(i);
				employeeInfoMapper.addgz(map);
			}
		}
		return 0;
	}

	/**
	 * 员工工作信息查询
	 * 
	 * @author chendb
	 * @date 2015年8月17日 上午11:47:13
	 * @param employeeId
	 *            员工标识
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> querygz(Integer employeeId) {
		List<Map<String, Object>> list = employeeInfoMapper.querygz(employeeId);
		return list;
	}

	/**
	 * 新增擅长信息
	 * 
	 * @author chendb
	 * @date 2015年8月13日 下午2:09:22
	 * @param employeeId
	 *            员工标识
	 * @param maplist
	 *            参数
	 * @return int
	 */
	@Transactional
	public int addsc(Integer employeeId, List<Map<String, Object>> maplist) {
		// 先清除之前的数据
		employeeInfoMapper.deletesc(employeeId);
		if (maplist.size() > 0) {
			for (int i = 0; i < maplist.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map = maplist.get(i);
				employeeInfoMapper.addsc(map);
			}
		}
		return 0;
	}

	/**
	 * 擅长信息
	 * 
	 * @author chendb
	 * @date 2015年8月17日 下午2:33:47
	 * @param employeeId
	 *            员工信息
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> querysc(Integer employeeId) {
		List<Map<String, Object>> list = employeeInfoMapper.querysc(employeeId);
		return list;
	}

	/**
	 * 根据员工标识获取员工详情
	 * 
	 * @author chendb
	 * @date 2015年8月14日 上午10:59:56
	 * @param employeeId
	 *            员工标识
	 * @return List<Map<String, Object>>
	 */
	public Map<String, Object> getDetail(Integer employeeId) {

		Map<String, Object> info = employeeInfoMapper.getDetail(employeeId);
		// 获取人员角色
//		UserAccount userAccount = userAccountMapper.queryRoleId(employeeId);
//		if (userAccount == null) {
//			info.put("roleId", "");
//		} 
//		else {
//			info.put("roleId", userAccount.getRoleId());
//		}
		return info;
	}

	/**
	 * 获取员工关系
	 * 
	 * @author chendb
	 * @date 2015年8月19日 上午11:53:15
	 * @param employeeId
	 *            员工标识
	 * @return Map<Object, Object>
	 */
	public Map<Object, Object> queryrygx(Integer employeeId) {
		// 获取员工信息
		Map<String, Object> selfinfo = employeeInfoMapper.getDetail(employeeId);
		// 获取推荐人相关信息
		Map<String, Object> referrerinfo = employeeInfoMapper.getreferrer(employeeId);
		// 获取被推荐的人信息列表
		List<Map<String, Object>> recommendedlist = employeeInfoMapper.getrecommended(employeeId);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("selfinfo", selfinfo);
		map.put("referrerinfo", referrerinfo);
		map.put("recommendedlist", recommendedlist);

		return map;
	}

	/**
	 * 根据部门标识获取人员
	 * 
	 * @author chendb
	 * @date 2015年9月11日 下午3:20:57
	 * @param deptId
	 *            部门标识
	 * @return List<EmployeeDto>
	 */
	public List<EmployeeDto> getDeptEmployee(Integer deptId) {
		List<EmployeeDto> list = employeeInfoMapper.getDeptEmployee(deptId);
		return list;
	}

	/**
	 * 根据标识获取详情展示 公共
	 * 
	 * @author chendb
	 * @date 2015年9月16日 上午10:45:31
	 * @param employeeId
	 *            员工标识
	 * @return List<Map<Object, Object>>
	 */
	/*
	 * public Map<Object, Object> employeedetailshow(Integer employeeId){
	 * Map<Object, Object> info=new HashMap<Object, Object>(); //获取基本资料信息
	 * Map<String, Object> detail= employeeInfoMapper.getDetail(employeeId);
	 * info.put("detail", detail); //获取培训信息 List<Map<String, Object>>
	 * pxlist=employeeInfoMapper.querypx(employeeId); info.put("pxlist",
	 * pxlist); //获取工作经验 List<Map<String, Object>>
	 * gzlist=employeeInfoMapper.querygz(employeeId); //获取擅长项目 List<Map<String,
	 * Object>> sclist=employeeInfoMapper.querysc(employeeId); //获取奖惩记录
	 * Map<String, Object>map=new HashMap<String, Object>();
	 * rewardsRuleMapper.getrewardsrecord(map); //获取推荐关系
	 * 
	 * 
	 * return info; }
	 */
	/**
	 * 
	 * @author chendb
	 * @date 2015年9月24日 下午4:14:19
	 * @param map
	 *            参数
	 * @return int
	 */
	@Transactional
	public int adddispatch(Map<String, Object> map) {
		employeeInfoMapper.adddispatch(map);
		map.put("employeeStatus", 3);
		employeeInfoMapper.updatestatus(map);
		return 0;
	}

	/**
	 * 获取派遣
	 * 
	 * @author chendb
	 * @date 2015年9月24日 下午5:32:26
	 * @param employeeId
	 *            员工标识
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getpqlist(Integer employeeId) {
		List<Map<String, Object>> list = employeeInfoMapper.getpqlist(employeeId);
		return list;
	}

	/**
	 * 导出excle
	 * 
	 * @author chendb
	 * @date 2015年10月8日 下午17:09:51
	 * @param response
	 *            返回
	 * @param map
	 *            返回
	 * @return BaseDto
	 */
	public BaseDto downloadExcle(HttpServletResponse response, Map<String, Object> map) {
		String fileName = "员工基本资料";
		List<EmployeeDto> employeeList = employeeInfoMapper.queryEmployeeList(map);
		List<Map<String, Object>> list = createExcelRecord(employeeList);
		// 列名
		String[] columnNames = { "员工编码", "员工姓名", "手机号码", "性别", "生日", "部门", "岗位", "职位", "到岗时间" };
		// map中的key
		String[] keys = { "employeeCode", "name", "phone", "sex", "birthday", "deptName", "positionName", "levelName", "entryDate" };
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} 
		catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (bis != null) {
				try {
					bis.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @author chendb
	 * @date 2015年10月8日 下午4:22:36
	 * @param employeeList
	 *            数据集合
	 * @return List<Map<String, Object>>
	 */
	private List<Map<String, Object>> createExcelRecord(List<EmployeeDto> employeeList) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		EmployeeDto employeeDto = null;
		for (int j = 0; j < employeeList.size(); j++) {
			employeeDto = employeeList.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("employeeCode", employeeDto.getEmployeeCode());
			mapValue.put("name", employeeDto.getName());
			mapValue.put("phone", employeeDto.getPhone());
			mapValue.put("sex", employeeDto.getSex());
			mapValue.put("birthday", employeeDto.getBirthday());
			mapValue.put("deptName", employeeDto.getDeptName());
			mapValue.put("positionName", employeeDto.getPositionName());
			mapValue.put("levelName", employeeDto.getLevelName());
			mapValue.put("entryDate", employeeDto.getEntryDate());
			listmap.add(mapValue);
		}
		return listmap;
	}

	/**
	 * 删除员工数据
	 * 
	 * @author chendb
	 * @date 2015年10月8日 下午5:22:23
	 * @param employeeId 标识
	 * @return int
	 */
	@Transactional
	public int deleteEmployee(Integer employeeId) {
		/**
		 * 删除员工之前需要在轮牌项目步骤关系表(shift_mahjong_project_step)
		 * 判断该员工当前状态(is_over)是否是服务中或等待中,if是就不能删除
		 */
		List<ShiftMahjongProjectStep> stepList = shiftMahjongProjectStepMapper.selectStepByStatusOfInServeOrWating(employeeId);
		if (stepList != null && stepList.size() != 0) {
			return -1;
		}
		// 调用轮牌员工删除接口
		employeeInfoMapper.deleteEmployee(employeeId);
		shiftMahjongService.deleteShiftMahjongEmployee(employeeId);
		employeeInfoMapper.deleteUser(employeeId);
		// 清除微信绑定数据
		wipeCache(employeeId);
		return 0;

	}

	/**
	 * 新增修改人员简介
	 * 
	 * @author chendb
	 * @date 2015年11月7日 上午10:53:37
	 * @param employeeId
	 *            员工标识
	 * @param desc
	 *            描述
	 * @return int
	 */
	public int savedesc(Integer employeeId, String desc) {
		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setEmployeeId(employeeId);
		employeeInfo.setEmployeeDesc(desc);
		return employeeInfoMapper.updateByPrimaryKeySelective(employeeInfo);
	}

	/**
	 * 员工导入
	 * 
	 * @author chendb
	 * @date 2015年10月13日 下午5:26:51
	 * @param file
	 *            对象
	 * @param temp
	 *            临时
	 * @param storeId
	 *            门店标识
	 * @return BaseDto
	 */
	@Transactional
	public BaseDto importExcle(MultipartFile file, String temp, Integer storeId) {

		File tempFile = new File(temp);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		if (file == null) {
			return null;
		}
		// 获取上传文件名,包括路径
		String name = file.getOriginalFilename();
		long size = file.getSize();
		if ((name == null || name.equals("")) && size == 0) {
			return null;
		}
		// 判断是否是excel2007格式
		boolean isE2007 = false;
		if (name.endsWith("xlsx")) {
			isE2007 = true;
		}
		try {
			// 建立输入流
			InputStream input = file.getInputStream();
			Workbook wb = null;
			// 根据文件格式(2003或者2007)来初始化
			if (isE2007) {
				wb = new XSSFWorkbook(input);
			} 
			else {
				wb = new HSSFWorkbook(input);
			}
			// 获得第一个表单
			Sheet sheet = wb.getSheetAt(0);
			sheet.getFirstRowNum();
			// 获得第一个表单的迭代器
			Iterator<Row> rows = sheet.rowIterator();
			List<EmployeeDto> listDto = new ArrayList<EmployeeDto>();
			while (rows.hasNext()) {
				EmployeeDto employeeDto = new EmployeeDto();
				// objectiveDto.setStoreId(storeId);
				// 获得行数据
				Row row = rows.next();
				// 临时实体，作用是最后判断该行是否是无效数据
				EmployeeDto copyemployeeDto = new EmployeeDto();
				if (row.getRowNum() > 0) {
					int deptCode = 0;
					int positionId = 0;
					int positionCode = 0;
					String employeeCodeSuffix = ""; // 员工编码后两位(用户在excel输入的两位数字工牌号)

					Iterator<Cell> cells = row.cellIterator();
					String columnValue = "";
					while (cells.hasNext()) {
						Cell cell = cells.next();
						switch (cell.getColumnIndex()) {
    						// 员工姓名
    						case 0:
    							columnValue = changeCellToString(cell);
    							if (columnValue != null && !columnValue.equals("")) {
    								copyemployeeDto.setName(columnValue);
    							}
    							break;
    						// 工牌号 : 员工编码是一位数字的部门号和一位数字的岗位号加两位数字的工牌号组成(eg:1101)
    						case 1:
    							columnValue = changeCellToString(cell);
    							if (columnValue != null && !columnValue.equals("")) {
    								// copyemployeeDto.setEmployeeCode(deptCode +
    								// positionCode +
    								// Integer.parseInt(columnValue));
    								copyemployeeDto.setEmployeeCode(Integer.parseInt(columnValue));
    							}
    							break;
    						// 手机号码
    						case 2:
    							columnValue = changeCellToString(cell);
    							if (columnValue != null && !columnValue.equals("")) {
    								copyemployeeDto.setPhone(columnValue);
    							}
    							break;
    						// 员工角色
    						case 3:
    							columnValue = changeCellToString(cell);
    							if (columnValue != null && !columnValue.equals("")) {
    								copyemployeeDto.setRoleId(roleInfoMapper.selectRoleIdByRoleName(columnValue));
    							}
    							break;
    						// 性别
    						case 4:
    							columnValue = changeCellToString(cell);
    							if (columnValue != null && !columnValue.equals("")) {
    								copyemployeeDto.setSex(columnValue);
    							}
    							break;
    						// 部门
    						case 5:
    							columnValue = changeCellToString(cell);
    							if (columnValue != null && !columnValue.equals("")) {
    								copyemployeeDto.setDeptName(columnValue);
    							}
    							break;
    						// 岗位
    						case 6:
    							columnValue = changeCellToString(cell);
    							if (columnValue != null && !columnValue.equals("")) {
    								copyemployeeDto.setPositionName(columnValue);
    							}
    							break;
    						// 职位
    						case 7:
    							columnValue = changeCellToString(cell);
    							if (columnValue != null && !columnValue.equals("")) {
    								copyemployeeDto.setLevelName(columnValue);
    							}
    							break;
    						default:
    							break;
						}
					}

					// 防止一行有格式的无效数据
					if (copyemployeeDto.getDeptName() == null && copyemployeeDto.getPositionName() == null
							    && copyemployeeDto.getLevelName() == null && copyemployeeDto.getEmployeeCode() == null
							    && copyemployeeDto.getName() == null && copyemployeeDto.getRoleId() == null
							    && copyemployeeDto.getSex() == null && copyemployeeDto.getPhone() == null) {
						break;
					}

					Iterator<Cell> cellss = row.cellIterator();
					while (cellss.hasNext()) {
						Cell cell = cellss.next();
						if (cell.getColumnIndex() == 0) {
							String returnValue = changeCellToString(cell);
							if (returnValue == null || returnValue == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员工姓名不能为空！");
							}
							employeeDto.setName(returnValue);
						} 
						else if (cell.getColumnIndex() == 1) {
							String returnValue = changeCellToString(cell);
							if (returnValue == null || returnValue == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行工号不能为空！");
							}
							// 排除填写单位数字 1-9，自动往前面加上0
							if (returnValue.length() == 1) {
								returnValue = "0" + returnValue;
							}
							Pattern pattern = Pattern.compile("^\\d{2}$");
							if (!pattern.matcher(returnValue).matches()) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行工号只需填2位数字!");
							}
							// 工牌号 : 员工编码是一位数字的部门号和一位数字的岗位号加两位数字的工牌号组成(eg:1101)
							String employeeCode = deptCode + "" + positionCode + returnValue;
							employeeCodeSuffix = returnValue;
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("employeeCode", employeeDto.getEmployeeCode());
							map.put("storeId", employeeDto.getStoreId());
							EmployeeInfo info = employeeInfoMapper.queryEmployeeCode(map);
							if (info != null) {
								if (info.getEmployeeId() != null) {
									// 已经被引用了
									return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员工编码已经存在！");
								}
							}

							employeeDto.setEmployeeCode(Integer.parseInt(employeeCode));
						} 
						else if (cell.getColumnIndex() == 2) {
							if (cell.toString() == null || cell.toString() == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员工手机号码不能为空！");
							}
							//"1.3528734801E10", "13554912187 "
							String phoneString = cell.toString().trim();
							String ii = null;
							if (phoneString.contains("E")) {
								BigDecimal db = new BigDecimal(phoneString.trim());
								ii = db.toPlainString();
							}
							else {
								//"13554912187 "：全是数字的字符串trim,replaceAll最后个空格是去不掉的，其他位置可以去掉
								if (phoneString.length() != 11) {
									phoneString = phoneString.substring(0, phoneString.length() - 1);
								}
								ii = phoneString;
							}
							cell.setCellValue(ii);
							String returnValue = changeCellToString(cell);
							if (returnValue == null || returnValue == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员工手机号码不能为空！");
							}
							if (returnValue.length() != 11) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员工手机号码不正确！");
							}
							UserAccount userInfo = new UserAccount();
							userInfo.setUserName(returnValue);
							int count = userAccountMapper.countUserName(userInfo);
							if (count > 0) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员工手机号码已经存在！");
							}
							// 判断是否是科学记数法
							/*
							 * if (DataTypeUtil.isNumeric(returnValue)) {
							 * employeeDto.setPhone(Integer.parseInt(
							 * returnValue) + ""); } else {
							 * employeeDto.setPhone(returnValue); }
							 */
							employeeDto.setPhone(returnValue);
						} 
						else if (cell.getColumnIndex() == 3) {
							String returnValue = changeCellToString(cell);
							if (returnValue == null || returnValue == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员工角色不能为空！");
							}
							/*
							 * if (!returnValue.endsWith("超级管理员")&&!returnValue.
							 * endsWith("高级管理员")&&!returnValue.endsWith("管理员")
							 * &&!returnValue.endsWith("普通员工")&&!returnValue.
							 * endsWith("收银员")){ return new BaseDto(-1, "第 "
							 * +row.getRowNum()+
							 * "行员工角色只能是:超级管理员、高级管理员、管理员、普通员工或收银员其中的一种！"); }
							 */
							RoleInfo roleinfo = roleInfoMapper.queryRoleInfo(returnValue);
							if (roleinfo == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员工角色不存在！");
							}
							employeeDto.setRoleId(roleinfo.getRoleId());
						} 
						else if (cell.getColumnIndex() == 4) {
							String returnValue = changeCellToString(cell);
							if (returnValue == null || returnValue == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行性别不能为空！");
							}
							if (returnValue.equals("男") || returnValue.equals("女")) {
								if ("男".equals(returnValue)) {
									employeeDto.setHeadImage("pc/defaulf_male.png");
								} 
								else {
									employeeDto.setHeadImage("pc/defaulf_male.png");
								}
								employeeDto.setSex(returnValue);
							} 
							else {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行员性别只能填写男或者女！");
							}

						} 
						else if (cell.getColumnIndex() == 5) {
							String returnValue = changeCellToString(cell);
							if (returnValue == null || returnValue == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行部门不能为空！");
							}
							DeptInfo deptInfo = new DeptInfo();
							deptInfo.setStoreId(storeId);
							deptInfo.setDeptName(returnValue);
							DeptInfo info = deptInfoMapper.getDeptDetail(deptInfo);
							if (info == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行部门不存在,请查询后确认再填写");
							}
							deptCode = info.getDeptCode();
							employeeDto.setDeptId(info.getDeptId());
						} 
						else if (cell.getColumnIndex() == 6) {
							String returnValue = changeCellToString(cell);
							if (returnValue == null || returnValue == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行岗位不能为空！");
							}
							PositionInfo positionInfo = new PositionInfo();
							positionInfo.setPositionName(returnValue);
							PositionInfo info = positioninfoMapper.queryPositiondetail(positionInfo);
							if (info == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行该部门下的岗位不存在,请查询后确认再填写！");
							}
							positionId = info.getPositionId();
							employeeDto.setPositionId(info.getPositionId());
						} 
						else if (cell.getColumnIndex() == 7) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("positionId", positionId);

							String returnValue = changeCellToString(cell);
							map.put("levelName", returnValue);
							if (returnValue == null || returnValue == "") {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行职位不能为空！");
							}
							EmployeeLevel bean = employeeLevelMapper.countlevel(map);
							if (bean == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行该岗位下的职位不存在,请查询后确认再填写！");
							}
							employeeDto.setLevelId(bean.getLevelId());
						}
					}

					/*
					 * 最后需要拼接员工编码(employeeCode)
					 * 由于excel的部门和岗位调整顺序在用户填写工牌后面，导致上面的deptId,positionId没有得到值,
					 * 故在每次循环最后给employeeCode赋值
					 */
					employeeDto.setEmployeeCode(Integer.parseInt(deptCode + "" + positionCode + employeeCodeSuffix));
					// 导入员工时候默认是 角色员工
					employeeDto.setStoreId(storeId);
					String password = StringUtil.md5(StringUtil.md5("123456"));
					String hash = StringUtil.encryptPwd(password);
					password = hash.split(":")[0];
					String salt = hash.split(":")[1];
					employeeDto.setPwdSalt(salt);
					employeeDto.setUserPwd(password);
					employeeDto.setNum(row.getRowNum());
					employeeDto.setCreateTime(DateUtil.getCurTime());
					employeeDto.setEmployeeStatus("1");
					listDto.add(employeeDto);
				}
			}

			// 插入数据
			if (listDto.size() > 0) {

				Set<String> set = new HashSet<String>();
				Set<String> set1 = new HashSet<String>();
				for (int i = 0; i < listDto.size(); i++) {
					EmployeeDto employeeDto = listDto.get(i);
					if (!set.add(employeeDto.getEmployeeCode().toString())) {
						return new BaseDto(-1, "第" + (employeeDto.getNum() + 1) + "行编码存在重复，请确认后再导入！");
					}
					if (!set1.add(employeeDto.getPhone())) {
						return new BaseDto(-1, "第" + (employeeDto.getNum() + 1) + "行手机号码存在重复，请确认后再导入，手机号码将作为账号！");
					}
				}

				List<EmployeeObjective> objectiveList = new ArrayList<EmployeeObjective>(listDto.size());
				String curMonth = DateUtil.getCurMonth();
				String curTime = DateUtil.getCurTime();
				for (int i = 0; i < listDto.size(); i++) {
					EmployeeDto employeeDto = listDto.get(i);
					// 新增人员资料
					employeeInfoMapper.insertList(employeeDto);
					// 新增人员账号
					userAccountMapper.insertlist(employeeDto);
					// 调用轮牌接口
					shiftMahjongService.addShiftMahjongEmployee(employeeDto);

					EmployeeObjective employeeObjective = new EmployeeObjective();
					employeeObjective.setEmployeeId(employeeDto.getEmployeeId());
					employeeObjective.setObjectiveMonth(curMonth);
					employeeObjective.setCreateTime(curTime);
					objectiveList.add(employeeObjective);
				}
				// 新增人员目标
				employeeObjectiveMapper.insertBatch(objectiveList);
			}
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功！");
	}

	/**
	 * 
	 * @author chendb
	 * @date 2015年10月13日 下午5:24:51
	 * @param cell
	 *            c
	 * @return String
	 */
	public String changeCellToString(Cell cell) {

		String returnValue = "";

		if (null != cell) {

			switch (cell.getCellType()) {
    			// 数字
    			case HSSFCell.CELL_TYPE_NUMERIC:
    				Double doubleValue = cell.getNumericCellValue();
    				String str = doubleValue.toString();
    				if (str.contains(".0")) {
    					str = str.replace(".0", "");
    				}
    				/**
    				 * 手机号码会进入此处，需判断下是否是科学记数法需要转换
    				 */
    				if (str.contains("E")) {
    					returnValue = ExcleUtils.phoneScientificNotationToFormat(str);
    				}
    				else {
    					Integer intValue = Integer.parseInt(str);
        				returnValue = intValue.toString();
    				}
    				break;
    			case HSSFCell.CELL_TYPE_STRING:
    				// 字符串
    				returnValue = cell.getStringCellValue();
    				break;
    			case HSSFCell.CELL_TYPE_BOOLEAN:
    				// 布尔
    				Boolean booleanValue = cell.getBooleanCellValue();
    				returnValue = booleanValue.toString();
    				break;
    			// 空值
    			case HSSFCell.CELL_TYPE_BLANK:
    				returnValue = "";
    				break;
    			// 公式
    			case HSSFCell.CELL_TYPE_FORMULA:
    				returnValue = cell.getCellFormula();
    				break;
    			// 故障
    			case HSSFCell.CELL_TYPE_ERROR:
    				returnValue = "";
    				break;
    			default:
    				break;
			}
		}
		return returnValue;

	}

	/**
	 * 根据员工标识获取部门标识
	 * 
	 * @author 张进军
	 * @date Nov 27, 2015 4:52:16 PM
	 * @param employeeId
	 *            员工标识
	 * @return 部门标识
	 */
	public int getDeptIdByEmployeeId(int employeeId) {
		String d = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_DEPT, employeeId);
		int deptId = 0;
		if (StringUtils.isBlank(d)) {
			deptId = employeeInfoMapper.selectByPrimaryKey(employeeId).getDeptId();
			redisService.hset(App.Redis.WECHAT_EMPLOYEEID_TO_DEPT, employeeId, deptId);
		} 
		else {
			deptId = Integer.parseInt(d);
		}
		return deptId;
	}

	/**
	 * 清除员工微信绑定关系、redis映射数据
	 * 
	 * @author 张进军
	 * @date Dec 8, 2015 1:33:36 PM
	 * @param employeeId
	 *            员工标识
	 */
	public void wipeCache(int employeeId) {
		String openId = redisService.hget(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId);
		if (StringUtils.isNotBlank(openId)) {
			redisService.hdel(App.Redis.WECHAT_OPENID_TO_STORE_KEY_HASH, openId);
			redisService.hdel(App.Redis.WECHAT_OPENID_TO_USERID_KEY_HASH, openId);
			redisService.hdel(App.Redis.WECHAT_OPENID_TO_BUSINESS_TYPE_KEY_HASH, openId);
			redisService.hdel(App.Redis.WECHAT_EMPLOYEEID_TO_OPENID_KEY_HASH, employeeId);
			wechatEmployeeMapper.deleteByPrimaryKey(openId);
		}
	}

	/**
	 * 根据店铺id查询员工
	 * 
	 * @param storeId
	 *            店铺id
	 * @return 该店铺下所有员工信息
	 */
	public List<EmployeeInfo> findEmployeeByStoreId(int storeId) {
//		return employeeInfoMapper.selectEmployeeListByStoreId(storeId);
		return employeeInfoMapper.selectEmployeeByStoreId(storeId);
	}

	/**
	 * 下载员工信息导入模板
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param storeId
	 *            该店铺id
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void downloadImportModelOfEmployeeInfo(HttpServletRequest request, HttpServletResponse response, int storeId) {
		// 该店铺下所有部门(状态没删除的)
		List<String> deptNames = deptInfoMapper.selectDeptNamesByStoreId(storeId);
		// 该店铺下所有岗位(状态没删除的)
		List<String> positionInfos = positioninfoMapper.selectPositionInfosByStoreId(storeId);
		// 该店铺下所有等级/职位(状态没删除的)
		List<String> employeeLevels = employeeLevelMapper.selectEmployeeLevelsByStoreId(storeId);
		POIFSFileSystem fs;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String filePath = request.getSession().getServletContext().getRealPath("/") + "/model/employee.xls";
		try {
			fs = new POIFSFileSystem(new FileInputStream(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			sheet = ExcelUtil.setHSSFValidation(sheet, deptNames.toArray(new String[deptNames.size()]), 0, 500, 5, 5);
			sheet = ExcelUtil.setHSSFValidation(sheet, positionInfos.toArray(new String[positionInfos.size()]), 0, 500,
					6, 6);
			sheet = ExcelUtil.setHSSFValidation(sheet, employeeLevels.toArray(new String[employeeLevels.size()]), 0,
					500, 7, 7);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(("employee.xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (bis != null) {
				try {
					bis.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 通过门店企业代号查询分店组织架构
	* @author 高国藩
	* @date 2016年6月23日 下午4:46:29
	* @param storeAccount   storeAccount
	* @param request        request
	* @return               ModelAndView
	 */
    public ModelAndView accountViewStorePosition(String storeAccount, HttpServletRequest request) {
        ModelAndView view = new ModelAndView(View.Position.VIEW_ACCOUNT);
        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreAccount(storeAccount);
        
        List<DeptInfo> deptInfos = deptInfoMapper.selectAllDetpByStoreId(storeInfos.get(0).getStoreId());
        List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(storeInfos.get(0).getStoreId());
        
        EmployeeLevel query = new EmployeeLevel();
        query.setStoreId(storeInfos.get(0).getStoreId());
        
        view.addObject("empLevels", JSONArray.fromObject(employeeLevelMapper.queryEmployeeLevel(query)));
        view.addObject("storeInfos", storeInfos);
        view.addObject("deptInfos", deptInfos);
        view.addObject("positionInfos", positionInfos);
        return view;
    }

    /***
     * 更换门店查询组织架构
    * @author 高国藩
    * @date 2016年6月24日 上午11:09:00
    * @param storeId   storeId
    * @return          BaseDto
     */
    public BaseDto accountViewStorePosition(Integer storeId) {
        List<DeptInfo> deptInfos = deptInfoMapper.selectAllDetpByStoreId(storeId);
        List<PositionInfo> positionInfos = positioninfoMapper.queryAllByStoreId(storeId);
        EmployeeLevel query = new EmployeeLevel();
        query.setStoreId(storeId);
        List<EmployeeLevel> employeeLevels = employeeLevelMapper.queryEmployeeLevel(query);
        Map<String, Object> results = new HashMap<>();
        results.put("deptInfos", deptInfos);
        results.put("positionInfos", positionInfos);
        results.put("empLevels", employeeLevels);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, results);
    }

}