package com.example.keephere;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class FittingRoom5 extends AppCompatActivity {

    RelativeLayout parent;

    CardView item1;
    CardView item2;
    CardView item3;
    CardView item4;
    CardView item5;
    CardView item6;

    TextView frNumber;
    TextView itemNumber1;
    TextView itemNumber2;
    TextView itemNumber3;
    TextView itemNumber4;
    TextView itemNumber5;
    TextView itemNumber6;

    TextView item1Barcode;
    TextView item2Barcode;
    TextView item3Barcode;
    TextView item4Barcode;
    TextView item5Barcode;
    TextView item6Barcode;

    Button btnCheckout;
    Button btnClear;

    FirebaseAuth mAuth;

    // Creating a variable for the Firebase Database
    FirebaseDatabase firebaseDatabase;

    // Creating a variable for the Database Reference for Firebase
    DatabaseReference databaseReference;

    SharedPreferences sharedPreferences;

    // Creating a shared preference name and key name
    private static final String SHARED_PREF_NAME5 = "mypref5";
    private static final String KEY_ITEM25 = "itemNumber1";
    private static final String KEY_ITEM26 = "itemNumber2";
    private static final String KEY_ITEM27 = "itemNumber3";
    private static final String KEY_ITEM28 = "itemNumber4";
    private static final String KEY_ITEM29 = "itemNumber5";
    private static final String KEY_ITEM30 = "itemNumber6";

    // Creating a variable for the object class
    KeepHere5 fittingRoom5;
    Report report;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitting_room_5);

        parent = findViewById(R.id.parent_layout);

        frNumber = findViewById(R.id.textView);

        itemNumber1 = findViewById(R.id.item1Text);
        itemNumber2 = findViewById(R.id.item2Text);
        itemNumber3 = findViewById(R.id.item3Text);
        itemNumber4 = findViewById(R.id.item4Text);
        itemNumber5 = findViewById(R.id.item5Text);
        itemNumber6 = findViewById(R.id.item6Text);

        item1Barcode = findViewById(R.id.item1Barcode);
        item2Barcode = findViewById(R.id.item2Barcode);
        item3Barcode = findViewById(R.id.item3Barcode);
        item4Barcode = findViewById(R.id.item4Barcode);
        item5Barcode = findViewById(R.id.item5Barcode);
        item6Barcode = findViewById(R.id.item6Barcode);

        // Used to get the instance of the Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Used to get the reference for the database
        databaseReference = firebaseDatabase.getReference("KeepHere5");

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME5, MODE_PRIVATE);

        String itemNum1 = sharedPreferences.getString(KEY_ITEM25, "NA");
        item1Barcode.setText(itemNum1);

        String itemNum2 = sharedPreferences.getString(KEY_ITEM26, "NA");
        item2Barcode.setText(itemNum2);

        String itemNum3 = sharedPreferences.getString(KEY_ITEM27, "NA");
        item3Barcode.setText(itemNum3);

        String itemNum4 = sharedPreferences.getString(KEY_ITEM28, "NA");
        item4Barcode.setText(itemNum4);

        String itemNum5 = sharedPreferences.getString(KEY_ITEM29, "NA");
        item5Barcode.setText(itemNum5);

        String itemNum6 = sharedPreferences.getString(KEY_ITEM30, "NA");
        item6Barcode.setText(itemNum6);

        fittingRoom5 = new KeepHere5();
        report = new Report(); // POTENTIAL ERROR


        btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(v ->{
            String fittingRoomNumber = frNumber.getText().toString();

            Date dateCurrent = Calendar.getInstance().getTime();
            String date = dateCurrent.toString();

            saveSuspected(fittingRoomNumber, date);
        });

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v ->{
            clearFittingRoom();
        });

