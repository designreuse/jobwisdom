package com.lives.manage.common.util;

import java.util.List;

import com.lives.manage.web.dto.PrivilegeDto;

import net.sf.json.util.PropertyFilter;

public class JsonPropertyFilter implements PropertyFilter {

	@Override
	public boolean apply(Object source, String name, Object value) {
		if(source instanceof PrivilegeDto){
			if(name.equals("nodes")){
				if(value == null||((List)value).size()==0) return true;
			}
			if(name.equals("url"))return true;
			if(name.equals("refId"))return true;
			if(name.equals("type"))return true;
		}
		return false;
	}

}
