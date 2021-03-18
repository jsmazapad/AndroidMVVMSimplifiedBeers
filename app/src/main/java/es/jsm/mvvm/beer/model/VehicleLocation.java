package es.jsm.mvvm.beer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VehicleLocation implements Parcelable{

    private double latitude;
    private double longitude;
    private String address;

    public VehicleLocation(double latitude, double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    protected VehicleLocation(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        address = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(address);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VehicleLocation> CREATOR = new Parcelable.Creator<VehicleLocation>() {
        @Override
        public VehicleLocation createFromParcel(Parcel in) {
            return new VehicleLocation(in);
        }

        @Override
        public VehicleLocation[] newArray(int size) {
            return new VehicleLocation[size];
        }
    };
}
