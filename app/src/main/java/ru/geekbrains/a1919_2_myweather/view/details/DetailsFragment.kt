package ru.geekbrains.a1919_2_myweather.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.a1919_2_myweather.R
import ru.geekbrains.a1919_2_myweather.databinding.DetailsFragmentBinding
import ru.geekbrains.a1919_2_myweather.repository.Weather
import ru.geekbrains.a1919_2_myweather.utils.KEY_BUNDLE_WEATHER

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding: DetailsFragmentBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let { renderData(it) }

    }

    @SuppressLint("SetTextI18n")
    private fun renderData(weather: Weather) {
        with(binding) {
            loadingLayout.visibility = View.GONE
            weather.also{
                cityName.text = it.city.name
                temperatureValue.text = it.temperature.toString()
                feelsLikeValue.text = it.feelsLike.toString()
                cityCoordinates.text = "${it.city.lat} ${it.city.lon}"
            }
            mainView.showSnackBar()
        }
    }

    private fun View.showSnackBar() =  Snackbar.make(this, getString(R.string.happened), Snackbar.LENGTH_LONG).show()

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}