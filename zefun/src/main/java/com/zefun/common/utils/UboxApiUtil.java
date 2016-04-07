package com.zefun.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zefun.common.consts.App;
import com.zefun.web.entity.UboxGoodsInfo;
import com.zefun.web.entity.UboxMachineInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 友宝API工具类
* @author 张进军
* @date Jan 28, 2016 5:59:18 PM
 */
public class UboxApiUtil {

    /**日志对象*/
    private static Logger logger = Logger.getLogger(UboxSignUtil.class);

    
    /**
     * 根据售货机编码、商品标识查询商品库存
    * @author 张进军
    * @date Mar 9, 2016 5:21:30 PM
    * @param vmid       售货机编码
    * @param goodsId    商品标识
    * @return   商品库存
     */
    public static int getGoodsStockByVmidAndGoodsId(String vmid, String goodsId) {
        Map<String, String> params = new HashMap<>();
        params.put("vmid", vmid);
        params.put("productid", goodsId);
        JSONObject res = sendUboxRequest(App.Ubox.API_REQUEST_URL + "opentrade/productInfo", params);
        if (res != null) {
            return res.getJSONObject("product").getInt("num");
        }
        return 0;
    }
    
    /**
     * 根据售货机标识查询售货机信息
    * @author 张进军
    * @date Mar 4, 2016 10:33:15 PM
    * @param vmid   售货机标识
    * @return   售货机信息
     */
    public static UboxMachineInfo getMachineInfoByVmid(String vmid) {
        Map<String, String> params = new HashMap<>();
        params.put("vmid", vmid);
        JSONObject res = sendUboxRequest(App.Ubox.API_REQUEST_URL + "opentrade/searchVmList", params);
        if (res == null) {
            return null;
        }
        JSONObject vm = res.getJSONArray("node_list").getJSONObject(0);
        UboxMachineInfo machineInfo = (UboxMachineInfo) JSONObject.toBean(vm, UboxMachineInfo.class);
        machineInfo.setIsShop(vm.getBoolean("is_shop") ? 1 :0);
        machineInfo.setHasBox(vm.getBoolean("has_box") ? 1 :0);
        machineInfo.setHasMeal(vm.getBoolean("has_meal") ? 1 :0);
        machineInfo.setVmConnected(vm.getBoolean("vm_connected") ? 1 :0);
        return machineInfo;
    }
    
    
    /**
     * 根据售货机编码获取商品列表
    * @author 张进军
    * @date Jan 28, 2016 6:01:58 PM
    * @param vmid   售货机编码
    * @return   商品列表
     */
    public static List<UboxGoodsInfo> getGoodsListByVmid(String vmid) {
        Map<String, String> params = new HashMap<>();
        params.put("vmid", vmid);
        JSONObject res = sendUboxRequest(App.Ubox.API_REQUEST_URL + "opentrade/productList", params);
        
        List<UboxGoodsInfo> goodsList = null;
        if (res != null) {
            JSONArray jsonArray = res.getJSONArray("products");
            if (jsonArray.size() > 0) {
                goodsList = new ArrayList<>(jsonArray.size());
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    UboxGoodsInfo goodsInfo = new UboxGoodsInfo();
                    goodsInfo.setUboxId(json.getString("id"));
                    goodsInfo.setUboxName(json.getString("name"));
                    goodsInfo.setUboxOriginalPrice(json.getInt("oldPrice"));
                    goodsInfo.setUboxSalePrice(json.getInt("price"));
                    goodsInfo.setUboxDesc(json.getString("desc"));
                    goodsInfo.setUboxExpireTime(json.getInt("expire_time"));
                    goodsInfo.setUboxStock(json.getInt("num"));
                    goodsInfo.setUboxPicture(json.getString("pic"));
                    goodsInfo.setGoodsName(json.getString("name"));
                    
                    JSONObject descJson = new JSONObject();
                    json.put("type", "1");
                    json.put("text", goodsInfo.getUboxDesc());
                    JSONArray descJsonArray = new JSONArray();
                    descJsonArray.add(descJson);
                    goodsInfo.setGoodsDesc(descJsonArray.toString());
                    
                    goodsList.add(goodsInfo);
                }
            }
        }
        return goodsList;
    }
    
    
    /**
     * 根据商品和售货机买码接口
    * @author 张进军
    * @date Jan 28, 2016 7:02:45 PM
    * @param vmid       售货机标识
    * @param goodsId    商品标识
    * @param userId     用户标识
    * @param tranId     智放订单标识
    * @return   JSON对象：<br>
    *           delivery_code   string  取货码<br>
    *           vmid    string  商品所在售货机id<br>
    *           expire_time int 取货码过期时间<br>
    *           box_number  string  商品所在盒子编号，如果是存放在售货机内的商品，此处返回空字符串。<br>
    *           tran_id string  友宝交易id，供取货、查询取货状态使用<br>
     */
    public static JSONObject payment(String vmid, String goodsId, int userId, String tranId) {
        Map<String, String> params = new HashMap<>();
        params.put("vmid", vmid);
        params.put("product_id", goodsId);
        params.put("app_uid", String.valueOf(userId));
        params.put("app_tran_id", tranId);
        return sendUboxRequest(App.Ubox.API_REQUEST_URL + "opentrade/orderCode", params);
    }
    
    
    /**
     * 出货操作
    * @author 张进军
    * @date Jan 28, 2016 7:11:56 PM
    * @param tranId 友宝交易ID
    * @return   出货状态码：200、出货成功，500、出货失败，202、出货确认中
     */
    public static int deliverTrade(String tranId){
        Map<String, String> params = new HashMap<>();
        params.put("tran_id", tranId);
        JSONObject json = sendUboxRequest(App.Ubox.API_REQUEST_URL + "opentrade/deliverTrade", params);
        if (json == null) {
            return App.Ubox.API_DELIVER_CODE_FAIL;
        }
        return json.getInt("code");
    }
    
    
    /**
     * 发送API请求
    * @author 张进军
    * @date Jan 28, 2016 6:09:48 PM
    * @param url        接口地址
    * @param params     接口参数
    * @return   成功返回JSON对象结果体，失败返回null
     */
    private static JSONObject sendUboxRequest(String url, Map<String, String> params){
        params.put("app_id", App.Ubox.APP_ID);
        UboxSignUtil.sign(params);
        String res = HttpClientUtil.sendPostReq(url, params, "utf-8");
        try {
            JSONObject resJson = JSONObject.fromObject(res);
            if (resJson.getJSONObject("head").getInt("return_code") == App.Ubox.API_REQUEST_SUCCESS_CODE) {
                return resJson.getJSONObject("body");
            }
            logger.error("sendUboxRequest error : " + res);
        }
        catch (Exception e) {
            logger.error("sendUboxRequest error : ", e);
        }
        return null;
    }
}
