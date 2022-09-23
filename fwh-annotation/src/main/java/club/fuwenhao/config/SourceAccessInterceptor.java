package club.fuwenhao.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

/**
 * 资源认证拦截校验
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2020/5/20 11:33 上午
 */
public class SourceAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //前置拦截认证
        final StringBuffer requestURL = request.getRequestURL();
        System.out.println(String.format("时间:%s 进入拦截器了", LocalTime.now()));

        final HandlerMethod handlerParam = (HandlerMethod) handler;
        final LoginRequired annotation = handlerParam.getMethod().getAnnotation(LoginRequired.class);
        if (annotation == null) {
            return true;
        }
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(String.format("时间:%s ,路径：%s,你访问的资源需要登录。", LocalTime.now(), requestURL));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
