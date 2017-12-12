package be.yildiz.module.database;

import org.h2.Driver;
import org.jooq.SQLDialect;

class DummySystem implements DatabaseSystem {

    @Override
    public SQLDialect getDialect() {
        return SQLDialect.H2;
    }

    @Override
    public String getDriver() {
        return "org.h2.Driver";
    }

    @Override
    public DriverProvider getDriverProvider() {
        return Driver::new;

    }

    @Override
    public String getUrl(DbProperties p) {
        return "jdbc:h2:~/test";
    }
}
