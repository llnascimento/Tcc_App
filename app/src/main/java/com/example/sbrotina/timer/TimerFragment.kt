package com.example.sbrotina.timer

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sbrotina.R
import com.example.sbrotina.databinding.FragmentTimerBinding
import com.example.sbrotina.notification.TimerNotification


@Suppress("DEPRECATION")
class TimerFragment : Fragment() {
    private lateinit var timerViewModel: TimerViewModel
    private lateinit var settingPref: SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTimerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_timer, container, false
        )

        timerViewModel = ViewModelProvider(requireActivity()).get(TimerViewModel::class.java)
        settingPref = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val vibe: Vibrator? = ContextCompat.getSystemService(requireContext(), Vibrator::class.java)
        val myNotification = TimerNotification(requireContext())



        binding.lifecycleOwner = this

        binding.timerViewModel = timerViewModel

        setTime()
        setKeepScreenOn()

        timerViewModel.startTimerStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.btnStart.text = getString(R.string.pause_btn)
                    binding.btnSkip.visibility = View.VISIBLE
                    binding.btnReset.isEnabled = true
                    binding.infoText.visibility = View.VISIBLE
                    myNotification.show("Timer Started")
                } else {
                    binding.btnStart.text = getString(R.string.start_btn)
                    binding.btnSkip.visibility = View.INVISIBLE
                    binding.infoText.visibility = View.INVISIBLE
                }
            }
        })

        timerViewModel.pauseTimerStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.btnStart.text = getString(R.string.resume_btn)
                } else {
                    binding.btnStart.text = getString(R.string.pause_btn)
                }
            }
        })

        timerViewModel.timerString.observe(viewLifecycleOwner, Observer { timerText ->
            timerText?.let {
                binding.timerText.text = timerText
            }
        })

        timerViewModel.resetTimerStatus.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding.btnReset.isEnabled = false
                    timerViewModel.resetTimerCompleted()
                }
            }
        })

        timerViewModel.infoText.observe(viewLifecycleOwner, Observer { info ->
            info?.let {
                when (info) {
                    "Work" -> binding.infoText.text = getString(R.string.work)
                    "Short Break" -> binding.infoText.text = getString(R.string.short_break)
                    "Long Break" -> binding.infoText.text = getString(R.string.long_break)
                }
            }
        })

        timerViewModel.vibration.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibe?.vibrate(VibrationEffect.createOneShot(500L, 255))
                        timerViewModel.vibrationCompleted()
                    }
                }
            }
        })


        binding.constraintLayout.keepScreenOn = timerViewModel.isKeepScreenOn.value!!

        return binding.root
    }

    private fun setKeepScreenOn() {
        if (settingPref.getBoolean(getString(R.string.key_keep_screen_on), false)) {
            timerViewModel.setKeepScreenOn()
        } else {
            timerViewModel.setKeepScreenOff()
        }
    }

    private fun setTime() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        timerViewModel.setWorkTime(
            sharedPreferences.getString(
                getString(R.string.key_work_time),
                "1500000"
            )!!.toLong()
        )

        timerViewModel.setShortBreak(
            sharedPreferences.getString(
                getString(R.string.key_short_break),
                "300000"
            )!!.toLong()
        )

        timerViewModel.setLongBreak(
            sharedPreferences.getString(
                getString(R.string.key_long_break),
                "900000"
            )!!.toLong()
        )

    }
}