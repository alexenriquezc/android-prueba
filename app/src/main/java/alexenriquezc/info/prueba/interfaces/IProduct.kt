package alexenriquezc.info.prueba.interfaces

import alexenriquezc.info.prueba.models.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

open interface IProduct {
    @GET("/product/read.php")
    fun  getProducts(): Call<List<Product>>

    @POST("/product/create.php")
    fun sendProduct(@Body product: Product): Call<Boolean>
}