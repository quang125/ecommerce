package com.example.ecommercebackendproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
Mục đích của lớp này là xử lý các yêu cầu truy cập bị từ chối do thiếu thông tin xác thực hoặc xác thực không hợp lệ. Nếu một yêu cầu không có thông tin xác thực hợp lệ, phương thức commence sẽ được gọi để xử lý ngoại lệ AuthenticationException.

Trong phương thức commence, một thông báo lỗi sẽ được ghi lại và trả về trong phản hồi HTTP, cho biết lỗi "Unauthorized". Mã trạng thái HTTP của phản hồi được đặt thành 401 (Unauthorized).
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error. Message - {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error -> Unauthorized");
    }
}
