package alexenriquezc.info.prueba


import alexenriquezc.info.prueba.Services.ApplicationService
import alexenriquezc.info.prueba.models.Product
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_new_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewProductFragment : Fragment() {

    private lateinit var fab: FloatingActionButton
    private lateinit var bar: BottomAppBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fab = MainActivity.getFab()
        bar = MainActivity.getBottomBar()

        return inflater.inflate(R.layout.fragment_new_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //AnimationHelper.loadBounceAnimation(this.context!!, fab, 0.2, 15.00)
        sendProduct(view)
    }

    private fun generateProduct(): Product?{
        var errorsCounter = 0
        val product = Product()

        product.categoryName = ""
        product.categoryId = 1

        product.description = product_description.text.toString()

        if(!isNullOrEmpty(product_id.text.toString()) && product_id.text.toString().toInt() > 0)
        {
            errorsCounter = decrementErrors(errorsCounter)
            product.id = product_id.text.toString()
        }

        else
        {
            errorsCounter = incrementErrors(errorsCounter)
            product_id.error = "The field can not be empty."
        }

        if(!isNullOrEmpty(product_name.text.toString()))
        {
            errorsCounter = decrementErrors(errorsCounter)
            product.name = product_name.text.toString()
        }
        else
        {
            errorsCounter = incrementErrors(errorsCounter)
            product_name.error = "The field can not be empty."
        }

        if(!isNullOrEmpty(product_price.text.toString()))
        {
            product.price = product_price.text.toString().toFloat()
        }
        else
        {
            product.price = 0.00f
        }

        if(errorsCounter > 0)
            return null
        else
            return product
    }

    private fun incrementErrors(_counter: Int): Int{
        var counter = _counter
        return counter ++
    }

    private fun decrementErrors(_counter: Int): Int{
        var counter = _counter
        if(counter < 1 )
            return 0
        else
            return counter --
    }


    private fun isNullOrEmpty(str: String?): Boolean {
        if (str != null && !str.isEmpty())
            return false
        return true
    }

    private fun showMessage(view: View, message: String){
        val marginSide = 0
        val marginBottom = 100


        val snackbar = Snackbar.make(view,message, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as CoordinatorLayout.LayoutParams

        params.setMargins(
            params.leftMargin + marginSide,
            params.topMargin,
            params.rightMargin + marginSide,
            params.bottomMargin + marginBottom
        )

        snackbarView.layoutParams = params
        snackbar.show()
    }


    private fun sendProduct(view: View){
        fab.setOnClickListener {
            val product = generateProduct()
            val service = ApplicationService.productsService()
            val request = service.sendProduct(product!!)
            if(product != null){
                request.enqueue(object : Callback<Boolean> {

                    override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
                        if (response!!.isSuccessful) {
                            if(response.body() == true){

                                showMessage(fab, "Product Saved.")

                                fab.setImageResource(R.drawable.ic_add_24dp)
                                bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

                                val navController = Navigation.findNavController(view)
                                navController.popBackStack()
                                //onBackPressed()
                            }
                        }
                        else{
                            showMessage(fab, response.message())
                        }
                    }

                    override fun onFailure(call: Call<Boolean>?, t: Throwable?) {
                        t!!.printStackTrace()
                        showMessage(fab, t.message!!)

                    }
                })
            }
        }
    }


}
