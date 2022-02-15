package com.bbd.github_repo.presentation.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bbd.github_repo.R
import com.bbd.github_repo.data.source.enums.SearchInputAction
import com.bbd.github_repo.databinding.FragmentSearchUserBinding
import com.bbd.github_repo.domain.entity.UserOverviewEntity
import com.bbd.github_repo.presentation.adapter.UsersAdapter
import com.bbd.github_repo.presentation.base.BaseFragment
import com.bbd.github_repo.presentation.viewmodel.SearchUsersViewModel
import com.bbd.github_repo.util.isWhiteSpace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchUsersFragment : BaseFragment() {

    private val TAG = SearchUsersFragment::class.java.name

    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchUsersViewModel by viewModels()

    private lateinit var usersAdapter: UsersAdapter

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
        setupListener()
        observeChanges()
        setupRecyclerView()
    }

    private fun setupListener() {
        binding.searchInputEt.requestFocus()
        binding.searchInputEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d(TAG, "afterTextChanged: ${p0.toString()}")
                if (p0.toString().isWhiteSpace()) {
                    binding.searchInputEt.setText(p0.toString().replace(" ", ""))
                    binding.searchInputEt.setSelection(binding.searchInputEt.text.length)

                    return
                }
                viewModel.search(p0.toString())
            }

        })

        binding.searchInputIv.setOnClickListener {

            viewModel.getLastAction().apply {
                when (this) {
                    SearchInputAction.VOICE -> launchSpeechToText()
                    SearchInputAction.CLEAR -> {
                        binding.searchInputEt.setText("")
                        usersAdapter.updateList(arrayListOf())
                    }
                }
            }
        }
    }

    private fun observeChanges() {
        viewModel.observeSearchResult().observe(this, {
            when {
                it.isLoading() -> {
                    Log.d(TAG, "loading... ")
                    showOrHideLoading(true)
                    showOrHideUsersNotFound(false)
                }
                it.isSuccess() -> {
                    Log.d(TAG, "success: List size : ${it.getData().userOverviews.size}")
                    showOrHideLoading(false)
                    if (it.getData().userOverviews.isEmpty()) {
                        showOrHideUsersNotFound(true)
                    }
                    usersAdapter.updateList(it.getData().userOverviews)
                }
                it.isError() -> {
                    Log.e(TAG, "error: ${it.getErrorMessage().throwable}")
                    showOrHideLoading(false)
                    showErrorMessage(binding.searchInputEt, localException = it.getErrorMessage())
                }
            }
        })

        viewModel.observeInputChanges().observe(this, {
            when (it) {
                SearchInputAction.VOICE -> {
                    usersAdapter.updateList(arrayListOf())
                    binding.searchInputIv.setImageResource(R.drawable.ic_baseline_keyboard_voice_24)

                }
                SearchInputAction.CLEAR -> binding.searchInputIv.setImageResource(R.drawable.ic_baseline_close_24)
            }
        })
    }


    private fun setupRecyclerView() {
        Log.d(TAG, "setupRecyclerView: ")
        binding.usersRv.apply {
            usersAdapter = UsersAdapter(onItemClick = ::onItemClicked)
            adapter = usersAdapter
        }
    }

    private fun onItemClicked(userOverviewEntity: UserOverviewEntity) {
        hideKeyboard(binding.searchInputEt)
        Navigation.findNavController(binding.root).navigate(
            SearchUsersFragmentDirections.actionSearchUsersFragmentToUserDetailsFragment(
                userOverviewEntity.userName
            )
        )
    }


    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.let {
                    it.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let { result ->
                        binding.searchInputEt.setText(result[0] ?: "")
                    }
                }
            }
        }


    private fun launchSpeechToText() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en")
        resultLauncher.launch(intent)
    }

    private fun showOrHideLoading(isLoading: Boolean) =
        if (isLoading) binding.searchLoading.visibility =
            View.VISIBLE else binding.searchLoading.visibility = View.INVISIBLE

    private fun showOrHideUsersNotFound(show: Boolean) {
        if (show) binding.notUsersFoundTv.visibility =
            View.VISIBLE else binding.notUsersFoundTv.visibility = View.INVISIBLE
    }
}