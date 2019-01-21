/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.jwt.struts.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class ExcludedParameterFilter implements Filter {
	private static final String DEFAULT_BLACKLIST_PATTERN = "(.*\\.|^|.*|\\[('|\"))(c|C)lass(\\.|('|\")]|\\[).*";
	private static final String INIT_PARAM_NAME = "excludeParams";

	private Pattern pattern;

	public void init( FilterConfig filterConfig ) throws ServletException {
		final String toCompile;
		final String initParameter = filterConfig.getInitParameter(INIT_PARAM_NAME);
		if (initParameter != null && initParameter.trim().length()>0) {
			toCompile = initParameter;
		} else {
			toCompile = DEFAULT_BLACKLIST_PATTERN;
		}
		this.pattern = Pattern.compile(toCompile, Pattern.DOTALL);
	}

	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain )
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			ExcludedParameterRequest wrapper = new ExcludedParameterRequest((HttpServletRequest) request, pattern);
			chain.doFilter(wrapper,response);
		}else {
			chain.doFilter(request,response);
		}
	}

	@Override
	public void destroy() {
	}
}