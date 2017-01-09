package com.sebastian_daschner.scalable_coffee_shop;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import java.io.File;
import java.nio.file.Paths;

@Singleton
@Startup
public class EventStorePersistenceExposer {

    private DB mapDb;

    @PostConstruct
    private void initDb() {
        // change with desired db location
        final File dbLocation = Paths.get("/tmp/db.mapdb").toFile();
        mapDb = DBMaker.fileDB(dbLocation).transactionEnable().make();
    }

    @Produces
    public DB exposeDb() {
        return mapDb;
    }

    @PreDestroy
    private void shutdownDb() {
        mapDb.close();
    }

}
