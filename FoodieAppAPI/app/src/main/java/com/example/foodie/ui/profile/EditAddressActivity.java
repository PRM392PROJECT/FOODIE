package com.example.foodie.ui.profile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.foodie.databinding.ActivityEditAddressBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EditAddressActivity extends AppCompatActivity implements View.OnClickListener, IEditAddressView {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private ActivityEditAddressBinding binding;
    private EditAddressPresent present;
    private String address;
    private Marker currentMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE));
        binding = ActivityEditAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        present = new EditAddressPresent(this, this);

        binding.map.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        binding.map.setMultiTouchControls(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getLastLocation();
        }

        binding.btnSaveLocation.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);

        // Tạo lớp Overlay để theo dõi khi người dùng di chuyển bản đồ
        binding.map.addMapListener(new org.osmdroid.events.MapListener() {
            @Override
            public boolean onScroll(ScrollEvent event) {
                // Khi bản đồ di chuyển, lấy tọa độ trung tâm và cập nhật vị trí
                GeoPoint centerPoint = (GeoPoint) binding.map.getMapCenter();
                updateMarker(centerPoint);
                showAddress(centerPoint);
                return false;
            }
            @Override
            public boolean onZoom(ZoomEvent event) {
                return false; // Bạn có thể xử lý sự kiện zoom nếu cần
            }

        });

    }

    private void getLastLocation() {
        double latitude = 20.9654502;
        double longitude = 105.8397535;
        GeoPoint geoPoint = new GeoPoint(latitude, longitude);

        binding.map.getController().setCenter(geoPoint);
        binding.map.getController().setZoom(15);

        // Thêm marker tại vị trí ban đầu
        updateMarker(geoPoint);

        // Hiển thị địa chỉ cho vị trí ban đầu
        showAddress(geoPoint);
    }

    private void updateMarker(GeoPoint geoPoint) {
        if (currentMarker != null) {
            // Nếu marker đã tồn tại, di chuyển marker đến vị trí mới
            currentMarker.setPosition(geoPoint);
        } else {
            // Nếu chưa có marker, tạo marker mới
            currentMarker = new Marker(binding.map);
            currentMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            currentMarker.setTitle("Vị trí hiện tại");
            binding.map.getOverlays().add(currentMarker);
        }
        currentMarker.setPosition(geoPoint);
        binding.map.invalidate(); // Cập nhật bản đồ
    }

    @Override
    public void showAddress(GeoPoint geoPoint) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(geoPoint.getLatitude(), geoPoint.getLongitude(), 1);
            if (addresses != null && !addresses.isEmpty()) {
                address = addresses.get(0).getAddressLine(0);
                binding.UserAddress.setText(address);
                binding.edtStreet.setText(addresses.get(0).getThoroughfare());
                binding.edtPostCode.setText(addresses.get(0).getPostalCode());
                binding.edtApartment.setText(addresses.get(0).getFeatureName());
            }
        } catch (IOException e) {
            Log.e("Geocoder", "Lỗi lấy địa chỉ", e);
        }
    }

    @Override
    public void updateSuccess() {
        Toast.makeText(this, "Cập nhật địa chỉ thành công", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.map.onResume(); // Bắt đầu lại MapView
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.map.onPause(); // Dừng MapView
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.map.onDetach(); // Tách MapView khi kết thúc activity
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation(); // Gọi lại để lấy vị trí nếu quyền được cấp
            } else {
                Toast.makeText(this, "Quyền truy cập vị trí bị từ chối", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnSaveLocation.getId()) {
            present.saveLocation(address);
        } else if (v.getId() == binding.btnBack.getId()) {
            this.finish();
        }
    }

}
