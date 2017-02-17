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
public class LiquibaseDatabaseUpdater implements DatabaseUpdater {

    private final String configurationFile;

    public LiquibaseDatabaseUpdater(String configurationFile) {
        super();
        this.configurationFile = configurationFile;
    }

    @Override
    public void update(DataBaseConnectionProvider provider) throws SQLException {
        try {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(provider.getConnection()));
            Liquibase liquibase = new Liquibase(this.configurationFile, new ClassLoaderResourceAccessor(), database);
            liquibase.update("database-update");
        } catch (LiquibaseException | SQLException e) {
            throw new SQLException(e);
        }
    }
}
