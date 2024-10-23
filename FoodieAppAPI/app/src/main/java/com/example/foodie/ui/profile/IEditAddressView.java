package com.example.foodie.ui.profile;

import android.location.Geocoder;

import org.osmdroid.util.GeoPoint;

public interface IEditAddressView {
    void showAddress(GeoPoint geoPoint);
    void updateSuccess();
}
