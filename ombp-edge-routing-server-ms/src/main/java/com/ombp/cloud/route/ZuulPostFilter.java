package com.ombp.cloud.route;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ZuulPostFilter extends ZuulFilter {

	@Override
    public String filterType() {
        return "post";
    }
    @Override
    public int filterOrder() {
        return 2;
    }
    @Override
    public boolean shouldFilter() {
        return true;
    }
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try (final InputStream responseDataStream = ctx.getResponseDataStream())
        {
            final String responseData =
                CharStreams.toString(new InputStreamReader(responseDataStream,
                    "UTF-8"));

            ctx.setResponseBody(responseData);
        } catch (IOException e) {
            try {
				throw e;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        return null;
    }
}

