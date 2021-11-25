package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding
import com.sagalogistics.backend.database.RepositoryFactoryFirebase
import com.sagalogistics.backend.database.Repository
import com.sagalogistics.backend.models.ItemImpl
import com.sagalogistics.backend.models.OrderImpl

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        //test
        Repository.initialize(RepositoryFactoryFirebase())
        val repo = Repository.getInstance()
        val test1 = ItemImpl("test1", 0.0F)
        repo.addItem(test1)
        repo.addItem(test1)
        val test2 = ItemImpl("test2", 0.0F)
        repo.addItem(test2)

        test2.weight = 1.5F
        test2.key?.let { repo.updateItem(it, test2) }

        val orderTest = OrderImpl()
        test1.key?.let { orderTest.updateItem(it, 2) }
        test2.key?.let { orderTest.updateItem(it, 5) }
        repo.addOrder(orderTest)
        var orderTest2 = orderTest.key?.let { repo.getOrder(it) }

        test1.key?.let { repo.deleteItem(it) }
        orderTest2 = orderTest.key?.let { repo.getOrder(it) }
        test1.key?.let { orderTest.removeItem(it) }

        val testnull = repo.getItem("keythatdoesnotexist")
        //repo.addItem(test1)
        //orderTest.updateItem(test1.key, 8)
        //repo.updateOrder(orderTest.key, orderTest)
        //end of test

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}