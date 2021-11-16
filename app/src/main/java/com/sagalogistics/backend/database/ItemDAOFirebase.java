package com.sagalogistics.backend.database;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sagalogistics.backend.models.Item;
import com.sagalogistics.backend.models.ItemImpl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ItemDAOFirebase implements ItemDAO{
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference itemsRef = database.getReference("items");

    @Override
    public void add(@NonNull Item item) {
        DatabaseReference pushedPostRef = itemsRef.push();
        item.setKey(pushedPostRef.getKey());
        pushedPostRef.setValue(item);
    }

    @NonNull
    @Override
    public Future<Item> get(@NotNull String key) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(() -> {
            Task<DataSnapshot> task = itemsRef.child(key).get();
            while(!task.isComplete());
            DataSnapshot data = task.getResult();
            Item item = data.getValue(ItemImpl.class);
            item.setKey(data.getKey());
            return item;
        });
    }

    @Override
    public void update(@NotNull String key, @NotNull Item item) {
        DatabaseReference itemRef = itemsRef.child(key);
        itemRef.setValue(item);
    }

    @Override
    public void delete(@NotNull String key) {
        DatabaseReference itemRef = itemsRef.child(key);
        itemRef.removeValue();
    }
}
