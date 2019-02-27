package com.capco.travel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.ProjectDetailsVO;

@Service
public interface ProjectDetailsService {
	
	public  List<ProjectDetailsVO> getAllProjectDetails() throws TravelServiceException;
	
	public ProjectDetailsVO getProjectDetails(String projectId) throws TravelServiceException;
}
