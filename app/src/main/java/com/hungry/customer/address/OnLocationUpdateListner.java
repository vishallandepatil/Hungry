package com.hungry.customer.address;

import com.google.android.gms.maps.model.LatLng;

public interface OnLocationUpdateListner {

    void onLocationUpdate(LatLng latLng);
}
