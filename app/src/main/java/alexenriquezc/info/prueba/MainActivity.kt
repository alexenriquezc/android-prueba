package alexenriquezc.info.prueba

import alexenriquezc.info.prueba.adapters.ProductsAdapter
import alexenriquezc.info.prueba.interfaces.IProduct
import alexenriquezc.info.prueba.models.Product
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var products:List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportActionBar!!.title = "Products"
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val products = arrayListOf<Product>()
        val product = Product()
        product.name ="A"
        product.category_id = 2
        product.category_name = "A"
        product.description="AB"
        product.price = 255f
        products.add(product)


        val productsAdapter = ProductsAdapter(this, R.layout.list_item, products)
        product_list.adapter = productsAdapter


        //loadProducts()
    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // TODO Auto-generated method stub
//        val id = item.itemId
//        if (id == android.R.id.home) {
//            finish()
//        }
//        return super.onOptionsItemSelected(item)
//    }

    companion object {
        fun productsService(): IProduct {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(IProduct::class.java)
        }
    }


    fun loadProducts() {

        val service = productsService()
        val request = service.getProducts()
        request.enqueue(object : Callback<List<Product>> {

            private var result: List<Product>? = null

            override fun onResponse(call: Call<List<Product>>?, response: Response<List<Product>>?) {
                if (response!!.isSuccessful) {
                    result = null
                    result = response.body()

                }
            }

            override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {
                t!!.printStackTrace()
            }
        })
    }
}
