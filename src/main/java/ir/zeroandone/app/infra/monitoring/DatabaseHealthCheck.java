package ir.zeroandone.app.infra.monitoring;

import com.codahale.metrics.health.HealthCheck;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseHealthCheck extends HealthCheck {

    public static final ThreadLocal<DataSource> connFactory = new ThreadLocal<DataSource>() {
        @Override
        protected DataSource initialValue() {
            try {
                InitialContext jndiContext = new InitialContext();
                return (DataSource) jndiContext.lookup("jdbc/bmi_db2");
            } catch (NamingException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    @Override
    protected Result check() throws Exception {
        Connection connection = connFactory.get().getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT current timestamp FROM sysibm.sysdummy1");
                resultSet.next();
                resultSet.getTimestamp(1);
                return Result.healthy("ok");
            } finally {
                if (!connection.isClosed())
                    connection.close();
            }
        }
        return Result.unhealthy("Can't connect to database");
    }
}
