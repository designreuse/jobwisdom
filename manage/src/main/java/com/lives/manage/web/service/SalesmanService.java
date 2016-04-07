package com.lives.manage.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lives.manage.web.entity.User;
import com.lives.manage.web.mapper.UserMapper;
/**
 * 业务员管理
* @author chendb
* @date 2015年10月30日 下午4:14:46
 */
@Service
public class SalesmanService {
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 获取渠道商底下的员工业务员
    * @author chendb
    * @date 2015年10月30日 下午6:14:58
    * @param map 参数
    * @return  List<User>querychannelUser
     */
    public List<User>querychannelUser(Map<String ,Object> map){
        List<User> querychannelUser=userMapper.querychannelUser(map);
        return querychannelUser;
    }
    /**
     * 统计
    * @author chendb
    * @date 2015年10月30日 下午6:33:59
    * @param map 参数
    * @return int
     */
    public int countchannelUser(Map<String ,Object> map){
            int count =userMapper.countchannelUser(map);
            return count;
        }
    
    

}
