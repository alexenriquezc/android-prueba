package alexenriquezc.info.prueba.interpolators

import android.view.animation.Interpolator
import kotlin.concurrent.timer

class BounceInterpolator(var amplitude:Double = 1.00, var frequency: Double = 10.00): Interpolator {

    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.pow(Math.E, -time/ amplitude) * Math.cos(frequency * time) +1).toFloat()
    }
}