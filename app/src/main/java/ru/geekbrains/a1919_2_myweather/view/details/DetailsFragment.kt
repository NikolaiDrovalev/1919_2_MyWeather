package ru.geekbrains.a1919_2_myweather.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.details_fragment.*
import ru.geekbrains.a1919_2_myweather.R
import ru.geekbrains.a1919_2_myweather.databinding.DetailsFragmentBinding
import ru.geekbrains.a1919_2_myweather.repository.Weather
import ru.geekbrains.a1919_2_myweather.utils.KEY_BUNDLE_WEATHER
import ru.geekbrains.a1919_2_myweather.viewmodel.DetailsState
import ru.geekbrains.a1919_2_myweather.viewmodel.DetailsViewModel

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

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) { t -> renderData(t) }
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            viewModel.getWeather(it.city)
        }
    }

    @SuppressLint("SetTextI18n", "StringFormatInvalid")
    private fun renderData(detailsState: DetailsState) {
        when (detailsState) {
            is DetailsState.Error -> {
                binding.loadingLayout.visibility = View.GONE
            }
            is DetailsState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is DetailsState.Success -> {
                val weather = detailsState.weather
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    cityName.text = weather.city.name
                    temperatureValue.text = weather.temperature.toString()
                    feelsLikeValue.text = weather.feelsLike.toString()
                    cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
                    headerCityIcon.load("http://freepngimg.com/thumb/city/36275-3-city-hd.png")
                    icon.loadSvg("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg")
                }
            }
        }
        mainView.showSnackBar(detailsState)
    }

    private fun ImageView.loadSvg(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
            .build()
        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()
        imageLoader.enqueue(request)
    }

    private fun View.showSnackBar(details: DetailsState) {
        when (details) {
            is DetailsState.Error -> {
                Snackbar.make(this, "${details.error}", Snackbar.LENGTH_LONG).show()
            }
            is DetailsState.Success -> {
                Snackbar.make(this, context.getString(R.string.success), Snackbar.LENGTH_LONG)
                    .show()
            }
            is DetailsState.Loading -> {
                Snackbar.make(this, context.getString(R.string.loading), Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}




