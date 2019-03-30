package alexenriquezc.info.prueba.models

import com.google.gson.annotations.SerializedName

open class Product {
    var id: String = ""
    var name: String = ""
    var description: String =""
    var price: Float = 0.0f
    @SerializedName("category_id")
    var categoryId: Int = 0
    @SerializedName("category_name")
    var categoryName: String = ""
}