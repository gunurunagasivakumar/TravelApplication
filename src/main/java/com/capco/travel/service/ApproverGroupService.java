package com.capco.travel.service;

import java.util.List;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.ApproverGroupVO;

public interface ApproverGroupService {
	
	public List<ApproverGroupVO> getAllApprovers(int level)  throws TravelServiceException;
	
}
