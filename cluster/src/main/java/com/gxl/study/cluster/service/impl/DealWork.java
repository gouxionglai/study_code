package com.gxl.study.cluster.service.impl;


import com.gxl.study.cluster.service.SRLockDealCallback;

import java.util.concurrent.TimeUnit;

public class DealWork implements SRLockDealCallback<String> {

	@Override
	public String deal() {
	 
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "abc";
	}

}
