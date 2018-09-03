package com.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class MyInterceptor implements HandlerInterceptor {

	//在请求处理之前进行调用（Controller方法调用之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("----- 方法执行即将执行 -----");
		return true; //如果false，停止流程，api被拦截
	}

	//请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("----- 方法执行完成 -----");
	}

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		  System.out.println("afterCompletion被调用");
	}
	
	   //mvc控制器
    @Configuration
    static class WebMvcConfigurer extends WebMvcConfigurerAdapter{
        //增加拦截器
        public void addInterceptors(InterceptorRegistry registry){
            registry.addInterceptor(new MyInterceptor())    //指定拦截器类
                    .addPathPatterns("/login/test");        //指定该类拦截的url
        }
    }

}
