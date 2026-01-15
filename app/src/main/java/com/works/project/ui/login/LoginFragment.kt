package com.works.project.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.works.project.R
import com.works.project.databinding.FragmentLoginBinding
import com.works.project.ui.models.UserLoginModel
import kotlin.getValue

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserLoginModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupClickListeners()
    }


    private fun setupObservers() {
        viewModel.userNumber.observe(viewLifecycleOwner) {
            binding.lFTxtData.text = it.toString()
        }
    }

    private fun setupClickListeners() {
        binding.lFBtnData.setOnClickListener {
            // viewModel.actionPlus()
        }

    }



}