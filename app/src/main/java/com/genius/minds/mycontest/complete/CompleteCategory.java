package com.genius.minds.mycontest.complete;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseCategory.CategoryListItem;
import com.genius.minds.Model.ResponseCategory.ResponseCategories;
import com.genius.minds.R;
import com.genius.minds.mycontest.complete.Adapter.AdapterComplete;
import com.genius.minds.mycontest.live.Adapter.AdapterHomeLive;
import com.genius.minds.mycontest.live.Adapter.ModelLive.LiveModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.genius.minds.Config.MyBaseUrl.COMPLETE_CATEGORY;

public class CompleteCategory extends Fragment implements SwipeRefreshLayout.OnRefreshListener  {

    List<CompleteCategoryModel> productList;
     static RecyclerView recyclerView,rec_complete;
    private ProgressBar spinner;
    SessionManager session;
    SwipeRefreshLayout mSwipeRefreshLayout ;

    String [] subcategory={"Cricket","footbal","tenish","USA","Japan"};
    String [] timeDate={"10:32 ,1/29/2022","10:32 ,1/29/2022","10:32 ,1/29/2022","10:32 ,1/29/2022","10:32 ,1/29/2022"};

    String [] category={"T20 WORLD CUP","T20 WORLD CUP","T20 WORLD CUP","T20 WORLD CUP","T20 WORLD CUP"};
    String [] matechDateTime={"India Vs Pakistan 1/29/2022","India Vs Pakistan 1/29/2022","India Vs Pakistan 1/29/2022","India Vs Pakistan 1/29/2022","India Vs Pakistan 1/29/2022"};
    String [] firstTeam={"Ind-206/5","Ind-206/5","Ind-206/5","Ind-206/5","Ind-206/5"};
    String [] SecondTeam={"Ind-206/5","Ind-206/5","Ind-206/5","Ind-206/5","Ind-206/5"};
    String [] contestnaem={"Cricket","Cricket","Cricket","Cricket","Cricket"};
    String [] date={"1/29/2022","1/29/2022","1/29/2022","1/29/2022","1/29/2022"};
    String [] fullScore={"Ind-306/5","Ind-306/5","Ind-306/5","Ind-306/5","Ind-306/5"};
    AdapterComplete adapterLive;
    ArrayList<LiveModel>liveModels;

    static AdapterComplete adapterHome;
    static List<CategoryListItem>categoryListItems= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_complete_category, container, false);
        SharedPreferences pref = this.getActivity().getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this.getActivity());
        recyclerView = v.findViewById(R.id.recylcerView);
        rec_complete = v.findViewById(R.id.rec_complete);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        productList = new ArrayList<>();
        mSwipeRefreshLayout =(SwipeRefreshLayout)v.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        productList = new ArrayList<>();
        //spinner.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                if (UtilMethods.INSTANCE.isNetworkAvailable(getContext())){
                    UtilMethods.INSTANCE.userTenderCategoryPage(getContext(),"4");

                }else{
                    UtilMethods.INSTANCE.Error(getContext(),getResources().getString(R.string.NOCONN));
                }
                loadCategory();
            }
        });




        if (UtilMethods.INSTANCE.isNetworkAvailable(getContext())){
            UtilMethods.INSTANCE.userTenderCategoryPage(getContext(),"4");

        }else{
            UtilMethods.INSTANCE.Error(getContext(),getResources().getString(R.string.NOCONN));
        }
//        RecyclerView.LayoutManager vertical = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
//        rec_complete.setLayoutManager(vertical);
//        rec_complete.setItemAnimator(new DefaultItemAnimator());
//        liveModels = new ArrayList<>();
//        for (int i = 0; i < category.length; i++) {
//            LiveModel ab = new LiveModel(category[i],subcategory[i],matechDateTime[i],firstTeam[i],SecondTeam[i],contestnaem[i],date[i],fullScore[i]);
//            liveModels.add(ab);
//        }
//        adapterLive = new AdapterComplete(liveModels,getContext());
//        rec_complete.setAdapter(adapterLive);

        return v;
    }
    @Override
    public void onRefresh() {
        // Fetching data from server
        productList.clear();
        loadCategory();
    }
    private void loadCategory() {
        mSwipeRefreshLayout.setRefreshing(true);
        User user = SharedPrefManager.getInstance(this.getActivity()).getUser();
        String email=user.getEmail();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, COMPLETE_CATEGORY+email,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new CompleteCategoryModel(
                                        contest.getInt("id"),
                                        contest.getString("cat_name"),
                                        contest.getString("cat_image")

                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            CompleteCategoryAdapter adapter = new CompleteCategoryAdapter(getActivity(), productList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            mSwipeRefreshLayout.setRefreshing(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this.getActivity()).add(stringRequest);
    }

    public static void completeCategory(Context context, String response) {

        Type type = new TypeToken<ResponseCategories>() {
        }.getType();
        ResponseCategories responseDashBoardList = new Gson().fromJson(response, type);

        categoryListItems = responseDashBoardList.getGeniusbetting().getCategoryList();
        if (categoryListItems.size() > 0) {
            adapterHome = new AdapterComplete(categoryListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rec_complete.setLayoutManager(horizontalLayoutManagaer1);
            rec_complete.setAdapter(adapterHome);

        }
    }

}