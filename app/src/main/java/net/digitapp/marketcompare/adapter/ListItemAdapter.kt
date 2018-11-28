package net.digitapp.marketcompare.adapter

import android.content.Context
import android.support.design.button.MaterialButton
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.model_products.view.*
import net.digitapp.marketcompare.R
import net.digitapp.marketcompare.interfaces.GotoStoreClickListener
import net.digitapp.marketcompare.model.DataModel

class ListItemAdapter(var context: Context, var gotobtnListener: GotoStoreClickListener) :
    RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {
    val products = arrayListOf<DataModel>()
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.model_products, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(h: ViewHolder, p: Int) {
        h.displayTitle.text = products[p].itemTitle
        h.displayPrice.text = products[p].itemPrice
        h.btnGotoStore.text = "Buy from ${products[p].marketSource}"
        h.displayImage

        h.btnGotoStore.setOnClickListener {
            gotobtnListener.onGotoStoreButtonClicked(products[p].itemUrl)
        }
    }

    fun addAll(list: List<DataModel>) {
        for (result in list) {
            add(result)
        }
    }

    fun add(r: DataModel) {
        products.add(r)
        notifyItemInserted(products.size - 1)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val displayImage = view.findViewById<ImageView>(R.id.product_image)
        val displayTitle = view.findViewById<TextView>(R.id.product_title)
        val displayPrice = view.findViewById<TextView>(R.id.product_price)
        val btnGotoStore = view.findViewById<MaterialButton>(R.id.btn_goto_store)
    }
}