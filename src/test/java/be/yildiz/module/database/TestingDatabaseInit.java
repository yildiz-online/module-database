package be.yildiz.module.database;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public class TestingDatabaseInit {

    public DataBaseConnectionProvider init(final String changeLogFile) throws SQLException {
        DataBaseConnectionProvider dbcp = new NoPoolConnectionProvider(DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY, new TestingDbProperties());
        new LiquibaseDatabaseUpdater(changeLogFile).update(dbcp);
        return dbcp;
    }

    /**
     * @author Grégory Van den Borre
     */
     static class TestingDbProperties implements DbProperties {
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
