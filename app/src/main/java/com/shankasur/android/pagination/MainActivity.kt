package com.shankasur.android.pagination

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shankasur.android.pagination.adapter.CardListingAdapter
import com.shankasur.android.pagination.adapter.CardListingBaseAdapter
import com.shankasur.android.pagination.base.BaseActivity
import com.shankasur.android.pagination.data.DataRepository
import com.shankasur.android.pagination.data.SampleData
import kotlin.properties.Delegates

class MainActivity : BaseActivity(), CardListingContract.View {
    lateinit var presenter: CardListingPresenter
    lateinit var pagingRecyclerView: RecyclerView
    private var adapter: CardListingBaseAdapter<*>? = null
    val hasFABorBottomBar = false
    private var isLast = false
    private lateinit var swipeToRefresh: SwipeRefreshLayout
    private var isLoading: Boolean by Delegates.observable(false) { property, old, new ->
        try {
            swipeToRefresh.isRefreshing = new
        } catch (e: UninitializedPropertyAccessException) {
            //log exception here
        }
    }
    private val limit = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init presenter
        val repo = DataRepository()
        presenter = CardListingPresenter(this, repo)

        pagingRecyclerView = findViewById(R.id.pagingRecyclerView)
        swipeToRefresh = findViewById(R.id.swipe_card_listing)
        swipeToRefresh.setOnRefreshListener {
            refreshCurrentPage()
        }
        pagingRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                if (hasFABorBottomBar) {
                    //show/hide here
                }
                val visibleItemCount = layoutManager?.childCount ?: -1
                val totalItemCount = layoutManager?.itemCount ?: -1
                val pastVisibleItems =
                    (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (pastVisibleItems + visibleItemCount >= totalItemCount && !isLoading && !isLast) {
                    checkInternetAndGetCards()
                }
            }
        })
    }

    override fun getProgressView(): View {
        TODO("Not yet implemented")
    }

    private fun refreshCurrentPage() {
        if (isLoading.not()) {
            adapter = null
            isLast = false
            checkInternetAndGetCards()
        }
    }

    private fun checkInternetAndGetCards() {
        if (checkIfNetworkAvailable()) {
//            presenter.getAmcCards(adapter?.itemCount ?: 0, limit, anyFilterValue)
            //and in success update the cards
        }
    }


    override fun displayCards(cards: List<SampleData>, totalItemCount: Int) {
        swipeToRefresh.isRefreshing = false
//        noCardsView.visibility = View.GONE
        pagingRecyclerView.visibility = View.VISIBLE

        if (adapter == null) {
            val data = mutableListOf<Any>()
            adapter = CardListingAdapter(this, data)
            pagingRecyclerView.adapter = adapter
        } else {
            val data = mutableListOf<Any>()
            (adapter as CardListingAdapter).updateList(data)
        }

        if (adapter!!.itemCount >= totalItemCount) {
            isLast = true
        }
    }

    override fun noCardsFound() {
        pagingRecyclerView.adapter = null
        swipeToRefresh.isRefreshing = false
        pagingRecyclerView.visibility = View.GONE
//        noCardsView.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
//        noCardsView.visibility = View.VISIBLE
//        noCardsView.text = message
    }

    override fun showProgressIndicator() {
        super.showProgressBar()
    }

    override fun dismissProgressIndicator() {
        super.dismissProgressBar()
    }

    override fun showGenericError(errorMsg: String) {
        displayError(errorMsg)
    }
}