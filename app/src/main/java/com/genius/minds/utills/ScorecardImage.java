package com.genius.minds.utills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.genius.minds.Config.MyBaseUrl.SCORECARD_IMAGE;

public class ScorecardImage extends AppCompatActivity {

    String category,subcat,matchcode;
    ImageView img;
//    String path="https://geniusbetting.in/admin/upload/";
    String path="https://pragatisoulutions.com/geniusbetting/upload";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scorecard_image);
        img=(ImageView)findViewById(R.id.scoreimage);
       // img.setBackgroundResource(R.drawable.logo);
        category=getIntent().getStringExtra("category_new");
        subcat=getIntent().getStringExtra("SubCategory_new");
        matchcode=getIntent().getStringExtra("matchcodes_new");


        getData();
    }
    private void getData()
    {
        Log.d("ScorecardImage Url",SCORECARD_IMAGE+matchcode);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SCORECARD_IMAGE+matchcode,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        showData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void showData(JSONArray jsonArray){

        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
                String scoreimage=obj.getString("scoreimage");
                Picasso.get().load("https://pragatisoulutions.com/geniusbetting/admin/upload/"+scoreimage).into(img);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}