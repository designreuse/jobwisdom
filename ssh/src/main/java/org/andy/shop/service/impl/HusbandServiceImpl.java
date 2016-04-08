package org.andy.shop.service.impl;

import java.util.List;

import org.andy.shop.dao.HusbandDao;
import org.andy.shop.entity.Husband;
import org.andy.shop.service.HusbandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

/**
 * 创建时间：2015-2-13 下午1:03:47
 * 
 * @author andy
 * @version 2.2 描述：
 */
@Service("husbandService")
public class HusbandServiceImpl implements HusbandService {

	@Autowired
	private HusbandDao husbandDao;

    public Husband load(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    public Husband get(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Husband> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public void persist(Husband entity) {
        // TODO Auto-generated method stub
        
    }

    public Integer save(Husband entity) {
        husbandDao.save(entity);
        return null;
    }

    public void saveOrUpdate(Husband entity) {
        // TODO Auto-generated method stub
        
    }

    public void delete(Integer id) {
        // TODO Auto-generated method stub
        
    }

    public void flush() {
        // TODO Auto-generated method stub
        
    }


}
