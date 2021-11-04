package com.SagaLogistic.backend.database;

import com.SagaLogistic.backend.models.CompatibleDB;

public class RepositoryFactory {
    public Repository createRepository(CompatibleDB db){
        Repository repository;
        switch (db){
            case Firebase:
                repository = new RepositoryFirebase();
                break;
            default: //should not happen since CompatibleDB is an Enum
                throw new IllegalStateException("Unsupported DB: " + db);
        }
        return repository;
    }
}
