package com.sagalogistics

import CustomAdapter
import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagalogistics.backend.models.ItemImpl

class BarOrderActivity : Activity()
{
    lateinit var mRecyclerView : RecyclerView

    private var items: List<ItemImpl> = listOf(
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),
        ItemImpl(name = "Pito",weight = 3f,image = "https://res.cloudinary.com/teepublic/image/private/s--AO0td9Wb--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_000000,e_outline:48/co_000000,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_auto,h_630,q_90,w_630/v1602419580/production/designs/14927736_0.jpg"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_bar)
        setUpRecyclerView()
    }

    fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.recylerview)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(items)
        mRecyclerView.adapter = adapter
    }
}