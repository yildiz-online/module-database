package be.yildiz.module.database;

import java.sql.SQLException;

/**
 * @author Grégory Van den Borre
 */
public interface DatabaseUpdater {

    void update(DataBaseConnectionProvider provider) throws SQLException;
}
