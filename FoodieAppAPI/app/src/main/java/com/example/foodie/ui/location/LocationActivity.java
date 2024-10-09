//package com.example.foodie.ui.location;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.foodie.R;
//import com.example.foodie.models.User;
//
//public class LocationActivity extends AppCompatActivity {
//
//    private TextView tvUserAddress;
//    private EditText etStreet, etPostCode, etApartment;
//    private Button btnHome, btnWork, btnOther, btnSaveLocation;
//    private User user;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_location);
//
//        tvUserAddress = findViewById(R.id.tvUserAddress);
//        etStreet = findViewById(R.id.etStreet);
//        etPostCode = findViewById(R.id.etPostCode);
//        etApartment = findViewById(R.id.etApartment);
//        btnHome = findViewById(R.id.btnHome);
//        btnWork = findViewById(R.id.btnWork);
//        btnOther = findViewById(R.id.btnOther);
//        btnSaveLocation = findViewById(R.id.btnSaveLocation);
//
//        // Dummy User data - replace with actual data fetching logic
//        user = getUserData();
//
//        // Set user data to views
//        tvUserAddress.setText(user.getAddress());
//        etStreet.setText(user.getAddress());
//        etPostCode.setText("34567");
//        etApartment.setText("345");
//
//        // Button clicks for Label As
//        btnHome.setOnClickListener(view -> setLabel(btnHome));
//        btnWork.setOnClickListener(view -> setLabel(btnWork));
//        btnOther.setOnClickListener(view -> setLabel(btnOther));
//
//        btnSaveLocation.setOnClickListener(view -> saveLocation());
//    }
//
//    private User getUserData() {
//        return new User(1, "John", "Doe", "1234567890", "john@example.com", "3235 Royal Ln, Mesa, New Jersey 34567", "2024-10-08", "2024-10-08", null, 1, "User");
//    }
//
//    private void setLabel(Button selectedButton) {
//        // Reset all buttons to default style
//        btnHome.setBackgroundTintList(getResources().getColorStateList(R.color.grey));
//        btnHome.setTextColor(getResources().getColor(R.color.black));
//        btnWork.setBackgroundTintList(getResources().getColorStateList(R.color.grey));
//        btnWork.setTextColor(getResources().getColor(R.color.black));
//        btnOther.setBackgroundTintList(getResources().getColorStateList(R.color.grey));
//        btnOther.setTextColor(getResources().getColor(R.color.black));
//
//        // Set selected button's style
//        selectedButton.setBackgroundTintList(getResources().getColorStateList(R.color.orange));
//        selectedButton.setTextColor(getResources().getColor(R.color.white));
//    }
//
//    private void saveLocation() {
//        // Collect the data and process saving
//        String street = etStreet.getText().toString();
//        String postCode = etPostCode.getText().toString();
//        String apartment = etApartment.getText().toString();
//
//        // For example, you could save this to a database or send it to a server
//        Toast.makeText(this, "Location saved!", Toast.LENGTH_SHORT).show();
//    }
//}
