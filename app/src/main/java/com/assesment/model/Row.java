package com.assesment.model;

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
public class Row {

    String title;
    String description;
    String imageHref;

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
}
