package com.searoth.adesa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.searoth.adesa.R
import com.searoth.adesa.common.BaseActivity
import com.searoth.adesa.databinding.ActivityMainBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    private val groupAdapter = GroupAdapter<ViewHolder>()
    lateinit var layoutBinding: ActivityMainBinding
    private lateinit var groupLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main).apply {
            activityViewModel = mainActivityViewModel
        }

        mainActivityViewModel.actionTalkToActivity.observe(this) {
            showSnackBar(it)
        }

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        setupAdapter()
    }

    fun setupAdapter(){
        groupAdapter.apply {
            spanCount = 12
        }
        groupLayoutManager = GridLayoutManager(this, groupAdapter.spanCount).apply {
            spanSizeLookup = groupAdapter.spanSizeLookup
        }
        rv_main.apply {
            layoutManager = groupLayoutManager
            adapter = groupAdapter

            addOnScrollListener(object: InfiniteScrollListener(groupLayoutManager) {
                override fun onLoadMore(current_page: Int) {
                    mainActivityViewModel.getPictureOfTheDay("2019-02-12")
                }
            })
        }
        groupAdapter.add(mainActivityViewModel.mainSection)
    }
}
