package ru.geekbrains.a1919_2_myweather.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.a1919_2_myweather.R
import ru.geekbrains.a1919_2_myweather.databinding.WeatherListFragmentBinding
import ru.geekbrains.a1919_2_myweather.repository.Weather
import ru.geekbrains.a1919_2_myweather.utils.KEY_BUNDLE_WEATHER
import ru.geekbrains.a1919_2_myweather.view.details.DetailsFragment
import ru.geekbrains.a1919_2_myweather.viewmodel.AppState
import ru.geekbrains.a1919_2_myweather.viewmodel.MainViewModel

class WeatherListFragment : Fragment(), OnItemListClickListener {

    private var _binding: WeatherListFragmentBinding? = null
    private val binding: WeatherListFragmentBinding
        get() {
            return _binding!!
        }

    private val adapter = WeatherListAdapter(this)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WeatherListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var isRussian = true

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        val observer = { data: AppState -> renderData(data) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        setupFab()
        viewModel.getWeatherRussia()
    }

    private fun setupFab() {
        binding.floatingActionButton.setOnClickListener {
            isRussian = !isRussian
            if (isRussian) {
                viewModel.getWeatherRussia()
                binding.floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_russia
                    )
                )
            } else {
                viewModel.getWeatherWorld()
                binding.floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_earht
                    )
                )
            }
        }
    }

    private fun initRecycler() = adapter.apply { binding.recyclerView.adapter = adapter }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.root.let {
                    Snackbar.make(
                        it,
                        getString(R.string.failed) + "${data.error}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                adapter.setData(data.weatherListData)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }

    override fun onItemClick(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                DetailsFragment.newInstance(Bundle().apply {
                    putParcelable(
                        KEY_BUNDLE_WEATHER,
                        weather
                    )
                })
            ).addToBackStack("")
            .commit()
    }
}