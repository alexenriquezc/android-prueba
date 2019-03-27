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


open class ProductsAdapter(
    context: Context, @LayoutRes private val layoutResource: Int,
    private val products: List<Product>
) :
    ArrayAdapter<Product>(context, layoutResource, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val product = getItem(position)
//        if(convertView == null){
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        view.findViewById<TextView>(R.id.product_name).text = product.name

        view.findViewById<TextView>(R.id.product_price).text = "$${product.price}"

        return view
        //return super.getView(position, convertView, parent)
    }
}