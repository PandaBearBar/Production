package com.jb.project3.finalCouponSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebConfig implements Filter {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry
//                .addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*");
//    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ((HttpServletResponse)servletResponse).addHeader("Access-Control-Allow-Origin","*");
        ((HttpServletResponse)servletResponse).addHeader("Access-Control-Allow-Origin","GET, OPTIONS, HEAD, PUT, DELETE, *");
        ((HttpServletResponse)servletResponse).addHeader("Access-Control-Allow-Origin","authorization, Origin, *");

        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (request.getMethod().equals("OPTIONS")){
            return;
        }
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}