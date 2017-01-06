package be.yildiz.module.database;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public class TestingDatabaseInit {

    public DataBaseConnectionProvider init(final String changeLogFile) throws LiquibaseException, SQLException {
        DataBaseConnectionProvider dbcp = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDbProperties());
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(dbcp.getConnection()));
        Liquibase liquibase = new Liquibase(changeLogFile, new ClassLoaderResourceAccessor(), database);
        liquibase.update("test");
        return dbcp;
    }

    /**
     * @author Grégory Van den Borre
     */
    private class TestingDbProperties implements DbProperties {
        @Override
        public String getDbUser() {
            return "YILDIZDATABASE";
        }

        @Override
        public int getDbPort() {
            return 0;
        }

        @Override
        public String getDbPassword() {
            return "";
        }

        @Override
        public String getDbHost() {
            return "";
        }

        @Override
        public String getDbName() {
            return "YILDIZDATABASE";
        }
    }
}
