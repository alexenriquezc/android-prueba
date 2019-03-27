package alexenriquezc.info.prueba.adapters

import alexenriquezc.info.prueba.R
import alexenriquezc.info.prueba.models.Product
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*


open class ProductsAdapter(val products: List<Product>, val context: Context): RecyclerView.Adapter<ProductViewHolder>() {
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder?.productName?.text = product.name
        holder?.productPrice?.text = "$%.2f".format(product.price)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }
}

class ProductViewHolder (view:View): RecyclerView.ViewHolder(view){
    val productName = view.product_name
    val productPrice = view.product_price


}