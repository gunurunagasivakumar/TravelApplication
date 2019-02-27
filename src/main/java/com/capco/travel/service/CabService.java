
package com.capco.travel.service;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.CabDetailsVO;

/**
 * @author e5542274
 *
 */

public interface CabService {

	public void insertCabDetails(CabDetailsVO cabDetails)throws TravelServiceException;

	public CabDetailsVO getCabDetails(int requestId)throws TravelServiceException;

	public void updateCabDetails(CabDetailsVO cabDetails)throws TravelServiceException;

	public void deleteCabDetails(int requestId)throws TravelServiceException;

	public List<CabDetailsVO> getAllCabDetails()throws TravelServiceException;
}
