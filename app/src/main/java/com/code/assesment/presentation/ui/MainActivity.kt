package com.code.assesment.presentation.ui

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat.collapseActionView
import androidx.lifecycle.ViewModelProvider
import com.code.assesment.R
import com.code.assesment.common.Resources
import com.code.assesment.presentation.adapter.AnimeAdapter
import com.code.assesment.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter: AnimeAdapter by lazy {

        AnimeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Anime Search"
        initObservers()
        initViews()
    }

    private fun initViews() {
        anime_list.adapter = adapter
    }

    private fun initObservers() {
        viewModel.data.observe(this) { value ->
            when (value) {
                is Resources.Error -> {
                }
                is Resources.Loading -> {
                }
                is Resources.Success -> {
                    value.data?.let {
                        adapter.setItems(it.results)
                    }

                    showHideProgressBar(false)
                }
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            maxWidth = Integer.MAX_VALUE
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.getAnimes(query)
                    showHideProgressBar(true)

                    collapseActionView(menu.findItem(R.id.search))
                    return false
                }

            })

        }

        return super.onCreateOptionsMenu(menu)
    }

    fun showHideProgressBar(visibility: Boolean) {
        paginationProgressBar.visibility = if (visibility) View.VISIBLE else View.GONE

    }
}