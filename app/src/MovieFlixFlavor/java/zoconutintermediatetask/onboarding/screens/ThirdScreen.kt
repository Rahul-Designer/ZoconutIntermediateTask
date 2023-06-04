package zoconutintermediatetask.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.zoconutintermediatetask.R
import com.example.zoconutintermediatetask.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {
    private lateinit var binding : FragmentThirdScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third_screen, container, false)


        binding.skipBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeActivity)
            onBoardingFinished()
        }

        binding.finishBtn.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeActivity)
            onBoardingFinished()
        }



        return binding.root
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }
}