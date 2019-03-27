package alexenriquezc.info.prueba

import alexenriquezc.info.prueba.adapters.ProductsAdapter
import alexenriquezc.info.prueba.models.Product
import alexenriquezc.info.prueba.models.ProductList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NewProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_product)

        save_product.setOnClickListener {
            val product: Product = Product()
            product.id = product_id.text.toString()
            product.name = product_name.text.toString()
            product.price = product_price.text.toString()
            product.category_name = product_category_name.text.toString()
            product.category_id = "2"
            product.created = Date()
            product.description = product_description.text.toString()

            val service = MainActivity.productsService()
            val request = service.sendProduct(product)
            request.enqueue(object : Callback<Boolean> {

                override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
                    if (response!!.isSuccessful) {
                        if(response.body() == true){
                            Toast.makeText(this@NewProduct, "Product Saved", Toast.LENGTH_LONG).show()
                            MainActivity.instance.loadProducts()
                            onBackPressed()
                        }
                    }
                }

                override fun onFailure(call: Call<Boolean>?, t: Throwable?) {
                    t!!.printStackTrace()
                }
            })


            //MainActivity.addNewItem(product)
            //onBackPressed()
        }
    }
}