// 123456
        item1 = findViewById(R.id.item1);
        item1.setOnClickListener(v -> {
            String fittingRoomNumber = frNumber.getText().toString();
            String itemNumber = itemNumber1.getText().toString();

            Date dateCurrent = Calendar.getInstance().getTime();
            String date = dateCurrent.toString();

            openScanner(fittingRoomNumber, itemNumber, date);
        });

        item2 = findViewById(R.id.item2);
        item2.setOnClickListener(v -> {
            String fittingRoomNumber = frNumber.getText().toString();
            String itemNumber = itemNumber2.getText().toString();

            Date dateCurrent = Calendar.getInstance().getTime();
            String date = dateCurrent.toString();

            openScanner(fittingRoomNumber, itemNumber, date);
        });

        item3 = findViewById(R.id.item3);
        item3.setOnClickListener(v -> {
            String fittingRoomNumber = frNumber.getText().toString();
            String itemNumber = itemNumber3.getText().toString();

            Date dateCurrent = Calendar.getInstance().getTime();
            String date = dateCurrent.toString();

            openScanner(fittingRoomNumber, itemNumber, date);
        });

        item4 = findViewById(R.id.item4);
        item4.setOnClickListener(v -> {
            String fittingRoomNumber = frNumber.getText().toString();
            String itemNumber = itemNumber4.getText().toString();

            Date dateCurrent = Calendar.getInstance().getTime();
            String date = dateCurrent.toString();

            openScanner(fittingRoomNumber, itemNumber, date);
        });

        item5 = findViewById(R.id.item5);
        item5.setOnClickListener(v -> {
            String fittingRoomNumber = frNumber.getText().toString();
            String itemNumber = itemNumber5.getText().toString();

            Date dateCurrent = Calendar.getInstance().getTime();
            String date = dateCurrent.toString();

            openScanner(fittingRoomNumber, itemNumber, date);
        });

        item6 = findViewById(R.id.item6);
        item6.setOnClickListener(v -> {
            String fittingRoomNumber = frNumber.getText().toString();
            String itemNumber = itemNumber6.getText().toString();

            Date dateCurrent = Calendar.getInstance().getTime();
            String date = dateCurrent.toString();

            openScanner(fittingRoomNumber, itemNumber, date);
        });

    }

    // Checkin
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
            AlertDialog.Builder builder = new AlertDialog.Builder(FittingRoom5.this); // alert box
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            String barcodeNumber = result.getContents(); // SAVE BARCODE NUMBER
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    storeBarcode(barcodeNumber); // to store barcode number

                    // Only save the barcode if it is not under the same item
                    DatabaseReference barcodeReference = FirebaseDatabase.getInstance().getReference("KeepHere5");
                    Query checkBarcode = barcodeReference.orderByChild("barcodeNumber").equalTo(barcodeNumber);

                    checkBarcode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                // Toast message
                                Toast.makeText(FittingRoom5.this, "Item exists", Toast.LENGTH_SHORT).show();

                            } else {
                                String currentItemNumber = fittingRoom5.getItemNumber();

                                if(!Objects.equals(currentItemNumber, barcodeNumber)){
                                    if(Objects.equals(currentItemNumber, "Item 1")){
                                        item1Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor25 = sharedPreferences.edit();
                                        editor25.putString(KEY_ITEM25, barcodeNumber);
                                        editor25.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 2")){
                                        item2Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor26 = sharedPreferences.edit();
                                        editor26.putString(KEY_ITEM26, barcodeNumber);
                                        editor26.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 3")){
                                        item3Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor27 = sharedPreferences.edit();
                                        editor27.putString(KEY_ITEM27, barcodeNumber);
                                        editor27.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 4")){
                                        item4Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor28 = sharedPreferences.edit();
                                        editor28.putString(KEY_ITEM28, barcodeNumber);
                                        editor28.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 5")){
                                        item5Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor29 = sharedPreferences.edit();
                                        editor29.putString(KEY_ITEM29, barcodeNumber);
                                        editor29.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 6")){
                                        item6Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor30 = sharedPreferences.edit();
                                        editor30.putString(KEY_ITEM30, barcodeNumber);
                                        editor30.apply();
                                    }
                                }

                                databaseReference.push().setValue(fittingRoom5); // only save data in Firebase if user respond

                                // Toast message
                                Toast.makeText(FittingRoom5.this, "Item saved", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }).show();
        }
    });

    private void openScanner(String fittingRoomNumber, String itemNumber, String date){
        scanCode();                                            // open the scanner
        fittingRoom5.setFittingRoomNumber(fittingRoomNumber);  // to store fitting room number
        fittingRoom5.setItemNumber(itemNumber);                // to store item number
        fittingRoom5.setDate(date);

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        fittingRoom5.setUserEmail(user.getEmail());
    }

    private void storeBarcode(String barcodeNumber){
        fittingRoom5.setBarcodeNumber(barcodeNumber);           // to store barcode number
    }


    // Checkout
    private void scanCheckoutCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class); // from CaptureAct.java
        checkoutBarLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> checkoutBarLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(FittingRoom5.this); // alert box
            builder.setTitle("Result");
            builder.setMessage(result.getContents()); // ?result.getContents() need to send to Firebase?
            String barcodeNumber = result.getContents(); // SAVE BARCODE NUMBER
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                    // Only delete the barcode if it is available in the database
                    DatabaseReference barcodeReference = FirebaseDatabase.getInstance().getReference();
                    Query checkoutBarcode = barcodeReference.child("KeepHere5").orderByChild("barcodeNumber").equalTo(barcodeNumber);

                    checkoutBarcode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){

                                String currentItemNumber = fittingRoom5.getItemNumber();

                                if(Objects.equals(currentItemNumber, "Item 1")){
                                    item1Barcode.setText("NA");
                                    SharedPreferences.Editor editor25 = sharedPreferences.edit();
                                    editor25.putString(KEY_ITEM25, "NA");
                                    editor25.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 2")){
                                    item2Barcode.setText("NA");
                                    SharedPreferences.Editor editor26 = sharedPreferences.edit();
                                    editor26.putString(KEY_ITEM26, "NA");
                                    editor26.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 3")){
                                    item3Barcode.setText("NA");
                                    SharedPreferences.Editor editor27 = sharedPreferences.edit();
                                    editor27.putString(KEY_ITEM27, "NA");
                                    editor27.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 4")){
                                    item4Barcode.setText("NA");
                                    SharedPreferences.Editor editor28 = sharedPreferences.edit();
                                    editor28.putString(KEY_ITEM28, "NA");
                                    editor28.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 5")){
                                    item5Barcode.setText("NA");
                                    SharedPreferences.Editor editor29 = sharedPreferences.edit();
                                    editor29.putString(KEY_ITEM29, "NA");
                                    editor29.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 6")){
                                    item6Barcode.setText("NA");
                                    SharedPreferences.Editor editor30 = sharedPreferences.edit();
                                    editor30.putString(KEY_ITEM30, "NA");
                                    editor30.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }

                            } else{
                                Toast.makeText(FittingRoom5.this, "Suspected stolen items", Toast.LENGTH_SHORT).show();
                                report.setBarcode(barcodeNumber);

                                databaseReference = firebaseDatabase.getReference("Report");
                                databaseReference.push().setValue(report);

                                showPopupWindow();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }).show();
        }
    });

    private void saveSuspected(String fittingRoomNumber, String date){
        scanCheckoutCode();                                        // open the scanner
        report.setFittingRoom(fittingRoomNumber);                  // to store fitting room number
        report.setDate(date);
    }

    private void showPopupWindow(){
        View view = View.inflate(this, R.layout.popup_layout, null);
        Button close = view.findViewById(R.id.close);
        TextView body = view.findViewById(R.id.popupMessage);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        PopupWindow popupWindow = new PopupWindow(view, width, height, false);

        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

        close.setOnClickListener(v -> {
            popupWindow.dismiss();

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0125091285"));
            startActivity(intent);
        });
    }

    public void clearFittingRoom(){
        DatabaseReference fittingRoom = FirebaseDatabase.getInstance().getReference("KeepHere5");

        fittingRoom.removeValue();

        item1Barcode.setText("NA");
        item2Barcode.setText("NA");
        item3Barcode.setText("NA");
        item4Barcode.setText("NA");
        item5Barcode.setText("NA");
        item6Barcode.setText("NA");

        SharedPreferences.Editor editor25 = sharedPreferences.edit();
        editor25.putString(KEY_ITEM25, "NA");
        editor25.putString(KEY_ITEM26, "NA");
        editor25.putString(KEY_ITEM27, "NA");
        editor25.putString(KEY_ITEM28, "NA");
        editor25.putString(KEY_ITEM29, "NA");
        editor25.putString(KEY_ITEM30, "NA");
        editor25.apply();
    }
}
