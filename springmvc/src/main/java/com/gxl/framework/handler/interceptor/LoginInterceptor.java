package com.gxl.framework.handler.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    

    public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除  
    
    public void setAllowUrls(String[] allowUrls) {  
      this.allowUrls = allowUrls;  
    }  
    

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      //获取请求的RUi:去除http:localhost:8080这部分剩下的
        String requestUrl = request.getRequestURI();
        //UTL:除了login.jsp是可以公开访问的，其他的URL都进行拦截控制
//        if (requestUrl.indexOf("/login") >= 0|| requestUrl.indexOf("/webservice/api") >= 0) {
//            return true;
//        }
        
        if(null != allowUrls && allowUrls.length>=1){  
            for(String url : allowUrls) {   
              if(requestUrl.contains(url)) {   
                return true;   
              }   
            } 
      } 
        
        //获取session
//        HttpSession session = request.getSession();
//        UserInfo user = (UserInfo) session.getAttribute(Constants.SESSION_USER);
//        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
//        if (user != null) {
//            MDC.put("UserId", user.getUi_login_name());
//            logService.loadLog(user.getUi_id(), requestUrl, "", NetworkInfo.getclientIP(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest()));
//            return true;
//        }
//        //不符合条件的给出提示信息，并转发到登录页面
////        request.setAttribute("msg", "您还没有登录，请先登录！");
//
//        if(requestUrl.contains("webservice/api/mobile") || "Android".equals(request.getHeader("xs-source"))) {
//            new JSONUtil(request,response).responseClientSessionTimeout();
//
//        }else {
//            response.sendRedirect(request.getContextPath()+"/loginIndex");
//        }
     // 未登录，重定向到登录页面
        
//        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        return false;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
