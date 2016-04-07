package com.lives.platform.web.dto;

import java.util.List;

import com.lives.platform.web.entity.ProjectInfo;

public class ProjectClassDto {

	private String classesName;
    
    private List<ProjectInfo> lproject;

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public List<ProjectInfo> getLproject() {
		return lproject;
	}

	public void setLproject(List<ProjectInfo> lproject) {
		this.lproject = lproject;
	}
    
}
