package nazanintorabigoudarzi.detf.android.com.mythirdapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nazanintorabigoudarzi.detf.android.com.RecyclerItemClickListener;

import static nazanintorabigoudarzi.detf.android.com.mythirdapp.Full_Information.setCounter;

public class MainActivity extends AppCompatActivity {

    public static String[] title = new String[1000];
    public static String[] tag = new String[1000];
    public static String[] image = new String[1000];
    public static String[] name = new String[1000];
    public static int[] score = new int[1000];
    public static int[] VC = new int[1000];
   public static String[] link = new String[1000];



    private static final String URL_DATA = "http://api.stackexchange.com/2.2/questions?page=1&pagesize=10&order=desc&sort=activity&site=stackoverflow";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
  private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();


        loadRecyclerViewData();
        recyclerView.setAdapter(new MyAdapter(listItems,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data ... ");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
            new Response.Listener<String>() {
                @Override
                 public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("items");

                        for(int i=0;i<array.length();i++){
                            JSONObject o = array.getJSONObject(i);
                            ListItem item =new ListItem(o.getString("title"),o.getJSONArray("tags").toString().replace("\"",""),
                                    o.getJSONObject("owner").getString("profile_image"),o.getBoolean("is_answered"));
                            listItems.add(item);

                            title[i] = o.getString("title");
                            tag[i] = o.getJSONArray("tags").toString().replace("\"", "");
                            image[i] = o.getJSONObject("owner").getString("profile_image");
                            name[i] = o.getJSONObject("owner").getString("display_name");
                            score[i] = o.getInt("score");
                            VC[i]= o.getInt("view_count");
                            link[i] = o.getString("link");

                            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.ClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                    Intent intent = new Intent(MainActivity.this, Full_Information.class);
                                    startActivity(intent);
                                    //finish();
                                    setCounter(position);

                                }

                                @Override
                                public void onLongClick(View view, int position) {
                                    ;
                                }
                            }));
                        }
                        adapter = new MyAdapter(listItems,getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
        },
             new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
         });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
