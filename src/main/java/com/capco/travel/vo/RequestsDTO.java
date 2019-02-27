package com.capco.travel.vo;

import java.util.List;


public class RequestsDTO {
	
	private List<MainRequestVO> mainRequestVOList;
	
	private RequestCountVO requestCountVO;

	/**
	 * @return the mainRequestVOList
	 */
	public List<MainRequestVO> getMainRequestVOList() {
		return mainRequestVOList;
	}

	/**
	 * @param mainRequestVOList the mainRequestVOList to set
	 */
	public void setMainRequestVOList(List<MainRequestVO> mainRequestVOList) {
		this.mainRequestVOList = mainRequestVOList;
	}

	/**
	 * @return the requestCountVO
	 */
	public RequestCountVO getRequestCountVO() {
		return requestCountVO;
	}

	/**
	 * @param requestCountVO the requestCountVO to set
	 */
	public void setRequestCountVO(RequestCountVO requestCountVO) {
		this.requestCountVO = requestCountVO;
	}
	
}
