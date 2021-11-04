package com.SagaLogistic.backend.database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.SagaLogistic.backend.models.Item;

import java.util.concurrent.Future;

public class ItemDAOFirebase implements ItemDAO{
    @Override
    public void add(@NonNull Item item) {

    }

    @Nullable
    @Override
    public Future<Item> get(@NonNull String id) {
        return null;
    }

    @Override
    public void update(@NonNull String id, @NonNull Item item) {

    }

    @Override
    public void delete(@NonNull String id) {

    }
}
