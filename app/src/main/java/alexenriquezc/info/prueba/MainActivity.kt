package alexenriquezc.info.prueba

import alexenriquezc.info.prueba.adapters.ProductsAdapter
import alexenriquezc.info.prueba.interfaces.IProduct
import alexenriquezc.info.prueba.models.Product
import alexenriquezc.info.prueba.models.ProductList
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var products:MutableList<Product>

    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        add_product.setOnClickListener {
            val newProductIntent = Intent(this, NewProduct::class.java)
            startActivity(newProductIntent)
        }


        loadProducts()
    }


    companion object {
        private lateinit var instance: MainActivity

        fun productsService(): IProduct {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(IProduct::class.java)
        }


        fun addNewItem(product: Product){
            //instance.loadProducts()
            instance.products.add(product)
        }
    }


    fun loadProducts() {

        val service = productsService()
        val request = service.getProducts()
        request.enqueue(object : Callback<ProductList> {

            override fun onResponse(call: Call<ProductList>?, response: Response<ProductList>?) {
                if (response!!.isSuccessful) {
                    products = response.body()!!.products.toMutableList()
                    val adapter =ProductsAdapter(products, this@MainActivity)
                    adapter.notifyDataSetChanged()
                    product_list.layoutManager = LinearLayoutManager(this@MainActivity)
                    product_list.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ProductList>?, t: Throwable?) {
                t!!.printStackTrace()
            }
        })
    }
}
