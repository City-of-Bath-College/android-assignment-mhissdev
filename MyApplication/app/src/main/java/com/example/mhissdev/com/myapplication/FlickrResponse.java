package com.example.mhissdev.com.myapplication;

/**
 * Created by Mark on 06/11/2015.
 */
public class FlickrResponse {

    private FlickrImage image;

    public FlickrImage getImage() {
        return image;
    }

    /* Flickr Image */
    public class FlickrImage{

        private String url_o;

        public String getUrl_o() {
            return url_o;
        }
    }
}
