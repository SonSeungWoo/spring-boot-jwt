package me.seungwoo.springbootjwt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Leo.
 * User: sonseungwoo
 * Date: 2019-02-18
 * Time: 21:33
 */
public class LgeApiFilter extends GenericFilterBean {

    @Value("${obt.applicationId}")
    private String applicationId;

    @Value("${obt.restApiToken}")
    private String restApiToken;

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("X-Obt-Rest-Api-Token");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        } else {
            if (authHeader == null) {
                throw new ServletException("Missing or invalid Authorization header apiKey");
            }
            final String token = authHeader;

            if(restApiToken.equals(token)){
                request.setAttribute("X-Obt-Rest-Api-Token", token);
            } else {
                throw new ServletException("Invalid apiKey");
            }
            chain.doFilter(req, res);
        }
    }
}
