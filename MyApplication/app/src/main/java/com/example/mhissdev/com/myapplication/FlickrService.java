package com.example.mhissdev.com.myapplication;


/**
 * Created by Mark on 06/11/2015.
 */
public interface FlickrService {

    @GET("/services/rest/?api_key=662d8d49c73e73d13fff41f3589fe073&format=json&safe_search=1&"  +
            "content_type=1&extras=url_o&nojsoncallback=1&method=flickr.photos.search")

    void getImageForLocation(@Query("woe_id") String woe_id, Callback<FlickrResponse> cb);
}
