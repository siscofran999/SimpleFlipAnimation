package com.siscofran.simpleflipanimation

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            showDialogAnimation(isChecked)
        }
    }

    private fun showDialogAnimation(checked: Boolean) {
        val dialogBuilder = AlertDialog.Builder(this, R.style.Flip)
        val dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog_card, null)
        val dialog = dialogBuilder.create()
        dialog.setView(dialogLayout)

        val cardMan: AppCompatImageView = dialogLayout.findViewById(R.id.card_man)
        val cardFemale: AppCompatImageView = dialogLayout.findViewById(R.id.card_female)

        val mSetRightOut = AnimatorInflater.loadAnimator(this, R.animator.anim_flip1) as AnimatorSet
        val mSetLeftIn = AnimatorInflater.loadAnimator(this, R.animator.anim_flip2) as AnimatorSet
        dialog.setCancelable(false)
        if(checked){
            mSetRightOut.setTarget(cardMan)
            mSetLeftIn.setTarget(cardFemale)
            mSetRightOut.start()
            mSetLeftIn.start()
            mSetLeftIn.addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    dialog.dismiss()
                    super.onAnimationEnd(animation)
                }
            })
        }else{
            mSetRightOut.setTarget(cardFemale)
            mSetLeftIn.setTarget(cardMan)
            mSetRightOut.start()
            mSetLeftIn.start()
            mSetLeftIn.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    dialog.dismiss()
                    super.onAnimationEnd(animation)
                }
            })
        }

        dialog.show()
    }
}