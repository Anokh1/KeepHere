package com.example.keephere;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class FittingRoom6 extends AppCompatActivity {
    CardView item1;
    CardView item2;
    CardView item3;
    CardView item4;
    CardView item5;
    CardView item6;

    Button btnReport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitting_room_6);

        item1 = findViewById(R.id.item1);
        item1.setOnClickListener(v -> {
            scanCode();
        });

        item2 = findViewById(R.id.item2);
        item2.setOnClickListener(v -> {
            scanCode();
        });

        item3 = findViewById(R.id.item3);
        item3.setOnClickListener(v -> {
            scanCode();
        });

        item4 = findViewById(R.id.item4);
        item4.setOnClickListener(v -> {
            scanCode();
        });

        item5 = findViewById(R.id.item5);
        item5.setOnClickListener(v -> {
            scanCode();
        });

        item6 = findViewById(R.id.item6);
        item6.setOnClickListener(v -> {
            scanCode();
        });

    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class); // from CaptureAct.java
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(FittingRoom6.this); // alert box
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}
