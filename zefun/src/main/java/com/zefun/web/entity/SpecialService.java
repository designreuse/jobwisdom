package com.zefun.web.entity;

/**
 * 特色服务
* @author 高国藩
* @date 2016年5月19日 下午8:25:53
 */
public class SpecialService {
    /***/
    private Integer sId;
    /***/
    private String sName;
    /***/
    private Integer storeId;
    /***/
    private Integer projectId;
    /***/
    private String projectName;
    /***/
    private Integer employeeCode;
    /***/
    private String employeeName;
    /***/
    private String sImage;
    /***/
    private Integer isDeleted;
    /***/
    private String content;

    /**
     * 第三方
    * @author 高国藩
    * @date 2016年5月19日 下午8:26:30
    * @return sdf
     */
    public Integer getsId() {
        return sId;
    }
    /**
     * sdf
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:02
    * @param sId sd
     */
    public void setsId(Integer sId) {
        this.sId = sId;
    }
    /**
     * 第三方
    * @author 高国藩
    * @date 2016年5月19日 下午8:26:30
    * @return sdf
     */
    public String getsName() {
        return sName;
    }
    /**
     * dsf
    * @author 高国藩
    * @date 2016年5月19日 下午8:26:53
    * @param sName ds
     */
    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Integer getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Integer employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    /**
     * sdf
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:20
    * @return sd
     */
    public String getsImage() {
        return sImage;
    }

    /**
     * dsf
    * @author 高国藩
    * @date 2016年5月19日 下午8:27:12
    * @param sImage d
     */
    public void setsImage(String sImage) {
        this.sImage = sImage == null ? null : sImage.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}