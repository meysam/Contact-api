package ir.zeroandone.app.infra.monitoring.interceptor;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import ir.zeroandone.app.infra.monitoring.MetricsServletContextListener;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Monitored
public class MonitoringInterceptor {

    @AroundInvoke
    public Object monitor(InvocationContext context) throws Exception {
        Exception f = null;
        MetricRegistry registry = MetricsServletContextListener.METRIC_REGISTRY;
        String calledClass = context.getMethod().getDeclaringClass().getSimpleName();
        String calledMethod = context.getMethod().getName();
        Meter requestMetric = registry.meter(calledClass + "/" + calledMethod);
        requestMetric.mark();
        Timer responses = registry.timer("timers/"+calledClass + "/" + calledMethod);
        Timer exceptionResponse = registry.timer("timers/"+calledClass + "/" + calledMethod + "@Exception");
        Timer.Context time = responses.time();
        Timer.Context timeF = exceptionResponse.time();
        try {
            Object proceed = context.proceed();
            return proceed;
        } catch (Exception e) {
            f = e;
            Meter exceptionMetrics = registry
                    .meter(calledClass + "/" + calledMethod + '@' + e.getClass().getCanonicalName());
            exceptionMetrics.mark();
            throw e;
        } finally {
            if (f == null)
                time.stop();
            else
                timeF.stop();
        }
    }

  /*  public void Monitor(String clazz, String methodName){
        Exception f = null;
        MetricRegistry registry = MetricsServletContextListener.METRIC_REGISTRY;
        String calledClass = clazz;
        String calledMethod = methodName;
        Meter requestMetric = registry.meter(calledClass + "/" + calledMethod);
        requestMetric.mark();
        Timer responses = registry.timer("timers/"+calledClass + "/" + calledMethod);
        Timer exceptionResponse = registry.timer("timers/"+calledClass + "/" + calledMethod + "@Exception");
        Timer.Context time = responses.time();
        Timer.Context timeF = exceptionResponse.time();
        try {
            Object proceed = context.proceed();
            return proceed;
        } catch (Exception e) {
            f = e;
            Meter exceptionMetrics = registry
                    .meter(calledClass + "/" + calledMethod + '@' + e.getClass().getCanonicalName());
            exceptionMetrics.mark();
            throw e;
        } finally {
            if (f == null)
                time.stop();
            else
                timeF.stop();
        }
    }*/

}
