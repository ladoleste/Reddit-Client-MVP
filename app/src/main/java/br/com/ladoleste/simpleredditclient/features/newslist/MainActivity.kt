package br.com.ladoleste.simpleredditclient.features.newslist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import br.com.ladoleste.simpleredditclient.R
import br.com.ladoleste.simpleredditclient.common.Category
import br.com.ladoleste.simpleredditclient.common.CustomApplication
import br.com.ladoleste.simpleredditclient.common.NewsItem
import br.com.ladoleste.simpleredditclient.common.Util.getErrorMessage
import br.com.ladoleste.simpleredditclient.features.comments.CommentsActivity
import br.com.ladoleste.simpleredditclient.features.common.ItemClick
import br.com.ladoleste.simpleredditclient.features.common.LoadingScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.inc_toolbar.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ItemClick, MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override var category: Category = Category.TOP
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var loadingScrollListener: LoadingScrollListener

    private val rvListing by lazy {
        val linearLayoutManager = LinearLayoutManager(this)
        rv_listing.layoutManager = linearLayoutManager
        loadingScrollListener = LoadingScrollListener({ presenter.loadNews(false) }, linearLayoutManager)
        rv_listing
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CustomApplication.component.inject(this)
        presenter.setView(this)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "${getString(R.string.app_title)} [$category]"
        swiperefresh.setOnRefreshListener {
            presenter.loadNews(true)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        category = when (item.itemId) {
            R.id.new_ -> Category.NEW
            R.id.top -> Category.TOP
            else -> Category.HOT
        }

        loading.visibility = View.VISIBLE
        presenter.loadNews(true)

        return true
    }

    override fun onItemClick(id: String, image: ImageView?) {
        val intent = Intent(this, CommentsActivity::class.java)
        intent.putExtra("id", id)

        if (image != null) {
            val options = ActivityOptions.makeSceneTransitionAnimation(this, image, "name")
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    private var lastAfter = ""

    override fun getAfter() = lastAfter

    override var loadingEnabled = false

    override fun showList(it: List<NewsItem>?) {
        loading.visibility = View.GONE

        rvListing.clearOnScrollListeners()
        if (loadingEnabled)
            rv_listing.addOnScrollListener(loadingScrollListener)

        supportActionBar?.title = "${getString(R.string.app_title)} [$category]"
        it?.let {
            swiperefresh.isRefreshing = false
            if (rvListing.adapter == null) {
                newsAdapter = NewsAdapter(it, this)
                rvListing.adapter = newsAdapter
                loadingScrollListener.loading = false
            } else {
                loadingScrollListener.loading = false
                newsAdapter.updateItems(it)
            }
        }
    }

    override fun showError(it: Throwable) {
        Timber.e(it)
        swiperefresh.isRefreshing = false
        loading.visibility = View.GONE
        Snackbar.make(root_view, getErrorMessage(it), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) { loading.visibility = View.VISIBLE; presenter.loadNews(false) }
                .show()
    }
}
