package com.example.myweather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myweather.R
import com.example.myweather.databinding.FragmentChooseCityBinding
import com.example.myweather.presentation.viewmodel.ChooseCityViewModel
import com.example.myweather.presentation.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChooseCityFragment : Fragment() {

    private lateinit var binding: FragmentChooseCityBinding

    private val mainViewModel: MainViewModel by viewModels()
    private val chooseCityViewModel: ChooseCityViewModel by viewModels()

    private val args: ChooseCityFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseCityBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObservers()
        setupViews()
        if (!args.search) {
            chooseCityViewModel.checkCity()
        }
    }

    private fun setupViews() {
        binding.run {
            btnConfirm.setOnClickListener {
                val city = tietName.text.toString().trim()
                if (city.isNotEmpty()) {
                    loading(true)
                    mainViewModel.getWeatherForCity(city)
                } else onError(resources.getString(R.string.empty_field))
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainViewModel.weather.collect {
                        if (it != null) {
                            chooseCityViewModel.chooseCity(it)
                            navigateToMain(it.name)
                        }
                    }
                }

                launch {
                    mainViewModel.error.collect {
                        if (it?.isNotEmpty() == true) {
                            onError(it)
                        }
                    }
                }

                launch {
                    chooseCityViewModel.lastCity.collect {
                        if (it == null) {
                            loading(false)
                        } else {
                            navigateToMain(it)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToMain(cityName: String) {
        activity?.findNavController(R.id.nav_host)
            ?.navigate(
                ChooseCityFragmentDirections.actionChooseCityDestToMainDest(
                    cityName
                )
            )
    }

    private fun onError(errorMessage: String?) {
        loading(false)
        Snackbar
            .make(
                binding.root,
                errorMessage
                    ?: resources.getString(R.string.unknown_error),
                1000
            ).show()
    }

    private fun loading(bool: Boolean) {
        binding.run {
            tvCity.visibility = if (bool) View.GONE else View.VISIBLE
            tilName.visibility = if (bool) View.GONE else View.VISIBLE
            btnConfirm.visibility = if (bool) View.GONE else View.VISIBLE
            pbLoading.visibility = if (bool) View.VISIBLE else View.GONE
        }
    }
}
