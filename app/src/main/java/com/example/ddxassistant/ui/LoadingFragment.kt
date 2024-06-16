package com.example.ddxassistant.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.BindingFragment
import com.example.ddxassistant.R
import com.example.ddxassistant.databinding.FragmentLoadingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoadingFragment : BindingFragment<FragmentLoadingBinding>() {

    private val animDuration = 500L

    private val waitTime = 500L

    private val moveDistanceDp = 15f
    private val viewModel by viewModel<LoadingViewModel>()

    private val handler = Handler(Looper.getMainLooper())
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoadingBinding {
        return FragmentLoadingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideSystemUI()
        val moveDistancePx = moveDistanceDp * resources.displayMetrics.density
        val moveUp = TranslateAnimation(0f, 0f, 0f, -moveDistancePx).apply {
            duration = animDuration
            fillAfter = true
            interpolator =AccelerateDecelerateInterpolator()
        }
        val moveDown = TranslateAnimation(0f, 0f, -moveDistancePx, 0f).apply {
            duration = animDuration
            fillAfter = true
            interpolator =AccelerateDecelerateInterpolator()
        }
        val animationListener = object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                handler.postDelayed({
                    if (animation == moveUp) {
                        binding.head.startAnimation(moveDown)
                    } else if (animation == moveDown) {
                        binding.head.startAnimation(moveUp)
                    }
                }, waitTime)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        }
        moveUp.setAnimationListener(animationListener)
        moveDown.setAnimationListener(animationListener)

        binding.head.startAnimation(moveUp)
        when(viewModel.checkFirstLaunch()){
            true ->  handler.postDelayed({findNavController().navigate(R.id.action_loadingFragment_to_welcomeFragment) }, 3000L)
            false -> handler.postDelayed({findNavController().navigate(R.id.action_loadingFragment_to_roleChoiceFragment) }, 3000L)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }

}