package com.zefun.app.common.param;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 
 * ClassName: SigeCheckParam    
 * date: 2016年3月23日 上午11:42:58  
 * @author michael  
 * @version   
 * @since JDK 1.8
 */
public class SigeCheckParam extends BaseParam{
	/** 员工当前位置经度 */
	@ApiModelProperty(value="员工当前位置经度", required=true)
    private Double longitude;

    /** 角色标识 */
	@ApiModelProperty(value="员工当前位置纬度", required=true)
    private Double latitude;

	/**  
	 * longitude.  
	 * @return  the longitude  
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**  
	 * longitude.  
	 * @param   longitude    the longitude to set   
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**  
	 * latitude.  
	 * @return  the latitude  
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**  
	 * latitude.  
	 * @param   latitude    the latitude to set   
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	} 
}
  
