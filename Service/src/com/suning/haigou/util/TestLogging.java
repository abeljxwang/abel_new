package com.suning.haigou.util;

import org.abel.service.base.common.EngineLogger;

public class TestLogging {
	
	 public static void main(String[] inputs){
		
		EngineLogger logger = EngineLogger.getLogger(TestLogging.class);
		
		logger.error("Test message");
	}

}
