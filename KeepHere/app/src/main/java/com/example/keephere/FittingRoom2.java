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

public class FittingRoom2 extends AppCompatActivity {

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
    private static final String SHARED_PREF_NAME2 = "mypref2";
    private static final String KEY_ITEM7 = "itemNumber1";
    private static final String KEY_ITEM8 = "itemNumber2";
    private static final String KEY_ITEM9 = "itemNumber3";
    private static final String KEY_ITEM10 = "itemNumber4";
    private static final String KEY_ITEM11 = "itemNumber5";
    private static final String KEY_ITEM12 = "itemNumber6";


    // Creating a variable for the object class
    KeepHere2 fittingRoom2;
    Report report;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitting_room_2);

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
        databaseReference = firebaseDatabase.getReference("KeepHere2");

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME2, MODE_PRIVATE);

        String itemNum1 = sharedPreferences.getString(KEY_ITEM7, "NA");
        item1Barcode.setText(itemNum1);

        String itemNum2 = sharedPreferences.getString(KEY_ITEM8, "NA");
        item2Barcode.setText(itemNum2);

        String itemNum3 = sharedPreferences.getString(KEY_ITEM9, "NA");
        item3Barcode.setText(itemNum3);

        String itemNum4 = sharedPreferences.getString(KEY_ITEM10, "NA");
        item4Barcode.setText(itemNum4);

        String itemNum5 = sharedPreferences.getString(KEY_ITEM11, "NA");
        item5Barcode.setText(itemNum5);

        String itemNum6 = sharedPreferences.getString(KEY_ITEM12, "NA");
        item6Barcode.setText(itemNum6);

        fittingRoom2 = new KeepHere2();
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
            AlertDialog.Builder builder = new AlertDialog.Builder(FittingRoom2.this); // alert box
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            String barcodeNumber = result.getContents(); // SAVE BARCODE NUMBER
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    storeBarcode(barcodeNumber); // to store barcode number

                    // Only save the barcode if it is not under the same item
                    DatabaseReference barcodeReference = FirebaseDatabase.getInstance().getReference("KeepHere2");
                    Query checkBarcode = barcodeReference.orderByChild("barcodeNumber").equalTo(barcodeNumber);

                    checkBarcode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                // Toast message
                                Toast.makeText(FittingRoom2.this, "Item exists", Toast.LENGTH_SHORT).show();

                            } else {
                                String currentItemNumber = fittingRoom2.getItemNumber();

                                if(!Objects.equals(currentItemNumber, barcodeNumber)){
                                    if(Objects.equals(currentItemNumber, "Item 1")){
                                        item1Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor7 = sharedPreferences.edit();
                                        editor7.putString(KEY_ITEM7, barcodeNumber);
                                        editor7.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 2")){
                                        item2Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor8 = sharedPreferences.edit();
                                        editor8.putString(KEY_ITEM8, barcodeNumber);
                                        editor8.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 3")){
                                        item3Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor9 = sharedPreferences.edit();
                                        editor9.putString(KEY_ITEM9, barcodeNumber);
                                        editor9.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 4")){
                                        item4Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor10 = sharedPreferences.edit();
                                        editor10.putString(KEY_ITEM10, barcodeNumber);
                                        editor10.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 5")){
                                        item5Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor11 = sharedPreferences.edit();
                                        editor11.putString(KEY_ITEM11, barcodeNumber);
                                        editor11.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 6")){
                                        item6Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor12 = sharedPreferences.edit();
                                        editor12.putString(KEY_ITEM12, barcodeNumber);
                                        editor12.apply();
                                    }
                                }

                                databaseReference.push().setValue(fittingRoom2); // only save data in Firebase if user respond

                                // Toast message
                                Toast.makeText(FittingRoom2.this, "Item saved", Toast.LENGTH_SHORT).show();

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
        fittingRoom2.setFittingRoomNumber(fittingRoomNumber);  // to store fitting room number
        fittingRoom2.setItemNumber(itemNumber);                // to store item number
        fittingRoom2.setDate(date);

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        fittingRoom2.setUserEmail(user.getEmail());
    }

    private void storeBarcode(String barcodeNumber){
        fittingRoom2.setBarcodeNumber(barcodeNumber);           // to store barcode number
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
            AlertDialog.Builder builder = new AlertDialog.Builder(FittingRoom2.this); // alert box
            builder.setTitle("Result");
            builder.setMessage(result.getContents()); // ?result.getContents() need to send to Firebase?
            String barcodeNumber = result.getContents(); // SAVE BARCODE NUMBER
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                    // Only delete the barcode if it is available in the database
                    DatabaseReference barcodeReference = FirebaseDatabase.getInstance().getReference();
                    Query checkoutBarcode = barcodeReference.child("KeepHere2").orderByChild("barcodeNumber").equalTo(barcodeNumber);

                    checkoutBarcode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){

                                String currentItemNumber = fittingRoom2.getItemNumber();

                                if(Objects.equals(currentItemNumber, "Item 1")){
                                    item1Barcode.setText("NA");
                                    SharedPreferences.Editor editor7 = sharedPreferences.edit();
                                    editor7.putString(KEY_ITEM7, "NA");
                                    editor7.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 2")){
                                    item2Barcode.setText("NA");
                                    SharedPreferences.Editor editor8 = sharedPreferences.edit();
                                    editor8.putString(KEY_ITEM8, "NA");
                                    editor8.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 3")){
                                    item3Barcode.setText("NA");
                                    SharedPreferences.Editor editor9 = sharedPreferences.edit();
                                    editor9.putString(KEY_ITEM9, "NA");
                                    editor9.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 4")){
                                    item4Barcode.setText("NA");
                                    SharedPreferences.Editor editor10 = sharedPreferences.edit();
                                    editor10.putString(KEY_ITEM10, "NA");
                                    editor10.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 5")){
                                    item5Barcode.setText("NA");
                                    SharedPreferences.Editor editor11 = sharedPreferences.edit();
                                    editor11.putString(KEY_ITEM11, "NA");
                                    editor11.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }
                                if(Objects.equals(currentItemNumber, "Item 6")){
                                    item6Barcode.setText("NA");
                                    SharedPreferences.Editor editor12 = sharedPreferences.edit();
                                    editor12.putString(KEY_ITEM12, "NA");
                                    editor12.apply();

                                    for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                        barcodeSnapshot.getRef().removeValue();
                                    }
                                }

                            } else{
                                Toast.makeText(FittingRoom2.this, "Suspected stolen items", Toast.LENGTH_SHORT).show();
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
        DatabaseReference fittingRoom = FirebaseDatabase.getInstance().getReference("KeepHere2");

        fittingRoom.removeValue();

        item1Barcode.setText("NA");
        item2Barcode.setText("NA");
        item3Barcode.setText("NA");
        item4Barcode.setText("NA");
        item5Barcode.setText("NA");
        item6Barcode.setText("NA");

        SharedPreferences.Editor editor7 = sharedPreferences.edit();
        editor7.putString(KEY_ITEM7, "NA");
        editor7.putString(KEY_ITEM8, "NA");
        editor7.putString(KEY_ITEM9, "NA");
        editor7.putString(KEY_ITEM10, "NA");
        editor7.putString(KEY_ITEM11, "NA");
        editor7.putString(KEY_ITEM12, "NA");
        editor7.apply();
    }
}
