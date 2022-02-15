package com.bbd.github_repo.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bbd.github_repo.databinding.FragmentSearchUserBinding
import com.bbd.github_repo.presentation.base.BaseFragment
import com.bbd.github_repo.presentation.viewmodel.SearchUsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUsersFragment : BaseFragment() {

    private val TAG = SearchUsersFragment::class.java.name

    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchUsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
    }
}