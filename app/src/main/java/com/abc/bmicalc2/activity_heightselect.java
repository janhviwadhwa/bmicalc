package com.abc.bmicalc2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityHeightSelect extends AppCompatActivity {
    String selected1, gender;
    Integer selectedAge, selectedWeight, pounds, kg, cm, inch, feet;
    List<Integer> inchList, feetList, weightList;
    TextView selectedInch, selectedFeet, selectedCm;
    int f = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heightselect);

        selectedInch = findViewById(R.id.selectedinch);
        selectedFeet = findViewById(R.id.selectedfeet);
        selectedCm = findViewById(R.id.selectedcm);

        Intent intent = getIntent();
        selected1 = intent.getStringExtra("selected1");
        selectedAge = Integer.parseInt(intent.getStringExtra("selectedAge"));
        selectedWeight = Integer.parseInt(intent.getStringExtra("selectedWeight"));
        gender = intent.getStringExtra("gender");

        ImageView avatar = findViewById(R.id.avatar);
        if (gender.equals("male")) {
            avatar.setImageResource(R.drawable.standingmale);
        } else {
            avatar.setImageResource(R.drawable.standingfemale);
        }

        LinearLayout llCm = findViewById(R.id.llcm);
        LinearLayout llFt = findViewById(R.id.llft);

        if (selected1.equals("KG")) {
            llCm.setVisibility(View.VISIBLE);
            llFt.setVisibility(View.GONE);
            kg = selectedWeight;
        } else {
            llCm.setVisibility(View.GONE);
            llFt.setVisibility(View.VISIBLE);
            pounds = selectedWeight;
        }

        // Setup RecyclerView for height in cm
        setupRecyclerView(R.id.rcheight, weightList, 1, 300, this::updateCenteredPositionCm);

        // Setup RecyclerView for height in feet
        setupRecyclerView(R.id.rpfeet, feetList, 1, 11, this::updateCenteredPositionFeet);

        // Setup RecyclerView for height in inches
        setupRecyclerView(R.id.rpinch, inchList, 1, 13, this::updateCenteredPositionInch);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
            }
        });

        startAnimations();

        Button nextBtn = findViewById(R.id.nextbtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), activity_bmiinformation.class);
                intent1.putExtra("selected1", selected1);
                intent1.putExtra("selectedAge", selectedAge.toString());
                if (kg != null) {
                    intent1.putExtra("kg", kg.toString());
                } else {
                    intent1.putExtra("pounds", pounds.toString());
                }
                intent1.putExtra("gender", gender);
                if (cm != null) {
                    intent1.putExtra("cm", cm.toString());
                } else {
                    intent1.putExtra("inch", inch.toString());
                    intent1.putExtra("feet", feet.toString());
                }
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                f = 1;
                finish();
            }
        });
    }

    private void setupRecyclerView(int recyclerViewId, List<Integer> list, int start, int end, RecyclerView.OnScrollListener listener) {
        RecyclerView recyclerView = findViewById(recyclerViewId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }

        ageRCadapter adapter = new ageRCadapter(list);
        recyclerView.setAdapter(adapter);

        int centerPosition = list.size() / 2;
        recyclerView.scrollToPosition(centerPosition);

        recyclerView.addOnScrollListener(listener);
    }

    private void startAnimations() {
        TextView selectHeightTxt = findViewById(R.id.selectheighttxt);
        Animation topToBottom = AnimationUtils.loadAnimation(this, R.anim.slidefromtoptobottom);
        topToBottom.setDuration(2000);
        selectHeightTxt.startAnimation(topToBottom);

        ImageView avatar = findViewById(R.id.avatar);
        Animation leftToRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        leftToRight.setDuration(2000);
        avatar.startAnimation(leftToRight);

        LinearLayout llCm = findViewById(R.id.llcm);
        LinearLayout llFt = findViewById(R.id.llft);
        Animation rightToLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        rightToLeft.setDuration(2000);
        llCm.startAnimation(rightToLeft);
        llFt.startAnimation(rightToLeft);

        LinearLayout llButtons = findViewById(R.id.llbuttons);
        Animation bottomToUp = AnimationUtils.loadAnimation(this, R.anim.slidefrombottomtotop);
        bottomToUp.setDuration(2000);
        llButtons.startAnimation(bottomToUp);
    }

    private void updateCenteredPositionCm(RecyclerView recyclerView) {
        updateCenteredPosition(recyclerView, selectedCm, weightList);
    }

    private void updateCenteredPositionFeet(RecyclerView recyclerView) {
        updateCenteredPosition(recyclerView, selectedFeet, feetList);
    }

    private void updateCenteredPositionInch(RecyclerView recyclerView) {
        updateCenteredPosition(recyclerView, selectedInch, inchList);
    }

    private void updateCenteredPosition(RecyclerView recyclerView, TextView textView, List<Integer> list) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

        int centerPosition = (firstVisiblePosition + lastVisiblePosition) / 2;
        if (centerPosition >= 0 && centerPosition < list.size()) {
            Integer value = list.get(centerPosition);
            textView.setText(value.toString());
            if (textView == selectedCm) {
                cm = value;
            } else if (textView == selectedFeet) {
                feet = value;
            } else if (textView == selectedInch) {
                inch = value;
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (f == 0) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
        }
    }
}
