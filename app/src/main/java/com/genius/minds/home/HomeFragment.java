package com.genius.minds.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Model.ResponseCategory.CategoryListItem;
import com.genius.minds.Model.ResponseCategory.ResponseCategories;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.genius.minds.Config.MyBaseUrl.CATEGORY;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    List<HomeViewModel> productList;
   static RecyclerView recyclerView,rec_home;
    private ProgressBar spinner;
    SwipeRefreshLayout mSwipeRefreshLayout ;
    int[] _img = {R.drawable.ttwnty, R.drawable.cover, R.drawable.ttwnty, R.drawable.cover,R.drawable.ttwnty,R.drawable.cover};
    String[] name = {"T20","T20","T20","T20","T20","T20"};
    static AdapterHome adapterHome;
    static List<CategoryListItem>categoryListItems= new ArrayList<>();
    ArrayList<ModelHome>modelHomes;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.recylcerView);
        rec_home = v.findViewById(R.id.rec_home);
        spinner=(ProgressBar)v.findViewById(R.id.progressBar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mSwipeRefreshLayout =(SwipeRefreshLayout)v.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        productList = new ArrayList<>();

        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                if (UtilMethods.INSTANCE.isNetworkAvailable(getContext())){
                    UtilMethods.INSTANCE.userTenderCategoryPage(getContext(),"1");

                }else{
                    UtilMethods.INSTANCE.Error(getContext(),getResources().getString(R.string.NOCONN));
                }
                loadCategory();
            }
        });

        if (UtilMethods.INSTANCE.isNetworkAvailable(getContext())){
            UtilMethods.INSTANCE.userTenderCategoryPage(getContext(),"1");

        }else{
            UtilMethods.INSTANCE.Error(getContext(),getResources().getString(R.string.NOCONN));
        }
        loadCategory();

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
        //productList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, CATEGORY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {


                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new HomeViewModel(
                                        contest.getInt("id"),
                                        contest.getString("cat_name"),
                                        contest.getString("cat_image")

                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            HomeCategoryAdapter adapter = new HomeCategoryAdapter(getActivity(), productList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            //spinner.setVisibility(View.GONE);
                            mSwipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
        Volley.newRequestQueue(this.getActivity()).add(stringRequest);
    }
    public static void categoryData(Context context, String response) {

        Type type = new TypeToken<ResponseCategories>() {}.getType();
        ResponseCategories responseDashBoardList = new Gson().fromJson(response, type);

        categoryListItems=responseDashBoardList.getGeniusbetting().getCategoryList();
        if (categoryListItems.size() > 0) {
            adapterHome = new AdapterHome(categoryListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            rec_home.setLayoutManager(horizontalLayoutManagaer1);
            rec_home.setAdapter(adapterHome);

        }

    }
}