package com.loginproject.bean;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class AboutBean {

	private String[] values;

	public AboutBean() {
		values = new String[] { "about.title", "about.built", "about.site", "about.build" };
	}

	public String[] getValues() {
		return values;
	}

}
