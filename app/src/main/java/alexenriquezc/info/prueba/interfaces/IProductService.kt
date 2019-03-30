package alexenriquezc.info.prueba.interfaces

import alexenriquezc.info.prueba.models.Product
import alexenriquezc.info.prueba.models.ProductList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IProductService {
    @GET("product/read.php")
    fun  getProducts(): Call<ProductList>

    @POST("product/create.php")
    fun sendProduct(@Body product: Product): Call<Boolean>
}