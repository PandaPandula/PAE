package com.SagaLogistic.backend.database;

import androidx.annotation.Nullable;

import com.SagaLogistic.backend.models.Item;

import java.util.concurrent.Future;

public class ItemDAOFirebase implements ItemDAO{
    @Override
    public void add(@Nullable Item item) {

    }

    @Nullable
    @Override
    public Future<Item> get(@Nullable String id) {
        return null;
    }

    @Override
    public void update(@Nullable String id, @Nullable Item item) {

    }

    @Override
    public void delete(@Nullable String id) {

    }
}
