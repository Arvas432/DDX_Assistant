package com.example.ddxassistant

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.ddxassistant.databinding.FragmentFeaturesBinding

class FeaturesFragment : BindingFragment<FragmentFeaturesBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFeaturesBinding {
        return FragmentFeaturesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).showSystemUI()
        setExerciseConstructorText()
        binding.carousel.setAdapter(object: Carousel.Adapter{
            override fun count(): Int {
                return 3
            }

            override fun populate(view: View?, index: Int) {

            }

            override fun onNewItem(index: Int) {
                renderScreenState(index)
            }
        }
        )
        binding.carouselInfoLayout.setOnClickListener {
            if(binding.carousel.currentIndex!=2){
                val currentCarouselIndex = binding.carousel.currentIndex
                binding.carousel.transitionToIndex(currentCarouselIndex + 1, 0)
                renderScreenState(currentCarouselIndex + 1)
            }
        }
        binding.startBtn.setOnClickListener{
            findNavController().navigate(R.id.action_featuresFragment_to_roleChoiceFragment)
        }
    }

    private fun setExerciseConstructorText(){
        val text1 = SpannableString("Составляй свои\n")
        val text2 = SpannableString("тренировки\n")
        val text3 = SpannableString("с помощью удобного\nконструктора")
        text1.setSpan(ForegroundColorSpan(resources.getColor(R.color.main_dark_purple)), 0, text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text2.setSpan(ForegroundColorSpan(resources.getColor(R.color.main_light_purple)), 0, text2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text2.setSpan(StyleSpan(Typeface.BOLD), 0, text2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text3.setSpan(ForegroundColorSpan(resources.getColor(R.color.main_dark_purple)), 0, text3.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.featureTextTv.text = text1
        binding.featureTextTv.append(text2)
        binding.featureTextTv.append(text3)
    }
    private fun setCoachChatText(){
        val text1 = SpannableString("Советуйся ")
        val text2 = SpannableString("с тренером\nв любое время")
        text1.setSpan(ForegroundColorSpan(resources.getColor(R.color.main_light_purple)), 0, text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text1.setSpan(StyleSpan(Typeface.BOLD), 0, text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text2.setSpan(ForegroundColorSpan(resources.getColor(R.color.main_dark_purple)), 0, text2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.featureTextTv.text = text1
        binding.featureTextTv.append(text2)
    }
    private fun setProgressText(){
        val text1 = SpannableString("Следи за своим\n")
        val text2 = SpannableString("прогрессом")
        text1.setSpan(ForegroundColorSpan(resources.getColor(R.color.main_light_purple)), 0, text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text1.setSpan(StyleSpan(Typeface.BOLD), 0, text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text2.setSpan(ForegroundColorSpan(resources.getColor(R.color.main_dark_purple)), 0, text2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.featureTextTv.text = text1
        binding.featureTextTv.append(text2)
    }
    private fun renderScreenState(state: Int){
        when(state){
            0->{
                setExerciseConstructorText()
                binding.startBtn.isVisible = false
                binding.dots.setImageResource(R.drawable.dots_1)
            }
            1->{
                setCoachChatText()
                binding.startBtn.isVisible = false
                binding.dots.setImageResource(R.drawable.dots_2)
            }
            2->{
                setProgressText()
                binding.startBtn.isVisible = true
                binding.dots.setImageResource(R.drawable.dots_3)
            }
        }
    }
}