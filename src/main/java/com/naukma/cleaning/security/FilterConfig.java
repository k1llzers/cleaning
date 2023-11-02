package com.naukma.cleaning.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;
import org.springframework.web.ErrorResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CleaningGeneralPostFilter> cleaningGeneralPostAuthFilter() {
        FilterRegistrationBean<CleaningGeneralPostFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CleaningGeneralPostFilter());
        registrationBean.addUrlPatterns("/*"); 

        return registrationBean;
    }

    public class CleaningGeneralPostFilter extends OncePerRequestFilter{
        private final Logger logger = LoggerFactory.getLogger(CleaningGeneralPostFilter.class);

        @Override
        protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
            try{
                CachedBodyHttpServletRequest wrappedRequest = new CachedBodyHttpServletRequest((HttpServletRequest) req);
                String body = StreamUtils.copyToString(wrappedRequest.getInputStream(), StandardCharsets.UTF_8);
                if(!wrappedRequest.getMethod().equals("POST")){
                    chain.doFilter(wrappedRequest, resp);
                    return;
                }
                Map<String, Object> bodyMap = new ObjectMapper().readValue(body, Map.class);
                if(bodyMap.size() > 0){
                    chain.doFilter(wrappedRequest, resp);
                    return;
                }
                FilterConfig.handle(resp, new Exception("Empty Post body"), logger);
            } catch (Exception e){
                FilterConfig.handle(resp, new Exception("Empty Post body"), logger);
            }
        }
    }

    static private void handle(ServletResponse resp, Exception e, Logger logger){
        HttpServletResponse response = (HttpServletResponse) resp;
        logger.warn("Error in filter: " + e.getMessage());
        try {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty Post body");
        } catch (IOException e2) {
            logger.error(e2.getMessage());
        }
    }
}
