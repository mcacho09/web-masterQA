package com.retailsbs.logistikapp.manager.logistic.test.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml",
		"/applicationContext-logistic.xml","/applicationContext-retail.xml","/applicationContext-manager-logistic.xml"})

public class BaseTestJunit {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public BaseTestJunit() {
	}

}
