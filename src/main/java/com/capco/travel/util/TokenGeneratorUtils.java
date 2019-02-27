package com.capco.travel.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TokenGeneratorUtils {
	
	public static final Map<String, Object> loginTokenTime = new HashMap<String, Object>();
	     
	    /**
	     * This method generates random string
	     * @return
	     */
	    public static String generateRandomString(String CAPCOUID){
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<9; i++){
	            int number = getRandomNumber(CAPCOUID);
	            char ch = CAPCOUID.charAt(number);
	            randStr.append(ch);
	        }
	        loginTokenTime.put("tokenId", randStr.toString());
	        System.out.println(randStr.toString());
	        return randStr.toString();
	    }
	    
	    private static int getRandomNumber(String CAPCOUID) {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CAPCOUID.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }

}
