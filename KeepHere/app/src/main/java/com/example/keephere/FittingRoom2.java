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

    Button btnCheckout;
    Button btnClear;

    FirebaseAuth mAuth;

    // Creating a variable for the Firebase Database
    FirebaseDatabase firebaseDatabase;

    // Creating a variable for the Database Reference for Firebase
    DatabaseReference databaseReference;

    SharedPreferences sharedPreferences;

    // Creating a shared preference name and key name
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_ITEM1 = "itemNumber1";
    private static final String KEY_ITEM2 = "itemNumber2";
    private static final String KEY_ITEM3 = "itemNumber3";
    private static final String KEY_ITEM4 = "itemNumber4";
    private static final String KEY_ITEM5 = "itemNumber5";
    private static final String KEY_ITEM6 = "itemNumber6";


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

        // Used to get the instance of the Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Used to get the reference for the database
        databaseReference = firebaseDatabase.getReference("KeepHere2");
        //databaseReference = firebaseDatabase.getReference("Report");

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.apply();

        String itemNum1 = sharedPreferences.getString(KEY_ITEM1, "NA");
        //itemNumber1.setText(itemNum1);
        item1Barcode.setText(itemNum1);

        String itemNum2 = sharedPreferences.getString(KEY_ITEM2, "NA");
        item2Barcode.setText(itemNum2);



        fittingRoom2 = new KeepHere2();
        report = new Report();

        btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(v ->{
            String fittingRoomNumber = frNumber.getText().toString();

            Date dateCurrent = Calendar.getInstance().getTime();
            String date = dateCurrent.toString();

            saveSuspected(fittingRoomNumber, date);

            //scanCheckoutCode();
        });

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v ->{
            clearFittingRoom();
            //startActivity(new Intent(FittingRoom2.this, FittingRoom2.class ));

        });

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
                                        //String originalItemNumber = fittingRoom2.getItemNumber();
                                        //String originalItemNumber1 = "Item 1";
                                        //itemNumber1.setText(barcodeNumber);
                                        item1Barcode.setText(barcodeNumber);
//                                        fittingRoom2.setItemNumber(originalItemNumber);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(KEY_ITEM1, barcodeNumber);
                                        editor.apply();
                                        //fittingRoom2.setItemNumber(originalItemNumber1);

                                    }
                                    if(Objects.equals(currentItemNumber, "Item 2")){
                                        item2Barcode.setText(barcodeNumber);
                                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                                        editor1.putString(KEY_ITEM2, barcodeNumber);
                                        editor1.apply();
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 3")){
                                        String originalItemNumber = fittingRoom2.getItemNumber();
                                        itemNumber3.setText(barcodeNumber);
                                        fittingRoom2.setItemNumber(originalItemNumber);
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 4")){
                                        String originalItemNumber = fittingRoom2.getItemNumber();
                                        itemNumber4.setText(barcodeNumber);
                                        fittingRoom2.setItemNumber(originalItemNumber);
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 5")){
                                        String originalItemNumber = fittingRoom2.getItemNumber();
                                        itemNumber5.setText(barcodeNumber);
                                        fittingRoom2.setItemNumber(originalItemNumber);
                                    }
                                    if(Objects.equals(currentItemNumber, "Item 6")){
                                        String originalItemNumber = fittingRoom2.getItemNumber();
                                        itemNumber6.setText(barcodeNumber);
                                        fittingRoom2.setItemNumber(originalItemNumber);
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
        scanCode();                                        // open the scanner
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
                                    //String originalItemNumber = fittingRoom2.getItemNumber();
                                    //String originalItemNumber1 = "Item 1";
                                    //itemNumber1.setText(barcodeNumber);
                                    item1Barcode.setText("NA");
//                                        fittingRoom2.setItemNumber(originalItemNumber);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(KEY_ITEM1, "NA");
                                    editor.apply();
                                    //fittingRoom2.setItemNumber(originalItemNumber1);

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
        report.setFittingRoom(fittingRoomNumber);  // to store fitting room number
        report.setDate(date);

        //FirebaseUser user = mAuth.getInstance().getCurrentUser();
        //report.setUserEmail(user.getEmail());
    }

    private void showPopupWindow(){
        View view = View.inflate(this, R.layout.popup_layout, null);
        Button close = view.findViewById(R.id.close);
        TextView body = view.findViewById(R.id.popupMessage);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        PopupWindow popupWindow = new PopupWindow(view, width, height, false);

        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

//        body.setText(R.string.suspectMessage);

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
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ITEM1, "NA");
        editor.putString(KEY_ITEM2, "NA");
        editor.apply();
    }

}
