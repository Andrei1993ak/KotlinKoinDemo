package com.github.andrei1993ak.mentoring.koin.ui.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import com.github.andrei1993ak.mentoring.koin.R
import com.github.andrei1993ak.mentoring.koin.databinding.ActivityUserListBinding
import com.github.andrei1993ak.mentoring.koin.repository.User
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class UserListActivity : AppCompatActivity(), ListContract.View {

    private lateinit var binding: ActivityUserListBinding

    private lateinit var postsAdapter: UserAdapter

    override val presenter: ListContract.Presenter by inject { parametersOf(this) }

    override fun updateUsers(users: List<User>) {
        postsAdapter.updateUsers(users)
    }

    override fun showError(error: String) {
        Log.e(this.javaClass.simpleName, error)
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun removeUser(userId: Long) {
        (binding.adapter as UserAdapter).removeUser(userId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postsAdapter = UserAdapter(this, object : OnUserClickListener {
            override fun onUserClicked(user: User) {
                presenter.onUserItemClicked(user)
            }
        })

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                presenter.onIemSwipedToDelete(postsAdapter.getItemId(viewHolder.adapterPosition))
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.posts)

        presenter.getList()
    }


    override fun onDestroy() {
        super.onDestroy()

        presenter.stop()
    }
}
