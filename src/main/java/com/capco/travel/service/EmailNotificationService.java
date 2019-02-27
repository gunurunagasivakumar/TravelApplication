
package com.capco.travel.service;

import org.springframework.stereotype.Service;


import com.capco.travel.custom.exception.TravelException.TravelServiceException;

/**
 * @author e5542274
 *
 */
@Service
public interface EmailNotificationService {
	public void sendEmailNotification(int requestId, String changesDone) throws TravelServiceException;
}

