package com.abc.bmicalc2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;

public class activity_bmiinformation extends AppCompatActivity {

    private int f = 0;
    private String selected1, gender, selectedAge, selectedWeight, pounds, kg, cm, inch, feet;
    private TextView age, height, weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiinformation);

        // Retrieve data from intent
        Intent intent = getIntent();
        selected1 = intent.getStringExtra("selected1");
        gender = intent.getStringExtra("gender");
        selectedAge = intent.getStringExtra("selectedAge");
        pounds = intent.getStringExtra("pounds");
        kg = intent.getStringExtra("kg");
        cm = intent.getStringExtra("cm");
        inch = intent.getStringExtra("inch");
        feet = intent.getStringExtra("feet");

        // Debugging output
        System.out.println(selected1);
        System.out.println(gender);
        System.out.println(selectedAge);
        System.out.println(kg);
        System.out.println(pounds);
        System.out.println(cm);
        System.out.println(inch);
        System.out.println(feet);

        // Set up TextViews
        age = findViewById(R.id.age);
        age.setText(selectedAge);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);

        if (selected1.equals("KG")) {
            height.setText(cm);
        } else {
            height.setText("Feet: " + feet + "\nInch: " + inch);
        }

        if (kg != null) {
            weight.setText("KG: " + kg);
        } else {
            weight.setText("Pounds: " + pounds);
        }

        // Set up animations
        LottieAnimationView loadingAnimation = findViewById(R.id.loadinginformation);
        TextView calBmi = findViewById(R.id.calbmi);
        CardView ageCard = findViewById(R.id.agecard);
        CardView heightCard = findViewById(R.id.heightcard);
        CardView weightCard = findViewById(R.id.weightcard);

        Animation topToDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidefromtoptobottom);
        topToDown.setDuration(2000);
        loadingAnimation.setAnimation(topToDown);
        calBmi.startAnimation(topToDown);

        Animation leftToRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_left);
        leftToRight.setDuration(2000);
        ageCard.setAnimation(leftToRight);

        Animation rightToLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        rightToLeft.setDuration(2000);
        heightCard.setAnimation(rightToLeft);
        weightCard.setAnimation(rightToLeft);

        // Delay to next activity
        new Handler().postDelayed(() -> {
            double heightInMeters;
            if (cm != null) {
                heightInMeters = Double.parseDouble(cm) / 100;
            } else {
                double totalInches = Double.parseDouble(feet) * 12 + Double.parseDouble(inch);
                heightInMeters = totalInches * 0.0254;
            }

            double weightInKg;
            if (kg != null) {
                weightInKg = Double.parseDouble(kg);
            } else {
                weightInKg = Double.parseDouble(pounds) * 0.453592;
            }

            double bmiValue = weightInKg / (heightInMeters * heightInMeters);

            Intent intent1 = new Intent(getApplicationContext(), activity_bmiresult.class);
            intent1.putExtra("bmi", bmiValue);
            intent1.putExtra("selected1", selected1);
            intent1.putExtra("selectedAge", selectedAge);
            intent1.putExtra("kg", kg);
            intent1.putExtra("pounds", pounds);
            intent1.putExtra("gender", gender);
            intent1.putExtra("cm", cm);
            intent1.putExtra("inch", inch);
            intent1.putExtra("feet", feet);
            startActivity(intent1);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            f = 1;
            finish();
        }, 4000);
    }

    @Override
    public void finish() {
        super.finish();
        if (f == 0) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
        }
    }
}
