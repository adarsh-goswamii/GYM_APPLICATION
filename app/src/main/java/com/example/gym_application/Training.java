package com.example.gym_application;

import android.os.Parcel;
import android.os.Parcelable;

public class Training implements Parcelable
{
    private String shortDesc, longDesc, imageUrl, name;
    private int id;

    protected Training(Parcel in) {
        shortDesc = in.readString();
        longDesc = in.readString();
        imageUrl = in.readString();
        name = in.readString();
        id = in.readInt();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shortDesc);
        parcel.writeString(longDesc);
        parcel.writeString(imageUrl);
        parcel.writeString(name);
        parcel.writeInt(id);
    }

    public Training(String shortDesc, String longDesc, String imageUrl, String name, int id) {
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.imageUrl = imageUrl;
        this.name = name;
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Training{" +
                "shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
