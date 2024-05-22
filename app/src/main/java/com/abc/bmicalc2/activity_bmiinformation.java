package com.abc.bmicalc2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class activity_bmiinformation extends AppCompatActivity {
    int f=0;
    String selected1,gender;
    String selectedAge, selectedWeight, selectedheight, pounds, kg, cm ,inch,feet;
    TextView age,height;
    protected void onCreate(Bundle savedInsatnceState){
        super.onCreate(savedInsatnceState);
        setContentView(R.layout.activity_bmiinformation);

        Intent intent=getIntent();
        selected1=intent.getStringExtra("selected1");
        gender=intent.getStringExtra("gender");
        selectedAge=intent.getStringExtra("selectedAge");
        pounds=intent.getStringExtra("pounds");
        kg=intent.getStringExtra("kg");
        cm=intent.getStringExtra("cm");
        inch=intent.getStringExtra("inch");
        feet=intent.getStringExtra("feet");

        System.out.println(selected1);
        System.out.println(gender);
        System.out.println(selectedAge);
        System.out.println(kg);
        System.out.println(pounds);
        System.out.println(cm);
        System.out.println(inch);
        System.out.println(feet);

        age=findViewById(R.id.age);
        age.setText(selectedAge);
        height=findViewById(R.id.height);

        if (selected1.equals("KG")){
            height.setText(cm);
        }else {
            height.setText("Feet : "+feet+"\n"+"Inch : "+inch);
        }
TextView weight=findViewById(R.id.weight);
        if (kg!=null){
            weight.setText("KG : "+ kg);
        }else {
            weight.setText("Pounds : "+pounds);

            LottieAnimationView loadinganimation=findViewById(R.id.loadinganimation);
            TextView calbmi=findViewById(R.id.calbmi);
            CardView agecard=findViewById(R.id.agecard);
            CardView heightcard=findViewById(R.id.heightcard);
            CardView weightcard=findViewById(R.id.weightcard);

            Animation toptodown= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slidefromtoptobottom);
            toptodown.setDuration(2000);
            loadinganimation.setAnimation(toptodown);
            calbmi.startAnimation(toptodown);

            Animation lefttoright= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_left);
            lefttoright.setDuration(2000);
            agecard.setAnimation(toptodown);

            Animation righttoleft= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
            righttoleft.setDuration(2000);
           heightcard.setAnimation(righttoleft);
           weightcard.setAnimation(righttoleft);

           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   Intent intent1=new Intent(getApplicationContext(),activity_bmiresult.class);
                   intent1.putExtra("selected1",selected1);
                   intent1.putExtra("selectedAge",selectedAge);
                   intent1.putExtra("kg",kg);
                   intent1.putExtra("pounds",pounds);
                   intent1.putExtra("gender",gender);
                   intent1.putExtra("cm",cm);
                   intent1.putExtra("inch",inch);
                   intent1.putExtra("feet",feet);
                  startActivity(intent1);
                  overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
                  f=1;
                  finish();
               }
           },4000);
        }


    }
    public void finish(){
        super.finish();
        if(f==0){
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right);

        }
    }
}