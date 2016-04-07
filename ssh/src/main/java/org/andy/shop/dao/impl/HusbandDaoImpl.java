package org.andy.shop.dao.impl;

import java.util.List;

import org.andy.shop.dao.HusbandDao;
import org.andy.shop.entity.Husband;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("husbandDao")
public class HusbandDaoImpl implements HusbandDao{
    
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

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
        return (Integer) this.getCurrentSession().save(entity);
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
