package be.yildiz.module.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.Properties;

/**
 * @author Grégory Van den Borre
 */
public class NoPoolConnectionProvider extends DataBaseConnectionProvider {

    private final Driver driver;

    private Connection connection;

    /**
     * Create a new Database connection provider.
     *
     * @param system     Database system to use.
     * @param properties Properties holding connection data.
     * @throws NullPointerException if a parameter is null.
     * @throws SQLException         If the given DB system is not managed.
     */
    public NoPoolConnectionProvider(DatabaseSystem system, DbProperties properties) throws SQLException {
        super(system, properties);
        try {
            Class.forName(system.getDriver());
            this.driver = system.getDriverProvider().getDriver();
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found: " + system.getDriver(), e);
        }

    }

    @Override
    protected Connection getConnectionImpl() throws SQLException {
        if(this.connection == null || this.connection.isClosed()) {
            this.connection = this.driver.connect(this.getUri(), new Properties());
        }
        return this.connection;
    }

    @Override
    public void close() throws Exception {
        if(this.getSystem() == DBSystem.DERBY_IN_MEMORY) {
            try {
                this.driver.connect(this.getUri().replace("create", "drop"), new Properties());
            } catch (SQLNonTransientConnectionException e) {
                //Expected exception when closing in memory derby.
            }
        } else {
            this.connection.close();
        }
    }
}
