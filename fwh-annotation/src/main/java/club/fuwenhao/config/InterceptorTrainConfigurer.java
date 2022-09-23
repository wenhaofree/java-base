package club.fuwenhao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2020/5/20 11:41 上午
 */
@Configuration
public class InterceptorTrainConfigurer implements WebMvcConfigurer {

    /**
     * 添加一个拦截器
     *
     * @param registry
     * @return void
     * @author fwh [2020/5/20 && 11:42 上午]
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SourceAccessInterceptor()).addPathPatterns("/**");
    }
}
