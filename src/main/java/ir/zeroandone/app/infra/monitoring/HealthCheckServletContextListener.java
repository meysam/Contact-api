package ir.zeroandone.app.infra.monitoring;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;

public class HealthCheckServletContextListener extends HealthCheckServlet.ContextListener {

    public static final HealthCheckRegistry HEALTH_CHECK_REGISTRY = new HealthCheckRegistry();

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        HEALTH_CHECK_REGISTRY.register("api.database.bmi_api.connection.healthcheck", new DatabaseHealthCheck());
        return HEALTH_CHECK_REGISTRY;
    }

}