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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.ExcelUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.ExcelOfEmployeeShiftInfoDto;
import com.zefun.web.dto.ShiftDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.EmployeeShift;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShiftInfo;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeLevelMapper;
import com.zefun.web.mapper.EmployeeShiftMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ShiftMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 班次相关信息
* @author chendb
* @date 2015年8月28日 上午9:39:05
 */
@Service
public class ShiftService {
	
	/** 员工(一周)班次关联映射 */
	@Autowired
	private EmployeeShiftMapper employeeShiftMapper;

	/** 班次 */
	@Autowired
	private ShiftMapper shiftMapper;
	
	/** 人员 */
	@Autowired
	private EmployeeInfoMapper employeeInfoMapper;
	
	/** 部门 */
	@Autowired
	private DeptInfoMapper deptInfoMapper;

	/** 岗位信息映射 */
	@Autowired
	private PositioninfoMapper positioninfoMapper;

	/** 员工职位/等级映射 */
	@Autowired
	private EmployeeLevelMapper employeeLevelMapper;
    
    /**
     * 查询某个店铺的班次信息
     * 默认返回该门店最前面10条数据
    * @author chendb
    * @date 2015年8月28日 下午1:59:44
    * @param params 参数
    * @return ModelAndView
     */
    public ModelAndView queryShift(Map<String, Object> params){
        Integer storeId=Integer.parseInt(params.get("storeId").toString());
        params.put("deptId", deptInfoMapper.selectAllDetpByStoreId(storeId).get(0).getDeptId());
        Page<ShiftDto> page=selectPageForShift(params, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView("employee/shift/shift");
        mav.addObject("page", page);
        //获取部门信息
        List<DeptInfo> deptlist=deptInfoMapper.getDeptInfo(storeId);
        mav.addObject("deptlist", deptlist);
        //获取班次信息
       /* List<ShiftDto> list=shiftMapper.getShiftInfo(storeId);
        mav.addObject("list", list);*/
        //获取人员   选择推荐人要用的下拉框
        List<EmployeeInfo> employeeList=employeeInfoMapper.getRecommendlist(storeId);
        mav.addObject("employeeList", employeeList);

        return mav;
    }
    
    /**
     * 翻页功能
    * @author 陈端斌
    * @date 2015年8月28日 上午10:55:59
    * @param params 参数
    * @param pageNo 页码
    * @param pageSize 每页参数
    * @return BaseDto
     */
    public BaseDto listAction(Map<String, Object> params, int pageNo, int pageSize){
        Page<ShiftDto> page = selectPageForShift(params, pageNo, pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    /**
     * 
    * @author chendb
    * @date 2015年8月27日 上午11:23:47
    * @param params 参数
    * @param pageNo 页码
    * @param pageSize 每页数量
    * @return Page<AttendanceDto>
     */
    private Page<ShiftDto> selectPageForShift(Map<String, Object> params, int pageNo, int pageSize){
        Page<ShiftDto> page = new Page<ShiftDto>();
        page.setParams(params);
        page.setPageNo(pageNo);
//        page.setPageSize(pageSize);
        page.setPageSize(1000);  //全查询
        List<ShiftDto> list = shiftMapper.getEmployeeShift(page);
        page.setResults(list);
        return page;
    }
    /**
     * 人员班次
    * @author chendb
    * @date 2015年8月28日 下午4:24:23
    * @param params 参数
    * @param employeeIdJSONArr 员工idJSON数组
    * @param jsonObject json全部数据
    * @return int
     */
    public int employeeShift(Map<String, Object> params, JSONArray employeeIdJSONArr, JSONObject jsonObject){
    	int count = 0;
    	int[] employeeIdArr = new int[employeeIdJSONArr.size()];
    	for (int i=0; i<employeeIdJSONArr.size(); i++) {
    		employeeIdArr[i] = employeeIdJSONArr.getInt(i);
    	}
    	//jsonObject.getJSONObject("shifIda");
		count = shiftMapper.countIsshiftByArray(employeeIdArr);
		if (count > 0) {
			return 1;
		}
//    		List<EmployeeShift> esList = new ArrayList<EmployeeShift>();
		for (int i=0; i<employeeIdArr.length; i++) {
			EmployeeShift es = new EmployeeShift();
			es.setShifIda(Integer.parseInt((String) params.get("shifIda")));
			es.setShifIdb(Integer.parseInt((String) params.get("shifIdb")));
			es.setShifIdc(Integer.parseInt((String) params.get("shifIdc")));
			es.setShifIdd(Integer.parseInt((String) params.get("shifIdd")));
			es.setShifIde(Integer.parseInt((String) params.get("shifIde")));
			es.setShifIdf(Integer.parseInt((String) params.get("shifIdf")));
			es.setShifIdg(Integer.parseInt((String) params.get("shifIdg")));
			es.setLastOperatorId((Integer) params.get("userId"));
			es.setCreateTime((String) params.get("createTime"));
			es.setEmployeeId(employeeIdArr[i]);
			employeeShiftMapper.insertSelective(es);
//    			esList.add(es);
		}
//    		shiftMapper.insertShiftBatch(esList);
		//判断之前是否已经新增过了
        /*count = shiftMapper.countIsshift(params);
        if (count > 0){
            return 1;
        }
        //重新录入新的员工班次
        shiftMapper.employeeShift(params);*/
        return 0;
    }
    /**
     * 修改人员班次信息
    * @author chendb
    * @date 2015年8月31日 下午5:01:16
    * @param params 参数
    * @return int
     */
    public int updateemployeeShift(Map<String, Object> params){
        //删除之前的数据
        shiftMapper.deleteShift(params);
        //重新录入新的员工班次
        shiftMapper.employeeShift(params);
        return 0;
    }
    /**
     * 
    * @author chendb
    * @date 2015年8月31日 下午3:56:55
    * @param map 参数
    * @return ShiftDto
     */
    public ShiftDto queryEmployeeShift(Map<String, Object> map){
        ShiftDto info=shiftMapper.queryEmployeeShift(map);
        return info;
    }
    /**
     * 删除人员班次信息
    * @author chendb
    * @date 2015年8月31日 下午6:42:18
    * @param map 参数
    * @return int
     */
    public int deleteEmployeeShift(Map<String, Object> map){
        shiftMapper.deleteShift(map);
        return 0;
    }
    /**
     * 修改班次信息
    * @author chendb
    * @date 2015年9月10日 下午4:37:23
    * @param zao 参数
    * @param zhong 参数
    * @param wan 参数
    * @param quan 参数
    * @return int
     */
    @Transactional
    public int updateShift(ShiftInfo zao, ShiftInfo zhong, ShiftInfo wan, ShiftInfo quan){
        shiftMapper.updateShift(zao);
        shiftMapper.updateShift(zhong);
        shiftMapper.updateShift(wan);
        shiftMapper.updateShift(quan);
        return 0;
    }
    /**
     * 新增班次信息
    * @author chendb
    * @date 2015年9月11日 下午4:37:23
    * @param zao 参数
    * @param zhong 参数
    * @param wan 参数
    * @param quan 参数
    * @return int
     */
    @Transactional
    public int addShift(ShiftInfo zao, ShiftInfo zhong, ShiftInfo wan, ShiftInfo quan){
        shiftMapper.insertShiftinfo(zao);
        shiftMapper.insertShiftinfo(zhong);
        shiftMapper.insertShiftinfo(wan);
        shiftMapper.insertShiftinfo(quan);
        return 0;
    }
    
    /**
     * 根据部门标识获取班次信息
    * @author chendb
    * @date 2015年9月9日 下午5:08:48
    * @param deptId 部门标识
    * @return List<DeptInfo>
     */
    public List<ShiftInfo> getShiftinfo(Integer deptId){
        List<ShiftInfo> list=shiftMapper.getShiftInfo(deptId);
        return list;
    } 
    
	/**
	 * 导入员工排班功能
	 * 
	 * @author chendb
	 * @date 2015年10月20日 上午10:52:31
	 * @param request
	 *            请求
	 * @param file
	 *            file
	 * @param temp
	 *            temp
	 * @param storeId
	 *            storeId
	 * @return BaseDto
	 */
	@Transactional
	public BaseDto importExcle(HttpServletRequest request, MultipartFile file, String temp, Integer storeId) {
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
			// 获得第一个表单的迭代器
			Iterator<Row> rows = sheet.rowIterator();
			List<ShiftDto> listDto = new ArrayList<ShiftDto>();
			while (rows.hasNext()) {
				ShiftDto shiftDto = new ShiftDto();
				ShiftDto copyshiftDto = new ShiftDto();
				// objectiveDto.setStoreId(storeId);
				// 获得行数据
				Row row = rows.next();
				if (row.getRowNum() > 0) {
					// 获得行号从0开始
					// 获得第一行的迭代器
					Iterator<Cell> cells = row.cellIterator();
					Integer employeeId = 0;
					Integer deptId = 0;
					while (cells.hasNext()) {
						Cell cell = cells.next();
						if (cell.getColumnIndex() == 0) {
							String returnValue = changeCellToString(cell);
							if (StringUtils.isNotBlank(returnValue)) {
								copyshiftDto.setEmployeeId("1");
							}
						} 
						//周一班次
						else if (cell.getColumnIndex() == 5) {
							String returnValue = changeCellToString(cell);
							if (StringUtils.isNotBlank(returnValue)) {
								copyshiftDto.setShifNamea(returnValue);
							}
						} 
						//周二班次
						else if (cell.getColumnIndex() == 6) {
							String returnValue = changeCellToString(cell);
							if (StringUtils.isNotBlank(returnValue)) {
								copyshiftDto.setShifNameb(returnValue);
							}
						} 
						//周三班次
						else if (cell.getColumnIndex() == 7) {
							String returnValue = changeCellToString(cell);
							if (StringUtils.isNotBlank(returnValue)) {
								copyshiftDto.setShifNamec(returnValue);
							}
						} 
						//周四班次
						else if (cell.getColumnIndex() == 8) {
							String returnValue = changeCellToString(cell);
							if (StringUtils.isNotBlank(returnValue)) {
								copyshiftDto.setShifNamed(returnValue);
							}
						} 
						//周五班次
						else if (cell.getColumnIndex() == 9) {
							String returnValue = changeCellToString(cell);
							if (StringUtils.isNotBlank(returnValue)) {
								copyshiftDto.setShifNamee(returnValue);
							}
						}
						//周六班次
						else if (cell.getColumnIndex() == 10) {
							String returnValue = changeCellToString(cell);
							if (StringUtils.isNotBlank(returnValue)) {
								copyshiftDto.setShifNamee(returnValue);
							}
						}
						//周日班次
						else if (cell.getColumnIndex() == 11) {
							String returnValue = changeCellToString(cell);
							if (StringUtils.isNotBlank(returnValue)) {
								copyshiftDto.setShifNamee(returnValue);
							}
						}
					}
					//防止一行有格式的无效数据
					if (copyshiftDto.getEmployeeId() == null && copyshiftDto.getShifNamea() == null
							  && copyshiftDto.getShifNameb() == null && copyshiftDto.getShifNamec() == null
							  && copyshiftDto.getShifNamed() == null && copyshiftDto.getShifNamee() == null
							  && copyshiftDto.getShifNamef() == null) {
						break;
					}
					Iterator<Cell> cellss = row.cellIterator();
					while (cellss.hasNext()) {
						Cell cell = cellss.next();
						if (cell.getColumnIndex() == 0) {
							String returnValue = changeCellToString(cell);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("storeId", storeId);
							map.put("employeeCode", returnValue);
							EmployeeDto employeeInfo = employeeInfoMapper.getEmployeeDetail(map);
							if (employeeInfo == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行该门店下没有此人员！");
							}
							employeeId = employeeInfo.getEmployeeId();
							map.put("employeeId", employeeId);
							deptId = employeeInfo.getDeptId();
							/*int count = shiftMapper.countIsshift(map);
							if (count > 0) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行该人员已经存在班次了不能重复添加！");
							}*/
							shiftDto.setEmployeeId(employeeId.toString());
						} 
						else if (cell.getColumnIndex() == 5) {
							String returnValue = changeCellToString(cell);
							if (returnValue == "" || returnValue == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行周一班次不能为空！");
							}
							if (!returnValue.equals("休息日") && !returnValue.equals("早班") && !returnValue.equals("中班")
									  && !returnValue.equals("晚班") && !returnValue.equals("全班")) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
							}
							Integer a = 0;
							if (!returnValue.equals("休息日")) {
								a = getshiftId(deptId, returnValue);
								if (a == 0) {
									return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行没设置" + returnValue + "班次");
								}
							}
							shiftDto.setShifIda(a.toString());
						} 
						else if (cell.getColumnIndex() == 6) {
							String returnValue = changeCellToString(cell);
							if (returnValue == "" || returnValue == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行周二班次不能为空！");
							}
							if (!returnValue.equals("休息日") && !returnValue.equals("早班") && !returnValue.equals("中班")
									  && !returnValue.equals("晚班") && !returnValue.equals("全班")) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
							}
							Integer a = 0;
							if (!returnValue.equals("休息日")) {
								a = getshiftId(deptId, returnValue);
								if (a == 0) {
									return new BaseDto(-1, "请先设置" + returnValue + "班次时间");
								}
							}
							shiftDto.setShifIdb(a.toString());
						} 
						else if (cell.getColumnIndex() == 7) {
							String returnValue = changeCellToString(cell);
							if (returnValue == "" || returnValue == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行周三班次不能为空！");
							}
							if (!returnValue.equals("休息日") && !returnValue.equals("早班") && !returnValue.equals("中班")
									  && !returnValue.equals("晚班") && !returnValue.equals("全班")) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
							}
							Integer a = 0;
							if (!returnValue.equals("休息日")) {
								a = getshiftId(deptId, returnValue);
								if (a == 0) {
									return new BaseDto(-1, "请先设置" + returnValue + "班次时间");
								}
							}
							shiftDto.setShifIdc(a.toString());
						} 
						else if (cell.getColumnIndex() == 8) {
							String returnValue = changeCellToString(cell);
							if (returnValue == "" || returnValue == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行周四班次不能为空！");
							}
							if (!returnValue.equals("休息日") && !returnValue.equals("早班") && !returnValue.equals("中班")
									  && !returnValue.equals("晚班") && !returnValue.equals("全班")) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
							}
							Integer a = 0;
							if (!returnValue.equals("休息日")) {
								a = getshiftId(deptId, returnValue);
								if (a == 0) {
									return new BaseDto(-1, "请先设置" + returnValue + "班次时间");
								}
							}
							shiftDto.setShifIdd(a.toString());
						} 
						else if (cell.getColumnIndex() == 9) {
							String returnValue = changeCellToString(cell);
							if (returnValue == "" || returnValue == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行周五班次不能为空！");
							}
							if (!returnValue.equals("休息日") && !returnValue.equals("早班") && !returnValue.equals("中班")
									  && !returnValue.equals("晚班") && !returnValue.equals("全班")) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
							}
							Integer a = 0;
							if (!returnValue.equals("休息日")) {
								a = getshiftId(deptId, returnValue);
								if (a == 0) {
									return new BaseDto(-1, "请先设置" + returnValue + "班次时间");
								}
							}
							shiftDto.setShifIde(a.toString());
						} 
						else if (cell.getColumnIndex() == 10) {
							String returnValue = changeCellToString(cell);
							if (returnValue == "" || returnValue == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行周六班次不能为空！");
							}
							if (!returnValue.equals("休息日") && !returnValue.equals("早班") && !returnValue.equals("中班")
									  && !returnValue.equals("晚班") && !returnValue.equals("全班")) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
							}
							Integer a = 0;
							if (!returnValue.equals("休息日")) {
								a = getshiftId(deptId, returnValue);
								if (a == 0) {
									return new BaseDto(-1, "请先设置" + returnValue + "班次时间");
								}
							}
							shiftDto.setShifIdf(a.toString());
						} 
						else if (cell.getColumnIndex() == 11) {
							String returnValue = changeCellToString(cell);
							if (returnValue == "" || returnValue == null) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行周日班次不能为空！");
							}
							if (!returnValue.equals("休息日") && !returnValue.equals("早班") && !returnValue.equals("中班")
									  && !returnValue.equals("晚班") && !returnValue.equals("全班")) {
								return new BaseDto(-1, "第 " + (row.getRowNum() + 1) + "行班次只能填写 休息日、早班、中班、晚班、或者休息日这几种！");
							}
							Integer a = 0;
							if (!returnValue.equals("休息日")) {
								a = getshiftId(deptId, returnValue);
								if (a == 0) {
									return new BaseDto(-1, "请先设置" + returnValue + "班次时间");
								}
							}
							shiftDto.setShifIdg(a.toString());
						}

					}
					listDto.add(shiftDto);
				}
			}
			for (int i = 0; i < listDto.size(); i++) {
				ShiftDto info = listDto.get(i);
				/**
				 * 先根据employee_id判断该员工是否已经排了班次
				 * if(排了班次)就对employee_shift表对该记录修改
				 * if(没排班次)就对employee_shift表新增记录
				 */
				EmployeeShift es = employeeShiftMapper.selectEmployeeShiftByEmployeeId(Integer.parseInt(info.getEmployeeId()));
				if (es != null) {
					es.setShifIda(Integer.parseInt(info.getShifIda()));
					es.setShifIdb(Integer.parseInt(info.getShifIdb()));
					es.setShifIdc(Integer.parseInt(info.getShifIdc()));
					es.setShifIdd(Integer.parseInt(info.getShifIdd()));
					es.setShifIde(Integer.parseInt(info.getShifIde()));
					es.setShifIdf(Integer.parseInt(info.getShifIdf()));
					es.setShifIdg(Integer.parseInt(info.getShifIdg()));
					es.setUpdateTime(DateUtil.getCurTime());
					es.setLastOperatorId((Integer) request.getSession().getAttribute(App.Session.USER_ID));
					employeeShiftMapper.updateByPrimaryKeySelective(es);
				} 
				else {
					shiftMapper.insertInfo(info);
				}
			}
			return new BaseDto(0, "导入成功！");
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
    /**
     * 
    * @author chendb
    * @date 2015年10月12日 下午2:06:21
    * @param cell exlce数据对象
    * @return String
     */
    public String changeCellToString(Cell cell){

        String returnValue = "";

        if (null != cell){

            switch(cell.getCellType()){
                //数字
                case HSSFCell.CELL_TYPE_NUMERIC:   
        
                    Double doubleValue = cell.getNumericCellValue();
            
                    String str = doubleValue.toString();
            
                    if (str.contains(".0")){
            
                        str = str.replace(".0", "");
            
                    }
            
                    Integer intValue = Integer.parseInt(str);
            
                    returnValue = intValue.toString();
            
                    break;
        
                case HSSFCell.CELL_TYPE_STRING:    
                    //字符串
                    returnValue = cell.getStringCellValue();
            
                    break;
        
                case HSSFCell.CELL_TYPE_BOOLEAN:   
                    //布尔
                    Boolean booleanValue=cell.getBooleanCellValue();
            
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
     * 
    * @author chendb
    * @date 2015年10月21日 上午9:25:02
    * @param deptId 部门标识
    * @param shiftName 班次名称
    * @return int
     */
    private int getshiftId(Integer deptId, String shiftName){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("deptId", deptId);
        map.put("shifName", shiftName);
        ShiftInfo info=shiftMapper.queryshiftinfo(map);
        if (info==null){
            return 0;
        }
        return info.getShifId();
    }
    
    /**
     * 下载员工班次信息导入模板
     * @param request  请求
     * @param response  响应
     * @param storeId  店铺id
     */
    public void downloadImportModelOfShiftInfo(HttpServletRequest request, HttpServletResponse response, int storeId) {
    	//员工班次excel模板信息
    	List<ExcelOfEmployeeShiftInfoDto> shiftInfos = shiftMapper.selectExcelOfEmployeeShiftInfo(storeId);
    	//该店铺下所有部门(状态没删除的)
		List<String> deptNames = deptInfoMapper.selectDeptNamesByStoreId(storeId);
		//该店铺下所有岗位(状态没删除的)
		List<String> positionInfos = positioninfoMapper.selectPositionInfosByStoreId(storeId);
		//该店铺下所有等级/职位(状态没删除的)
		List<String> employeeLevels = employeeLevelMapper.selectEmployeeLevelsByStoreId(storeId);
    	POIFSFileSystem fs;
		BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String filePath =  request.getSession().getServletContext().getRealPath("/") + "/model/shift.xls";
        try {
			fs = new POIFSFileSystem(new FileInputStream(filePath));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			//填充
			for (int i=0; i<shiftInfos.size(); i++) {
				ExcelUtil.setHSSFCell(sheet, i + 1, 0, shiftInfos.get(i).getEmployeeCode());
				ExcelUtil.setHSSFCell(sheet, i + 1, 1, shiftInfos.get(i).getName());
				ExcelUtil.setHSSFCell(sheet, i + 1, 2, shiftInfos.get(i).getDeptName());
				ExcelUtil.setHSSFCell(sheet, i + 1, 3, shiftInfos.get(i).getPositionName());
				ExcelUtil.setHSSFCell(sheet, i + 1, 4, shiftInfos.get(i).getLevelName());
				ExcelUtil.setHSSFCell(sheet, i + 1, 5, shiftInfos.get(i).getShifNamea());
				ExcelUtil.setHSSFCell(sheet, i + 1, 6, shiftInfos.get(i).getShifNameb());
				ExcelUtil.setHSSFCell(sheet, i + 1, 7, shiftInfos.get(i).getShifNamec());
				ExcelUtil.setHSSFCell(sheet, i + 1, 8, shiftInfos.get(i).getShifNamed());
				ExcelUtil.setHSSFCell(sheet, i + 1, 9, shiftInfos.get(i).getShifNamee());
				ExcelUtil.setHSSFCell(sheet, i + 1, 10, shiftInfos.get(i).getShifNamef());
				ExcelUtil.setHSSFCell(sheet, i + 1, 11, shiftInfos.get(i).getShifNameg());
			}
			//样式
			ExcelUtil.setHSSFValidation(sheet, deptNames.toArray(new String[deptNames.size()]), 0, 500, 2, 2);
			ExcelUtil.setHSSFValidation(sheet, positionInfos.toArray(new String[positionInfos.size()]), 0, 500, 3, 3);
			ExcelUtil.setHSSFValidation(sheet, employeeLevels.toArray(new String[employeeLevels.size()]), 0, 500, 4, 4);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String(("shift.xls").getBytes(), "iso-8859-1"));
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
            if (bis != null){
                try {
                    bis.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null){
                try {
                    bos.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
