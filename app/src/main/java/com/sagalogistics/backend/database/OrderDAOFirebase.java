package com.sagalogistics.backend.database;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sagalogistics.backend.models.Order;
import com.sagalogistics.backend.models.OrderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OrderDAOFirebase implements OrderDAO{
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final DatabaseReference ordersRef = database.getReference("orders");

    @Override
    public void add(Order order) {
        DatabaseReference pushedPostRef = ordersRef.push();
        order.setKey(pushedPostRef.getKey());
        pushedPostRef.setValue(order);
    }

    @Override
    public Future<Order> get(String key) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(() -> {
            Task<DataSnapshot> task = ordersRef.child(key).get();
            while(!task.isComplete());
            DataSnapshot data = task.getResult();
            Order order = data.getValue(OrderImpl.class);
            order.setKey(data.getKey());
            return order;
        });
    }

    @Override
    public void update(String key, Order order) {
        DatabaseReference orderRef = ordersRef.child(key);
        orderRef.setValue(order);
    }

    @Override
    public void delete(String key) {
        DatabaseReference orderRef = ordersRef.child(key);
        orderRef.removeValue();
    }

    @Override
    public void deleteItem(String itemKey) {
        Task<DataSnapshot> task = ordersRef.get();
        while(!task.isComplete()); //forcem concurrencia
        if(task.isSuccessful()) {
            DataSnapshot data = task.getResult();
            if (data != null) {
                Map<String, Object> orders = (Map<String, Object>) data.getValue();
                for (Map.Entry<String, Object> order : orders.entrySet()) {
                    ordersRef.child(order.getKey()).child("items").child(itemKey).removeValue();
                }
            }
        }
    }
}
