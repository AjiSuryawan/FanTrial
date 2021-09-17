package com.example.fantrial;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    void getDataApi(){
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/now_playing?api_key=6ac7a042ac3b7599a689eb943fa0b6d0&language=en-US")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray result = response.getJSONArray("results");
                            for (int i = 0; i < result.length(); i++) {
                                MovieModel movieModel = new MovieModel();
                                JSONObject movieobject = result.getJSONObject(i);
                                movieModel.setOriginal_title(movieobject.getString("original_title"));
                                movieModel.setOverview(movieobject.getString("overview"));
                                movieModel.setRelease_date(movieobject.getString("release_date"));
                                movieModel.setPoster_path(movieobject.getString("poster_path"));
                                // harus di add ke Arraylist
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataApi();
    }
}