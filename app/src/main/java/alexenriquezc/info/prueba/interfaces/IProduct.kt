package alexenriquezc.info.prueba.interfaces

import alexenriquezc.info.prueba.models.Product
import alexenriquezc.info.prueba.models.ProductList
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

open interface IProduct {
    @GET("product/read.php")
    //@GET("master/read.txt")
    fun  getProducts(): Call<ProductList>

    @POST("product/create.php")
    fun sendProduct(@Body  product: Product): Call<Boolean>
}