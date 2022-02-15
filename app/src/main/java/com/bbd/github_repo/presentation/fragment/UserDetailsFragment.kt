package com.bbd.github_repo.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bbd.github_repo.databinding.FragmentUserDetailsBinding
import com.bbd.github_repo.presentation.base.BaseFragment
import com.bbd.github_repo.presentation.viewmodel.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment() {

    private val TAG = UserDetailsFragment::class.java.name

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")
        _binding = null
        super.onDestroyView()
    }
}