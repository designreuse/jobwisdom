package org.andy.shop.service.impl;

import java.util.List;

import javax.management.Query;

import org.andy.shop.dao.UserInfoDao;
import org.andy.shop.entity.UserInfo;
import org.andy.shop.service.UserInfoService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建时间：2015-2-13 下午1:03:47
 * 
 * @author andy
 * @version 2.2 描述：
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
    private SessionFactory sessionFactory;

	public UserInfo load(Integer id) {
		return null;
	}

	/**
	 * 在此方法上进行测试缓存
	 */
	public UserInfo get(Integer id) {
//	    Session s1 = sessionFactory.openSession();
//	    Session s2 = sessionFactory.openSession();
//	    s1.beginTransaction();  
//	    UserInfo u1 = (UserInfo) s1.load(UserInfo.class, id);
//	    UserInfo u2 = (UserInfo) s1.load(UserInfo.class, id);
//	    s1.getTransaction().commit();
//	    s1.close();
//	    s2.getTransaction();
//	    UserInfo u3 = (UserInfo) s2.load(UserInfo.class, id);
//	    System.out.println(u1 == u2);
//	    System.out.println(u1 == u3);
//	    userInfoDao.get(id);
	    Session s1 = sessionFactory.openSession();
	    org.hibernate.Query query = s1.createQuery("from UserInfo where id = 0");
	    query.setCacheable(true);
	    List<UserInfo> ls = query.list();
	    System.out.println(ls);
	    List<UserInfo> lx = query.list();
	    System.out.println(lx);
		return userInfoDao.get(id);
	}

	public List<UserInfo> findAll() {
		return userInfoDao.findAll();
	}

	public void persist(UserInfo entity) {
		userInfoDao.persist(entity);
	}

	public Integer save(UserInfo entity) {
		return userInfoDao.save(entity);
	}

	public void saveOrUpdate(UserInfo entity) {
		userInfoDao.saveOrUpdate(entity);
	}

	public void delete(Integer id) {
		userInfoDao.delete(id);
	}

	public void flush() {
		userInfoDao.flush();
	}

}
