package net.digitapp.marketcompare.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.main_search_fragment.*
import net.digitapp.marketcompare.R
import net.digitapp.marketcompare.interfaces.GotoStoreClickListener
import net.digitapp.marketcompare.viewmodel.MainSearchViewModel
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import net.digitapp.marketcompare.adapter.ListItemAdapter


class MainSearchFragment : Fragment(), GotoStoreClickListener {
    private lateinit var adapter: ListItemAdapter

    override fun onGotoStoreButtonClicked(storeUrl: String) {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(storeUrl))
        if (i.resolveActivity(context!!.packageManager) != null) {
            startActivity(i)
        }
    }

    companion object {
        fun newInstance() = MainSearchFragment()
    }

    private lateinit var viewModel: MainSearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainSearchViewModel::class.java)
        adapter = ListItemAdapter(context!!, this)
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = adapter

        // perform set on query text listener event
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                progressBar.visibility = View.VISIBLE
                // do something on text submit
                viewModel.getProductLists(query).observe(activity!!, Observer {
                    progressBar.visibility = View.GONE
                    if (it != null) {
                        it.let {
                            if (it.productList!!.isNotEmpty())
                                adapter.addAll(it.productList!!)
                            else
                                recyclerView.visibility = View.GONE
                                txt_result.visibility = View.VISIBLE
                                txt_result.text = getString(R.string.empty_search)
                        }
                    }else{
                        recyclerView.visibility = View.GONE
                        txt_result.visibility = View.VISIBLE
                        txt_result.text = getString(R.string.null_search)
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // do something when text changes
                return false
            }
        })
    }
}
