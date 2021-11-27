package com.sagalogistics.backend.models

import org.junit.Assert
import org.junit.Test

class OrderImplTest {
    @Test
    fun test_true_private(){
        var test = OrderImpl()
        test.updateItem("hola", 0)
        var test2 = test.items
        test2["hola"] = 1
        Assert.assertEquals(0, test.getQuantityItem("hola"))
    }

    @Test
    fun test_shallow_copy(){
        var test = HashMap<String, Item>()
        test.put("a", ItemImpl(name = "test", weight = 0.0f))
        var test2 = HashMap<String, Item>(test)
        var item = test2.get("a")
        if (item != null) {
            item.key = "test2"
        }
        Assert.assertNotEquals("test", test.get("a")?.key)
    }
}