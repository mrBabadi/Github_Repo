package com.bbd.github_repo.presentation.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bbd.github_repo.R
import com.bbd.github_repo.data.LocalException
import com.google.android.material.snackbar.Snackbar
import retrofit2.HttpException
import java.net.UnknownHostException

open class BaseFragment : Fragment() {

    fun showErrorMessage(view: View? = null, localException: LocalException) {
        var message = ""
        when (localException.throwable) {
            is HttpException -> {
                message = getHttpErrorMessage(localException.throwable.code())
            }
            is SecurityException -> {
                message = getString(R.string.permission_denied)
            }
            is UnknownHostException -> {
                message = getString(R.string.unresolve_host)
            }
            is NullPointerException -> {
                message = getString(R.string.something_bad_happened)
            }
        }
        view?.let {
            showSnackBarError(message, it)
        } ?: kotlin.run {
            showToastError(message)
        }
    }

    private fun getHttpErrorMessage(httpCode: Int): String {
        return when (httpCode) {
            403 -> getString(R.string.error_403)
            404 -> getString(R.string.error_404)
            else -> getString(R.string.error_not_defined)
        }
    }

    fun hideKeyboard(view: View?) {

        view?.let {
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    private fun showSnackBarError(message: String, view: View) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showToastError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }


}