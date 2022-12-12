package com.ombp.cloud.route;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
@Component
public class ZuulPreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = new HttpServletRequestWrapper(ctx.getRequest());
        String requestData = null;
        try {
            if (request.getContentLength() > 0) {
                requestData = CharStreams.toString(request.getReader());
            }
        } catch (Exception e) {
            try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
        return null;
    }

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}

