package com.example.keephere;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


public class FittingRoom1 extends AppCompatActivity {

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

    Button btnCheckout;

    FirebaseAuth mAuth;

    // Creating a variable for the Firebase Database
    FirebaseDatabase firebaseDatabase;

    // Creating a variable for the Database Reference for Firebase
    DatabaseReference databaseReference;

    // Creating a variable for the object class
    KeepHere keepHere;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitting_room_1);

        frNumber = findViewById(R.id.textView);

        itemNumber1 = findViewById(R.id.item1Text);
        itemNumber2 = findViewById(R.id.item2Text);
        itemNumber3 = findViewById(R.id.item3Text);
        itemNumber4 = findViewById(R.id.item4Text);
        itemNumber5 = findViewById(R.id.item5Text);
        itemNumber6 = findViewById(R.id.item6Text);

        // Used to get the instance of the Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance();

        // Used to get the reference for the database
        databaseReference = firebaseDatabase.getReference("KeepHere");

        keepHere = new KeepHere();

        btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(v ->{
            scanCheckoutCode();
        });


        item1 = findViewById(R.id.item1);
//        item1.setOnClickListener(v -> {
//            scanCode();
//            String fittingRoomNumber = frNumber.getText().toString();
//            String itemNumber = itemNumber1.getText().toString();
//
//            addDataToFirebase(fittingRoomNumber, itemNumber);
//        });

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fittingRoomNumber = frNumber.getText().toString(); // get the Fitting Room Number from activity_fitting_room_1.xml
                String itemNumber = itemNumber1.getText().toString();     // get the Item Number from activity_fitting_room_1.xml

                Date dateCurrent = Calendar.getInstance().getTime();
                String date = dateCurrent.toString();

                openScanner(fittingRoomNumber, itemNumber, date);         // to open the scanner and send data to Firebase



            }
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

//        FirebaseUser user = mAuth.getInstance().getCurrentUser();
//        String email = user.getEmail();
//
//        DatabaseReference emailReference = FirebaseDatabase.getInstance().getReference("KeepHere");
//        Query checkEmail = emailReference.orderByChild("userEmail").equalTo(email);
//
//        checkEmail.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    // Toast message
//                    //Toast.makeText(FittingRoom1.this, "Item exists", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    //databaseReference.push().setValue(keepHere); // only save data in Firebase if user respond
//                    barLauncher.launch(options);
//
//                    // Toast message
//                    //Toast.makeText(FittingRoom1.this, "Item saved", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        barLauncher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(FittingRoom1.this); // alert box
            builder.setTitle("Result");
            builder.setMessage(result.getContents()); // ?result.getContents() need to send to Firebase?
            String barcodeNumber = result.getContents(); // SAVE BARCODE NUMBER
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    //addBarcodeToFirebase(barcodeNumber); // OLD METHOD
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
                                databaseReference.push().setValue(keepHere); // only save data in Firebase if user respond

                                // Toast message
                                Toast.makeText(FittingRoom1.this, "Item saved", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    //databaseReference.child(barcodeNumber);

                    //databaseReference.push().setValue(keepHere); // only save data in Firebase if user respond
                }
            }).show();


        }
    });

    private void openScanner(String fittingRoomNumber, String itemNumber, String date){
         scanCode();                                        // open the scanner
         keepHere.setFittingRoomNumber(fittingRoomNumber);  // to store fitting room number
         keepHere.setItemNumber(itemNumber);                // to store item number
         keepHere.setDate(date);

         FirebaseUser user = mAuth.getInstance().getCurrentUser();
         keepHere.setUserEmail(user.getEmail());
    }

    private void storeBarcode(String barcodeNumber){
        keepHere.setBarcodeNumber(barcodeNumber);           // to store barcode number
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
            builder.setTitle("Result");
            builder.setMessage(result.getContents()); // ?result.getContents() need to send to Firebase?
            String barcodeNumber = result.getContents(); // SAVE BARCODE NUMBER
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    //storeCheckoutBarcode(barcodeNumber); // to store barcode number

                    // Only delete the barcode if it is available in the database
                    DatabaseReference barcodeReference = FirebaseDatabase.getInstance().getReference();
                    Query checkoutBarcode = barcodeReference.child("KeepHere").orderByChild("barcodeNumber").equalTo(barcodeNumber);

                    checkoutBarcode.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot barcodeSnapshot: snapshot.getChildren()) {
                                barcodeSnapshot.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

//                    barcodeReference.child("KeepHere").orderByChild("barcodeNumber").equalTo(barcodeNumber).addChildEventListener(new ChildEventListener() {
//                        @Override
//                        public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
//                            barcodeReference.child("KeepHere").child(snapshot.getKey()).setValue(null);
//                            //snapshot.getRef().setValue(null);
//                        }
//
//                        @Override
//                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                        }
//
//                        @Override
//                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                        }
//
//                        @Override
//                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });

                }
            }).show();


        }
    });

    private void openCheckoutScanner(String fittingRoomNumber, String itemNumber, String date){
        scanCode();                                        // open the scanner
        keepHere.setFittingRoomNumber(fittingRoomNumber);  // to store fitting room number
        keepHere.setItemNumber(itemNumber);                // to store item number
        keepHere.setDate(date);

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        keepHere.setUserEmail(user.getEmail());
    }

    private void storeCheckoutBarcode(String barcodeNumber){
        keepHere.setBarcodeNumber(barcodeNumber);           // to store barcode number
    }




    private void addDataToFirebase(String fittingRoomNumber, String itemNumber /*String barcodeNumber*/){
        // Set data in the object class (KeepHere)
        keepHere.setFittingRoomNumber(fittingRoomNumber);
        keepHere.setItemNumber(itemNumber);
        //keepHere.setBarcodeNumber(barcodeNumber);

        // Add value event listener method that is called with database reference
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Setting the object class (KeepHere) to the Database Reference
                // Database Reference will send the data to Firebase
                //databaseReference.setValue(keepHere); //THIS IS THE ORIGINAL

                // Toast message
                Toast.makeText(FittingRoom1.this, "Item saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FittingRoom1.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addBarcodeToFirebase(String barcodeNumber){
        // Set data in the object class (KeepHere)
        keepHere.setBarcodeNumber(barcodeNumber);

        // Add value event listener method that is called with database reference
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Setting the object class (KeepHere) to the Database Reference
                // Database Reference will send the data to Firebase
                databaseReference.setValue(keepHere); //THIS IS THE ORIGINAL

                // Toast message
                Toast.makeText(FittingRoom1.this, "Item saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FittingRoom1.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
