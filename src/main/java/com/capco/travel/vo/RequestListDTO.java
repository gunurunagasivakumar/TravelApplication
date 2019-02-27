package com.capco.travel.vo;

import java.util.List;

/**@author e5544344
 * This class is the RequestList DTO class 
 */

public class RequestListDTO {
	private List<RequestListVO> requestListVO;

	/**
	 * @return the requestListVO
	 */
	public List<RequestListVO> getRequestListVO() {
		return requestListVO;
	}

	/**
	 * @param requestListVO the requestListVO to set
	 */
	public void setRequestListVO(List<RequestListVO> requestListVO) {
		this.requestListVO = requestListVO;
	}
	

}
