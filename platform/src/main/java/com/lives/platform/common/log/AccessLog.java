package com.lives.platform.common.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

public class AccessLog {
	public static final int LOG_LEVEL = 20050;

    private static class CustomerLogLevel extends Level{  
		private static final long serialVersionUID = 1L;

		public CustomerLogLevel(int level, String levelStr, int syslogEquivalent) {  
            super(level, levelStr, syslogEquivalent);  
        }         
    }  
      
    private static final Level CustomerLevel = new CustomerLogLevel(LOG_LEVEL, "ACCESS ", SyslogAppender.LOG_LOCAL0);  
      
    public static void log(Logger logger,
    		Object info){  
        logger.log(CustomerLevel, info);  
    } 
}
