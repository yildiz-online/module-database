package be.yildiz.module.database;

import be.yildiz.common.exeption.UnhandledSwitchCaseException;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public class DatabaseConnectionProviderFactory {

    public static DataBaseConnectionProvider create(DbProperties properties) throws SQLException {
        DatabaseSystem system;
        switch (properties.getSystem()) {
            case "derby-memory" :
                system = DataBaseConnectionProvider.DBSystem.DERBY_IN_MEMORY;
                break;
            case "derby-file" :
                system = DataBaseConnectionProvider.DBSystem.DERBY;
                break;
            case "derby-create":
                system = DataBaseConnectionProvider.DBSystem.DERBY_CREATE;
                break;
            case "mysql":
                system = DataBaseConnectionProvider.DBSystem.MYSQL;
                break;
            default: throw new UnhandledSwitchCaseException(properties.getSystem());
        }
        if("no-pool".equals(properties.getPool())) {
            return new NoPoolConnectionProvider(system, properties);
        } else if("c3p0".equals(properties.getPool())) {
            return new C3P0ConnectionProvider(system, properties);
        } else throw new UnhandledSwitchCaseException(properties.getPool());

    }
}
