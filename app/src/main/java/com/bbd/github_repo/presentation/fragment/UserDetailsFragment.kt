package com.bbd.github_repo.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bbd.github_repo.R
import com.bbd.github_repo.databinding.FragmentUserDetailsBinding
import com.bbd.github_repo.domain.entity.UserDetailEntity
import com.bbd.github_repo.domain.entity.UserExtraInfo
import com.bbd.github_repo.presentation.adapter.UserExtraInfoAdapter
import com.bbd.github_repo.presentation.base.BaseFragment
import com.bbd.github_repo.presentation.viewmodel.UserDetailsViewModel
import com.bbd.github_repo.util.launchBrowser
import com.bbd.github_repo.util.toSimpleDate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment() {

    private val TAG = UserDetailsFragment::class.java.name

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserDetailsViewModel by viewModels()
    private val args: UserDetailsFragmentArgs by navArgs()

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
        setupToolbar()
        observeChanges()
    }

    private fun setupToolbar() {
        binding.userNameTv.text = args.userName
        binding.backIv.setOnClickListener { findNavController().popBackStack() }
    }

    private fun observeChanges() {
        viewModel.getUserDetails(args.userName).observe(this, {
            when {
                it.isLoading() -> {
                    Log.d(TAG, "loading... ")
                    showOrHideLoading(true)

                }
                it.isSuccess() -> {
                    Log.d(TAG, "success: ${it.getData()}")
                    showOrHideLoading(false)
                    showUserDetails(it.getData())
                }
                it.isError() -> {
                    Log.e(TAG, "error: ${it.getErrorMessage().throwable}")
                    showOrHideLoading(false)
                    showErrorMessage(binding.backIv, localException = it.getErrorMessage())
                }
            }
        })
    }


    private fun showUserDetails(details: UserDetailEntity) {

        binding.userDetailsRel.visibility = View.VISIBLE
        binding.bioSectionRel.visibility =
            if (details.bio.isNullOrEmpty()) View.GONE else View.VISIBLE

        Glide.with(requireContext()).load(details.userAvatarUrl)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.userAvatarIv)

        binding.fullNameTv.text = details.userName ?: details.login
        binding.followersCountTv.text = details.followers.toString()
        binding.followingCountCountTv.text = details.following.toString()

        binding.joinedDateTv.text = details.joinedDate.toSimpleDate()
        binding.lastActivityDateTv.text = details.lastActivityDate.toSimpleDate()
        binding.repositoriesCountTv.text = details.repos.toString()
        binding.repositoryHintTv.text =
            if (details.repos <= 1) getString(R.string.repository) else getString(R.string.repositories)

        details.bio?.let {
            binding.bioTv.text = it.trim()
        }

        binding.githubPageTv.setOnClickListener { requireActivity().launchBrowser(details.pageUrl) }

        setupExtraInfoList(viewModel.getUserExtraInfo(details))
    }

    private fun setupExtraInfoList(list: List<UserExtraInfo>) {
        binding.extraInfoRv.apply {
            adapter = UserExtraInfoAdapter(list)
        }
    }

    private fun showOrHideLoading(isLoading: Boolean) =
        if (isLoading) binding.searchLoading.visibility =
            View.VISIBLE else binding.searchLoading.visibility = View.INVISIBLE

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")
        _binding = null
        super.onDestroyView()
    }
}