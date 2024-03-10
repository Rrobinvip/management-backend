package com.rrobinvip.interceptor;

import com.rrobinvip.constant.JwtClaimsConstant;
import com.rrobinvip.context.BaseContext;
import com.rrobinvip.properties.JwtProperties;
import com.rrobinvip.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
// Use jakarta instead of javax
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT token interceptor
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Pre-processes each HTTP request to check JWT authentication.
     * <p>
     * This method intercepts HTTP requests to verify JWT tokens for authentication purposes before the request reaches the controller.
     * It extracts the JWT token from the request header, validates the token, and extracts the employee ID from the token claims.
     * If the token validation fails, it sets the HTTP response status to 401 (Unauthorized), preventing the request from reaching the controller.
     *
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return boolean true if the request should proceed to the handler (controller), false if the request should be rejected based on JWT authentication failure
     * @throws Exception if there is an error in JWT token parsing or verification
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // Check if it's handler for controller
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // Get token from request header
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        // Verify token
        try {
            log.info("jwt verification:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("current employee id：{}", empId);

            BaseContext.setCurrentId(empId);
            return true;
        } catch (Exception ex) {
            response.setStatus(401);
            return false;
        }
    }
}
