package alexenriquezc.info.prueba.models

import com.google.gson.annotations.SerializedName

open class ProductList {
    @SerializedName("records")
    lateinit var products:List<Product>
}