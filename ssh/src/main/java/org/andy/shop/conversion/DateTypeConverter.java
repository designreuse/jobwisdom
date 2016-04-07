package org.andy.shop.conversion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.andy.shop.entity.UserInfo;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 类型转换器
 * 1.局部,在action或者entity下建立className-conversion.properites
 * 2.全局,在src下建立xwork-conversion.properites
* @author 高国藩
* @date 2015年11月5日 下午7:09:34
 */
public class DateTypeConverter extends StrutsTypeConverter{

    @Override
    public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
        System.out.println("conversion....");
        if (arg2 == UserInfo.class){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.parse(arg1[3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return arg1;
    }

    @Override
    public String convertToString(Map arg0, Object arg1) {
        UserInfo userInfo = (UserInfo) arg1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(userInfo.getDate());
    }

}
