package com.assesment.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  Row object of th webservice
 *  Holds three string variable one is for title another one for description and the last one for image url
 *
 *  Sample row value
 *
 *
 *  {
 "title":"Flag",
 "description":null,
 "imageHref":"http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png"
 }
 */
public class Row implements Parcelable{

    String title;
    String description;
    String imageHref;

    protected Row(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageHref = in.readString();
    }

    public static final Creator<Row> CREATOR = new Creator<Row>() {
        @Override
        public Row createFromParcel(Parcel in) {
            return new Row(in);
        }

        @Override
        public Row[] newArray(int size) {
            return new Row[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageHref);
    }
}
