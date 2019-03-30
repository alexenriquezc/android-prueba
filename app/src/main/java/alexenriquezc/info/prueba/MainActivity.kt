package alexenriquezc.info.prueba

import alexenriquezc.info.prueba.adapters.ProductsAdapter
import alexenriquezc.info.prueba.interfaces.IProductService
import alexenriquezc.info.prueba.models.Product
import alexenriquezc.info.prueba.models.ProductList
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.to_newProduct)
        }
        //NavigationUI.setupWithNavController(bar, Navigation.findNavController(this, R.id.root_fragment))
    }


    companion object {
        private lateinit var instance: MainActivity

        fun getFab(): FloatingActionButton{
            return instance.fab
        }

        fun getBottomBar(): BottomAppBar{
            return instance.bar
        }

        fun hideOnScroll(isHidden: Boolean){
            instance.bar.hideOnScroll = isHidden
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        fab.setImageResource(R.drawable.ic_add_24dp)
        bar.setNavigationIcon(R.drawable.ic_menu_24dp)
    }
}
