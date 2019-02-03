package com.github.andrei1993ak.mentoring.koin.ui.details

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.andrei1993ak.mentoring.koin.R
import com.github.andrei1993ak.mentoring.koin.databinding.ActivityDetailBinding
import com.github.andrei1993ak.mentoring.koin.repository.User
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class UserDetailsActivity : AppCompatActivity(), DetailContract.View {

    override fun displayUser(user: User) {
        binding.user = user
    }

    override val presenter: DetailContract.Presenter by inject { parametersOf(this) }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        presenter.getDetail(intent)
    }


    override fun onDestroy() {
        super.onDestroy()

        presenter.stop()
    }
}
