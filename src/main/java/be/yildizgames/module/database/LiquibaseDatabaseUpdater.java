/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */

package be.yildizgames.module.database;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Liquibase implementation to update the database schema.
 *
 * @author Grégory Van den Borre
 */
public class LiquibaseDatabaseUpdater implements DatabaseUpdater {

    private final System.Logger logger = System.getLogger(this.getClass().getName());

    private final String configurationFile;

    private LiquibaseDatabaseUpdater(final String configurationFile) {
        super();
        Objects.requireNonNull(configurationFile);
        this.configurationFile = configurationFile;
    }

    public static LiquibaseDatabaseUpdater fromConfigurationPath(final String path) {
        return new LiquibaseDatabaseUpdater(path);
    }

    @Override
    public final void update(final DataBaseConnectionProvider provider) throws SQLException {
        this.logger.log(System.Logger.Level.INFO, "Updating database schema...");
        try (Connection c = provider.getConnection()) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
            Liquibase liquibase = new Liquibase(this.configurationFile, new ClassLoaderResourceAccessor(), database);
            liquibase.forceReleaseLocks();
            liquibase.update("database-update");
            this.logger.log(System.Logger.Level.INFO, "Updating database schema completed.");
            database.close();
        } catch (LockException e) {
            e.printStackTrace();
        } catch (LiquibaseException | SQLException e) {
            throw new SQLException(e);
        }
    }
}
