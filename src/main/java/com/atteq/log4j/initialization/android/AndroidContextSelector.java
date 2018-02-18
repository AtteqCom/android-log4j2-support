package com.atteq.log4j.initialization.android;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.impl.ContextAnchor;
import org.apache.logging.log4j.core.selector.ContextSelector;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by loune on 16/05/2015.
 */
public class AndroidContextSelector implements ContextSelector {

	private static final LoggerContext CONTEXT = new LoggerContext("Default");

	private void start(LoggerContext context) {
		InputStream stream = AndroidLog4jHelper.getConfig();

		try {
			ConfigurationSource source = new ConfigurationSource(stream);
			Configuration config = org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory.getInstance()
					.getConfiguration(source);
			context.start(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LoggerContext getContext(String fqcn, ClassLoader loader, boolean currentContext) {
		if (!CONTEXT.isStarted()) {
			start(CONTEXT);
		}
		final LoggerContext ctx = ContextAnchor.THREAD_CONTEXT.get();
		return ctx != null ? ctx : CONTEXT;
	}

	public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext,
			final URI configLocation) {
		if (!CONTEXT.isStarted()) {
			start(CONTEXT);
		}
		final LoggerContext ctx = ContextAnchor.THREAD_CONTEXT.get();
		return ctx != null ? ctx : CONTEXT;
	}

	public void removeContext(final LoggerContext context) {
	}

	public List<LoggerContext> getLoggerContexts() {
		if (!CONTEXT.isStarted()) {
			start(CONTEXT);
		}
		final List<LoggerContext> list = new ArrayList<>();
		list.add(CONTEXT);
		return Collections.unmodifiableList(list);
	}
}
