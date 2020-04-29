package com.ydj.logback.requestContext;

public class RequestContext {

	private static final ThreadLocal requestContext = new ThreadLocal();

	private static void set(Object object) {
		requestContext.set(object);
	}

	private static void remove() {
		requestContext.remove();
	}

}
