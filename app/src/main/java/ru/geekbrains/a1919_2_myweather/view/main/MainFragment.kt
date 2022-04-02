package ru.geekbrains.a1919_2_myweather.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.a1919_2_myweather.R
import ru.geekbrains.a1919_2_myweather.databinding.MainFragmentBinding
import ru.geekbrains.a1919_2_myweather.viewmodel.MainViewModel

class MainFragment : Fragment() {

     private var binding: MainFragmentBinding? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = object : Observer<Any> {
            override fun onChanged(data: Any) {
                renderData(data)
            }
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getWeather()
    }

    private fun renderData(data: Any) {
        Toast.makeText(requireContext(), "Работает", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}