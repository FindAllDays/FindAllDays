package com.ift.slip.resteasy.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.ift.slip.url.service.AquaticURLService;

public class JaxRsActivator extends Application{
	private Set<Object> singletons = new HashSet<Object>();

	public JaxRsActivator() {
		singletons.add(new AquaticURLService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
