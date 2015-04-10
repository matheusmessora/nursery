package br.com.pandox.nursery.monitors;

import br.com.pandox.nursery.domain.metricData.MetricDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ServletResponseTimeMonitor extends HandlerInterceptorAdapter {

    @Autowired
    private MetricDataService metricDataService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HandlerMethod method = (HandlerMethod) handler;

        if(method.getBean().getClass().getSimpleName().contains("Controller")){
            long startTime = System.currentTimeMillis();
            request.setAttribute("startTime", startTime);

        }


        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView)
            throws Exception {

        HandlerMethod method = (HandlerMethod) handler;
        if(method.getBean().getClass().getSimpleName().contains("Controller")){
            long startTime = (Long)request.getAttribute("startTime");
            long endTime = System.currentTimeMillis();
            int executeTime = (int) (endTime - startTime);
            metricDataService.create(executeTime, 5L);
        }


    }
}
