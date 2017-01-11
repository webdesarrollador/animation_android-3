package com.webdesarrollador.animationtutorial3;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout fondo = (RelativeLayout) findViewById(R.id.activity_main);

        //Asignamos al RelativeLayout la animación de cambio de fondo -- property animation
        ObjectAnimator oa = (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.animation_background);
        oa.setTarget(fondo);
        oa.start();

        //Animación vectorial para las nubes
        ImageView nube1 = (ImageView) findViewById( R.id.nube1 );
        ImageView nube2 = (ImageView) findViewById( R.id.nube2 );

        Drawable drawable = nube1.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        drawable = nube2.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        //Animación tween para los rayos
        final ImageView rayo1 = (ImageView) findViewById(R.id.rayo1);
        final Animation animation_rayo1 = AnimationUtils.loadAnimation(this, R.anim.animation_rayo1);

        final ImageView rayo2 = (ImageView) findViewById(R.id.rayo2);
        final Animation animation_rayo2 = AnimationUtils.loadAnimation(this, R.anim.animation_rayo2);

        //Animación por frames del chico
        ImageView chico = (ImageView) findViewById(R.id.animation_chico);
        final AnimationDrawable chicoAnimation = (AnimationDrawable) chico.getBackground();

        //Property animation del chico
        final ObjectAnimator oa_chico = (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.animation_movimiento_chico);
        oa_chico.setTarget(chico);

        //Cuando acabe la animación del fondo comenzamos la de los rayos y la del chico
        oa.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                rayo1.startAnimation(animation_rayo1);
                rayo2.startAnimation(animation_rayo2);
                chicoAnimation.start();
                oa_chico.start();
            }
        });


    }
}
