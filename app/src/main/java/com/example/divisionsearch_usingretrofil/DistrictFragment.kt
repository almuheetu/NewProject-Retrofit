package com.example.divisionsearch_usingretrofil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.divisionsearch_usingretrofil.databinding.FragmentDistrictListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DistrictFragment : Fragment() {
    private lateinit var binding: FragmentDistrictListBinding
    private lateinit var districtAdapter: DistrictAdapter
    private val args: DistrictFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDistrictListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val division = args.data
        val divisionId = division.id

        val recyclerView: RecyclerView = binding.districtRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val responseApi = RetrofitHelper.getInstance().create(ResponseApi::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            val result = responseApi.getDivision()
            if (result.body() != null) {
                Log.d("response", result.body().toString())
                val allDistricts = result.body()!!
                val filteredDistricts = allDistricts.filter { it.id == divisionId }
                districtAdapter = DistrictAdapter(filteredDistricts[0].districts)
                recyclerView.adapter = districtAdapter
            }
        }
    }
}