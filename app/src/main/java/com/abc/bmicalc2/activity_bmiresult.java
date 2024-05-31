package com.abc.bmicalc2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_bmiresult extends AppCompatActivity {

    private TextView bmiCategoryTextView, bmiValueTextView, idealWeightRangeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        // Retrieve BMI and other data from intent
        Intent intent = getIntent();
        double bmiValue = intent.getDoubleExtra("bmi", 0.0);
        String gender = intent.getStringExtra("gender");
        String cm = intent.getStringExtra("cm");
        String feet = intent.getStringExtra("feet");
        String inch = intent.getStringExtra("inch");

        // Set up TextViews
        bmiCategoryTextView = findViewById(R.id.bmiCategoryTextView);
        bmiValueTextView = findViewById(R.id.bmiValueTextView);
        idealWeightRangeTextView = findViewById(R.id.idealWeightRangeTextView);

        // Display BMI value
        bmiValueTextView.setText(String.format("%.2f", bmiValue));

        // Determine BMI category and display it
        String bmiCategory = getBMICategory(bmiValue);
        bmiCategoryTextView.setText(bmiCategory);
        bmiCategoryTextView.setTextColor(getResources().getColor(getBMICategoryColor(bmiCategory)));

        // Calculate and display ideal weight range
        String idealWeightRange = getIdealWeightRange(cm, feet, inch, gender);
        idealWeightRangeTextView.setText(idealWeightRange);
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    private int getBMICategoryColor(String category) {
        switch (category) {
            case "Underweight":
                return R.color.underweight;
            case "Normal weight":
                return R.color.normalweight;
            case "Overweight":
                return R.color.overweight;
            case "Obesity":
                return R.color.obesity;
            default:
                return R.color.black;
        }
    }

    private String getIdealWeightRange(String cm, String feet, String inch, String gender) {
        double heightInMeters;
        if (cm != null && !cm.isEmpty()) {
            heightInMeters = Double.parseDouble(cm) / 100;
        } else {
            double totalInches = Double.parseDouble(feet) * 12 + Double.parseDouble(inch);
            heightInMeters = totalInches * 0.0254;
        }

        // Calculate ideal weight range using BMI range for normal weight (18.5 to 24.9)
        double minWeight = 18.5 * heightInMeters * heightInMeters;
        double maxWeight = 24.9 * heightInMeters * heightInMeters;

        // Convert weights to kilograms and format the range
        return String.format("%.1f ~ %.1f", minWeight, maxWeight);
    }
}
