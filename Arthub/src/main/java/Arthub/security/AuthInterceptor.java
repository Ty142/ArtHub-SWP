package Arthub.security;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
            return true;
        }
    }


