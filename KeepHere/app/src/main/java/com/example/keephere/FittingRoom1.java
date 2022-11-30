package com.example.keephere;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
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


public class FittingRoom1 extends AppCompatActivity {

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
    private static final String SHARED_PREF_NAME1 = "mypref1";
    private static final String KEY_ITEM1 = "itemNumber1";
    private static final String KEY_ITEM2 = "itemNumber2";
    private static final String KEY_ITEM3 = "itemNumber3";
    private static final String KEY_ITEM4 = "itemNumber4";
    private static final String KEY_ITEM5 = "itemNumber5";
    private static final String KEY_ITEM6 = "itemNumber6";

    // Creating a variable for the object class
    KeepHere fittingRoom1;
    Report report;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitting_room_1);

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
        databaseReference = firebaseDatabase.getReference("KeepHere");

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME1, MODE_PRIVATE);

        String itemNum1 = sharedPreferences.getString(KEY_ITEM1, "NA");
        item1Barcode.setText(itemNum1);

        String itemNum2 = sharedPreferences.getString(KEY_ITEM2, "NA");
        item2Barcode.setText(itemNum2);

        String itemNum3 = sharedPreferences.getString(KEY_ITEM3, "NA");
        item3Barcode.setText(itemNum3);

        String itemNum4 = sharedPreferences.getString(KEY_ITEM4, "NA");
        item4Barcode.setText(itemNum4);

        String itemNum5 = sharedPreferences.getString(KEY_ITEM5, "NA");
        item5Barcode.setText(itemNum5);

        String itemNum6 = sharedPreferences.getString(KEY_ITEM6, "NA");
        item6Barcode.setText(itemNum6);

        fittingRoom1 = new KeepHere();
        report = new Report();

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
            AlertDialog.Builder builder = new AlertDialog.Builder(FittingRoom1.this); // alert box
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            String barcodeNumber = result.getContents(); // SAVE BARCODE NUMBER
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    storeBarcode(barcodeNumber); // to store barcode number

                    // Only save the barcode if it is not under the same item
                    DatabaseReference barcodeReference = FirebaseDatabase.getInstance().getReference("KeepHere");
                    Query checkBarcode = barcodeReference.orderByChild("barcodeNumber").equalTo(barcodeNumber);

                    checkBarcode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                // Toast message
                                Toast.makeText(FittingRoom1.this, "Item exists", Toast.LENGTH_SHORT).show();

                            } else {
                                String currentItemNumber = fittingRoom1.getItemNumber();

                                if(!Objects.equals(currentItemNumber, barcodeNumber)){
                                    if(Objects.equals(currentItemNumber, "Item 1")){
                                        item1Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                                        editor1.putString(KEY_ITEM1, barcodeNumber);
                                        editor1.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 2")){
                                        item2Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor2 = sharedPreferences.edit();
                                        editor2.putString(KEY_ITEM2, barcodeNumber);
                                        editor2.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 3")){
                                        item3Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor3 = sharedPreferences.edit();
                                        editor3.putString(KEY_ITEM3, barcodeNumber);
                                        editor3.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 4")){
                                        item4Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor4 = sharedPreferences.edit();
                                        editor4.putString(KEY_ITEM4, barcodeNumber);
                                        editor4.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 5")){
                                        item5Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor5 = sharedPreferences.edit();
                                        editor5.putString(KEY_ITEM5, barcodeNumber);
                                        editor5.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 6")){
                                        item6Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor6 = sharedPreferences.edit();
                                        editor6.putString(KEY_ITEM6, barcodeNumber);
                                        editor6.apply();
                                    }
                                }

                                databaseReference.push().setValue(fittingRoom1); // only save data in Firebase if user respond

                                // Toast message
                                Toast.makeText(FittingRoom1.this, "Item saved", Toast.LENGTH_SHORT).show();

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
        fittingRoom1.setFittingRoomNumber(fittingRoomNumber);  // to store fitting room number
        fittingRoom1.setItemNumber(itemNumber);                // to store item number
        fittingRoom1.setDate(date);

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        fittingRoom1.setUserEmail(user.getEmail());
    }

    private void storeBarcode(String barcodeNumber){
        fittingRoom1.setBarcodeNumber(barcodeNumber);           // to store barcode number
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
            AlertDialog.Builder builder = new AlertDialog.Builder(FittingRoom1.this); // alert box
            builder.setTitle("Checkout Result");
            builder.setMessage(result.getContents()); // ?result.getContents() need to send to Firebase?
            String barcodeNumber = result.getContents(); // SAVE BARCODE NUMBER
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                    // Only delete the barcode if it is available in the database
                    DatabaseReference barcodeReference = FirebaseDatabase.getInstance().getReference();
                    Query checkoutBarcode = barcodeReference.child("KeepHere").orderByChild("barcodeNumber").equalTo(barcodeNumber);

                    checkoutBarcode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){

                                for (DataSnapshot itemNumberSnapshot : snapshot.getChildren()){
                                    String currentItemNumber = itemNumberSnapshot.child("itemNumber").getValue().toString();

                                    if(Objects.equals(currentItemNumber, "Item 1")){
                                        item1Barcode.setText("NA");
                                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                                        editor1.putString(KEY_ITEM1, "NA");
                                        editor1.apply();

                                        for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                            barcodeSnapshot.getRef().removeValue();
                                        }

                                        Toast.makeText(FittingRoom1.this, currentItemNumber, Toast.LENGTH_SHORT).show();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 2")){
                                        item2Barcode.setText("NA");
                                        SharedPreferences.Editor editor2 = sharedPreferences.edit();
                                        editor2.putString(KEY_ITEM2, "NA");
                                        editor2.apply();

                                        for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                            barcodeSnapshot.getRef().removeValue();
                                        }

                                        Toast.makeText(FittingRoom1.this, currentItemNumber, Toast.LENGTH_SHORT).show();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 3")){
                                        item3Barcode.setText("NA");
                                        SharedPreferences.Editor editor3 = sharedPreferences.edit();
                                        editor3.putString(KEY_ITEM3, "NA");
                                        editor3.apply();

                                        for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                            barcodeSnapshot.getRef().removeValue();
                                        }

                                        Toast.makeText(FittingRoom1.this, currentItemNumber, Toast.LENGTH_SHORT).show();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 4")){
                                        item4Barcode.setText("NA");
                                        SharedPreferences.Editor editor4 = sharedPreferences.edit();
                                        editor4.putString(KEY_ITEM4, "NA");
                                        editor4.apply();

                                        for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                            barcodeSnapshot.getRef().removeValue();
                                        }

                                        Toast.makeText(FittingRoom1.this, currentItemNumber, Toast.LENGTH_SHORT).show();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 5")){
                                        item5Barcode.setText("NA");
                                        SharedPreferences.Editor editor5 = sharedPreferences.edit();
                                        editor5.putString(KEY_ITEM5, "NA");
                                        editor5.apply();

                                        for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                            barcodeSnapshot.getRef().removeValue();
                                        }

                                        Toast.makeText(FittingRoom1.this, currentItemNumber, Toast.LENGTH_SHORT).show();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 6")){
                                        item6Barcode.setText("NA");
                                        SharedPreferences.Editor editor6 = sharedPreferences.edit();
                                        editor6.putString(KEY_ITEM6, "NA");
                                        editor6.apply();

                                        for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                            barcodeSnapshot.getRef().removeValue();
                                        }

                                        Toast.makeText(FittingRoom1.this, currentItemNumber, Toast.LENGTH_SHORT).show();
                                    }

                                }


                            } else{
                                Toast.makeText(FittingRoom1.this, "Suspected stolen items", Toast.LENGTH_SHORT).show();
                                report.setBarcode(barcodeNumber);

                                databaseReference = firebaseDatabase.getReference("Report");
                                databaseReference.push().setValue(report);

                                showPopupWindow();

                                databaseReference = firebaseDatabase.getReference("KeepHere");
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

    @SuppressLint("SetTextI18n")
    public void clearFittingRoom(){
        DatabaseReference fittingRoom = FirebaseDatabase.getInstance().getReference("KeepHere");

        fittingRoom.removeValue();

        item1Barcode.setText("NA");
        item2Barcode.setText("NA");
        item3Barcode.setText("NA");
        item4Barcode.setText("NA");
        item5Barcode.setText("NA");
        item6Barcode.setText("NA");

        SharedPreferences.Editor editor1 = sharedPreferences.edit();
        editor1.putString(KEY_ITEM1, "NA");
        editor1.putString(KEY_ITEM2, "NA");
        editor1.putString(KEY_ITEM3, "NA");
        editor1.putString(KEY_ITEM4, "NA");
        editor1.putString(KEY_ITEM5, "NA");
        editor1.putString(KEY_ITEM6, "NA");
        editor1.apply();
    }
}
