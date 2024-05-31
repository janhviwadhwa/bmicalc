package com.abc.bmicalc2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String selected1;
    Integer selectedage, selectedweight;
    List<Integer> ageList, weightList;
    String genderselect = "";
    TextView agecounter, weightcounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView agerecycleview = findViewById(R.id.agerecyclerview);
        RecyclerView weightrecyclerview = findViewById(R.id.weightrecyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        agerecycleview.setLayoutManager(layoutManager);
        weightrecyclerview.setLayoutManager(layoutManager1);

        ageList = new ArrayList<>();
        weightList = new ArrayList<>();
        for (int i = 1; i <= 150; i++) {
            ageList.add(i);
        }
        for (int i = 1; i <= 200; i++) {
            weightList.add(i);
        }
        ageRCadapter ageRCAdapter = new ageRCadapter(ageList);
        ageRCadapter weightRCAdapter = new ageRCadapter(weightList);

        agerecycleview.setAdapter(ageRCAdapter);
        weightrecyclerview.setAdapter(weightRCAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                agerecycleview.smoothScrollToPosition(30);
                weightrecyclerview.smoothScrollToPosition(60);
            }
        }, 3000);

        agerecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                updateCenteredPosition(recyclerView, ageRCAdapter, ageList, agecounter);
            }
        });

        weightrecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                updateCenteredPosition(recyclerView, weightRCAdapter, weightList, weightcounter);
            }
        });

        ImageButton male, female;
        ImageView maleselect, femaleselect;
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        maleselect = findViewById(R.id.maleselect);
        femaleselect = findViewById(R.id.femaleselect);
        maleselect.setVisibility(View.INVISIBLE);
        femaleselect.setVisibility(View.INVISIBLE);
        MaterialButton kg = findViewById(R.id.kg);
        MaterialButton pounds = findViewById(R.id.pounds);
        agecounter = findViewById(R.id.agecounter);
        weightcounter = findViewById(R.id.weightcounter);

        Animation slidetoright = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slidetoright.setDuration(1500);
        female.startAnimation(slidetoright);
        femaleselect.startAnimation(slidetoright);

        Animation slidetoleft = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        slidetoleft.setDuration(1500);
        male.startAnimation(slidetoleft);
        maleselect.startAnimation(slidetoleft);

        TextView txtage = findViewById(R.id.txtage);
        TextView txtweight = findViewById(R.id.txtweight);
        MaterialButton nextheight = findViewById(R.id.nextheight);

        Animation frombottomtotop = AnimationUtils.loadAnimation(this, R.anim.slidefrombottomtotop);
        frombottomtotop.setDuration(2500);
        txtage.startAnimation(frombottomtotop);

        Animation frombottomtotop1 = AnimationUtils.loadAnimation(this, R.anim.slidefrombottomtotop);
        frombottomtotop1.setDuration(2800);
        txtage.startAnimation(frombottomtotop1);

        Animation frombottomtotop2 = AnimationUtils.loadAnimation(this, R.anim.slidefrombottomtotop);
        frombottomtotop2.setDuration(3100);
        txtweight.startAnimation(frombottomtotop2);
        kg.startAnimation(frombottomtotop2);
        pounds.startAnimation(frombottomtotop2);

        Animation frombottomtotop3 = AnimationUtils.loadAnimation(this, R.anim.slidefrombottomtotop);
        frombottomtotop3.setDuration(3500);
        weightrecyclerview.startAnimation(frombottomtotop3);

        LinearLayout agell = findViewById(R.id.agell);
        Animation frombottomtotop4 = AnimationUtils.loadAnimation(this, R.anim.slidefrombottomtotop);
        frombottomtotop4.setDuration(3900);
        agell.startAnimation(frombottomtotop4);

        LinearLayout weightll = findViewById(R.id.weightll);
        Animation frombottomtotop5 = AnimationUtils.loadAnimation(this, R.anim.slidefrombottomtotop);
        frombottomtotop5.setDuration(4200);
        weightll.startAnimation(frombottomtotop5);

        Animation frombottomtotop6 = AnimationUtils.loadAnimation(this, R.anim.slidefrombottomtotop);
        frombottomtotop6.setDuration(4500);
        nextheight.startAnimation(frombottomtotop6);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleselect.setVisibility(View.VISIBLE);
                femaleselect.setVisibility(View.INVISIBLE);
                genderselect = "male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleselect.setVisibility(View.INVISIBLE);
                femaleselect.setVisibility(View.VISIBLE);
                genderselect = "female";
            }
        });

        kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected1 = kg.getText().toString();
                kg.setBackgroundColor(Color.BLACK);
                pounds.setBackgroundColor(Color.parseColor("#3F51B5"));
            }
        });

        pounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected1 = pounds.getText().toString();
                pounds.setBackgroundColor(Color.BLACK);
                kg.setBackgroundColor(Color.parseColor("#3F51B5"));
            }
        });

        nextheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected1 != null && selectedage != null && selectedweight != null && !genderselect.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), ActivityHeightSelect.class);
                    intent.putExtra("selected1", selected1);
                    intent.putExtra("selectedAge", selectedage.toString());
                    intent.putExtra("selectedWeight", selectedweight.toString());
                    intent.putExtra("gender", genderselect);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please select all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateCenteredPosition(RecyclerView recyclerView, ageRCadapter adapter, List<Integer> dataList, TextView counter) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

        int centerPosition = (firstVisiblePosition + lastVisiblePosition) / 2;
        if (centerPosition >= 0 && centerPosition < adapter.getItemCount()) {
            Integer selectedItem = dataList.get(centerPosition);
            if (selectedItem != null) {
                counter.setText(selectedItem.toString());
                if (counter == agecounter) {
                    selectedage = selectedItem;
                } else if (counter == weightcounter) {
                    selectedweight = selectedItem;
                }
            }
        }
    }
}
