package com.example.divisionsearch_usingretrofil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.divisionsearch_usingretrofil.databinding.FragmentDivisionListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DivisionFragment : Fragment(), DivisionAdapter.ItemClickListener {
    private lateinit var binding: FragmentDivisionListBinding
    private lateinit var divisionAdapter: DivisionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDivisionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.divisionRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val responseApi = RetrofitHelper.getInstance().create(ResponseApi::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val result = responseApi.getDivision()
            if (result.body() != null) {
                Log.d("response", result.body().toString())
                divisionAdapter = DivisionAdapter(result.body()!!, this@DivisionFragment)
                recyclerView.adapter = divisionAdapter
            }
        }
    }

    override fun onItemClick(division: DivisionResponseItem) {
        val action = DivisionFragmentDirections.actionDivisionFragmentToDistrictFragment(division)
        findNavController().navigate(action)
    }
}