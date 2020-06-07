package com.example.fitass;



import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;


public interface GetDb {
    @Streaming
    @GET("{url}")
    Observable<ResponseBody> downloadDB(@Path("url") String url);
}
