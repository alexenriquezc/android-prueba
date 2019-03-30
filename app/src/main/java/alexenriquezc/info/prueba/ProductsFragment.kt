package alexenriquezc.info.prueba


import alexenriquezc.info.prueba.Services.ApplicationService
import alexenriquezc.info.prueba.adapters.ProductsAdapter
import alexenriquezc.info.prueba.models.Product
import alexenriquezc.info.prueba.models.ProductList
import alexenriquezc.info.prueba.utils.AnimationHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_products.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private lateinit var bar: BottomAppBar
    private lateinit var products:MutableList<Product>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fab = MainActivity.getFab()
        bar = MainActivity.getBottomBar()

        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadProducts()
        //AnimationHelper.loadBounceAnimation(this.context!!, fab, 0.2, 15.00)
        toNewProduct(view)
    }


    private fun toNewProduct(view:View){
        fab.setOnClickListener{
            fab.setImageResource(R.drawable.ic_send_24dp)
            bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END

            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.to_newProduct)
        }
    }


    private fun loadProducts() {

        val service = ApplicationService.productsService()
        val request = service.getProducts()
        request.enqueue(object : Callback<ProductList> {

            override fun onResponse(call: Call<ProductList>?, response: Response<ProductList>?) {
                if (response!!.isSuccessful) {
                    products = response.body()!!.products.toMutableList()
                    val adapter = ProductsAdapter(products, this@ProductsFragment.context!!)
                    adapter.notifyDataSetChanged()
                    product_list.layoutManager = LinearLayoutManager(this@ProductsFragment.context)
                    product_list.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ProductList>?, t: Throwable?) {
                t!!.printStackTrace()
            }
        })
    }
}
