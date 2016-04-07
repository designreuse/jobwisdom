package com.lives.platform.common.log;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class AccessLogFilter extends Filter{

	@Override
	public int decide(LoggingEvent event) {
		int inputLevel = event.getLevel().toInt();
		if (inputLevel == AccessLog.LOG_LEVEL) {
			return 0;
		}
		return -1;
	}

}
