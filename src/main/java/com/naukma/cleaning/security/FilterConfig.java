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

    public class CleaningGeneralPostFilter implements Filter {
        private final Logger logger = LoggerFactory.getLogger(CleaningGeneralPostFilter.class);

        @Override
        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
            try{
                HttpServletRequest request = (HttpServletRequest) req;
                String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
                if(!request.getMethod().equals("POST")){
                    chain.doFilter(req, resp);
                    return;
                }
                Map<String, Object> bodyMap = new ObjectMapper().readValue(body, Map.class);
                if(bodyMap.size() > 0){
                    chain.doFilter(req, resp);
                    return;
                }
                else{
                    FilterConfig.handle(resp, new Exception("Empty Post body"), logger);
                }
                
            } catch (Exception e){
                FilterConfig.handle(resp, e, logger);
            }
        }
    }

    static private void handle(ServletResponse resp, Exception e, Logger logger){
        HttpServletResponse response = (HttpServletResponse) resp;
        logger.warn("(500) Error in filter: " + e.getMessage());
        try {
            response.sendError(500, "Error in filter: " + e.getMessage());
        } catch (IOException e2) {
            logger.error(e2.getMessage());
        }
    }
}
