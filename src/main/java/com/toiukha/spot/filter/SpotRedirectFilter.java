package com.toiukha.spot.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/spot/listAllSpot.jsp")
public class SpotRedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[Filter] SpotRedirectFilter 初始化成功");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        System.out.println("[Filter] 攔截到請求：" + httpRequest.getRequestURI());
        System.out.println("[Filter] 請求方法：" + httpRequest.getMethod());
        
        // 檢查是否有景點資料
        Object spotList = httpRequest.getAttribute("spotList");
        System.out.println("[Filter] spotList 屬性：" + spotList);
        
        if (spotList == null) {
            System.out.println("[Filter] 沒有資料，準備重定向");
            
            // 建立重定向 URL
            String contextPath = httpRequest.getContextPath();
            String redirectUrl = contextPath + "/spot/SpotServlet?action=listAll";
            
            System.out.println("[Filter] 重定向到：" + redirectUrl);
            
            // 執行重定向
            httpResponse.sendRedirect(redirectUrl);
            return; // 重要：必須 return，不繼續執行
        }
        
        System.out.println("[Filter] 有資料，繼續處理 JSP");
        // 如果有資料，繼續處理
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("[Filter] SpotRedirectFilter 銷毀");
    }
}
