package alexenriquezc.info.prueba.Services

import alexenriquezc.info.prueba.BuildConfig
import alexenriquezc.info.prueba.interfaces.IProductService
import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationService : Application() {

    companion object {
        fun productsService(): IProductService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(IProductService::class.java)
        }
    }
}