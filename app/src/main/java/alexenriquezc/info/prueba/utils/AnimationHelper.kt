package alexenriquezc.info.prueba.utils

import alexenriquezc.info.prueba.R
import alexenriquezc.info.prueba.interpolators.BounceInterpolator
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator

class AnimationHelper {
    companion object {
        fun loadBounceAnimation(context:Context, view: View, amplitude: Double, frequency: Double){
            val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)
            val interpolator = BounceInterpolator(amplitude, frequency)
            animation.interpolator = interpolator
            view.startAnimation(animation)
        }
    }
}