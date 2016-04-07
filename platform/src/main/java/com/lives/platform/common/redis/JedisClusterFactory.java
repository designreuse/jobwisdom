package com.lives.platform.common.redis;

import java.util.HashSet;  
import java.util.List;
import java.util.Properties;  
import java.util.Set;  
import java.util.regex.Pattern;  
  

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;  
import org.springframework.beans.factory.FactoryBean;  
import org.springframework.beans.factory.InitializingBean;  
import org.springframework.core.io.Resource;  
  

import redis.clients.jedis.HostAndPort;  
import redis.clients.jedis.JedisCluster;  
  
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {  
  
    private Resource addressConfig;  
    private String addressKeyPrefix ;  
  
    private JedisCluster jedisCluster;  
    private Integer timeout;  
    private Integer maxRedirections;  
    private GenericObjectPoolConfig genericObjectPoolConfig;  
    private List<String> addressList;
      
    private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");  
  
    @Override  
    public JedisCluster getObject() throws Exception {  
        return jedisCluster;  
    }  
  
    @Override  
    public Class<? extends JedisCluster> getObjectType() {  
        return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);  
    }  
  
    @Override  
    public boolean isSingleton() {  
        return true;  
    }  
  
  
  
    private Set<HostAndPort> parseHostAndPort() throws Exception {  
        try {  
            Set<HostAndPort> haps = new HashSet<HostAndPort>();  
            for (String val : addressList ) {  
                String[] ipAndPort = val.split(":");  
  
                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));  
                haps.add(hap);  
            }  
  
            return haps;  
        } catch (IllegalArgumentException ex) {  
            throw ex;  
        } catch (Exception ex) {  
            throw new Exception("解析 jedis 配置文件失败", ex);  
        }  
    }  
      
    @Override  
    public void afterPropertiesSet() throws Exception {  
        Set<HostAndPort> haps = this.parseHostAndPort();  
          
        jedisCluster = new JedisCluster(haps, timeout, maxRedirections,genericObjectPoolConfig);  
    }  
    public void setAddressConfig(Resource addressConfig) {  
        this.addressConfig = addressConfig;  
    }  
  
    public void setTimeout(int timeout) {  
        this.timeout = timeout;  
    }  
  
    public void setMaxRedirections(int maxRedirections) {  
        this.maxRedirections = maxRedirections;  
    }  
  
    public void setAddressKeyPrefix(String addressKeyPrefix) {  
        this.addressKeyPrefix = addressKeyPrefix;  
    }  
  
    public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {  
        this.genericObjectPoolConfig = genericObjectPoolConfig;  
    }

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}  
}  
