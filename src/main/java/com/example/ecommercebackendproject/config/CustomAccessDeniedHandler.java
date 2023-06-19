package com.example.ecommercebackendproject.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
Mục đích của lớp này là xử lý các yêu cầu bị từ chối truy cập. Nếu một yêu cầu không có quyền truy cập đủ để truy cập tài nguyên được bảo vệ, phương thức handle sẽ được gọi để xử lý ngoại lệ AccessDeniedException.

Trong phương thức handle, mã trạng thái HTTP của phản hồi được đặt thành 403 (Forbidden) và thông báo "Access Denied!" được ghi vào phản hồi. Điều này sẽ thông báo cho người dùng rằng họ không có quyền truy cập vào tài nguyên mà họ đã yêu cầu.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("AccessDenied");
    }
}
