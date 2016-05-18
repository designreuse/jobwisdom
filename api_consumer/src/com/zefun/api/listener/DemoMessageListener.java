package com.zefun.api.listener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.zefun.api.service.CashierOrderService;

import net.sf.json.JSONObject;

public class DemoMessageListener implements MessageListener {

	@Autowired
	private CashierOrderService cashierOrderService;
	
    @Override
	public Action consume(Message message, ConsumeContext context) {

        System.out.println("Receive: " + message.getMsgID());
        try {
        	if (message.getTag().equals("queue_cashier_order_comission")) {
        		Object obj = toObject(message.getBody());
        		JSONObject json = (JSONObject) obj;
        		Integer orderId = json.getInt("orderId");
        		cashierOrderService.insertCashierCommission(orderId);
        	}
        	
            return Action.CommitMessage;
        }catch (Exception e) {
            //消费失败
            return Action.ReconsumeLater;
        }
    }
    
    /**  
     * 数组转对象  
     * @param bytes  数组
     * @return  Object
     */  
    public Object toObject (byte[] bytes) {      
        Object obj = null;      
        try {        
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);        
            ObjectInputStream ois = new ObjectInputStream (bis);        
            obj = ois.readObject();      
            ois.close();   
            bis.close();   
        } catch (IOException ex) {        
            ex.printStackTrace();   
        } catch (ClassNotFoundException ex) {        
            ex.printStackTrace();   
        }      
        return obj;    
    }   
}
