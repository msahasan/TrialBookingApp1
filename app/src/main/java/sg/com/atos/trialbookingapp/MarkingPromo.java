package sg.com.atos.trialbookingapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class MarkingPromo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marking_promo);

        final ListView listview = (ListView) findViewById(R.id.listView);
        final TextView textView = (TextView) findViewById(R.id.textView2);
        final ArrayList<String> list2 = new ArrayList<String>();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Log.i("List2","list2 starts..");
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray data = jsonResponse.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);
                        String PromoID = c.getString("PromoID");
                        String SourceCode = c.getString("SourceCode");
                        String MaketingDescription = c.getString("MaketingDescription");
                        //Log.i("List2",PromoID +","+ SourceCode +","+ MaketingDescription);
                        textView.setText(SourceCode);
                        list2.add(SourceCode);
                    }
                } catch (JSONException e) {
                    Log.i("List2","Exception arisen..");
                    e.printStackTrace();
                }
            }
        };
        MktgRequest mktgRequest = new MktgRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MarkingPromo.this);
        queue.add(mktgRequest);

        String[] values = new String[] { "Promo 01", "Promo 02", "Promo 03","Promo 04", "Promo 05", "Promo 06", "Promo 07"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            //Log.i("List",values[i]);
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, list2);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        //list.remove(item);
                        adapter.notifyDataSetChanged();
                        view.setAlpha(1);
                    }
                });
            }
        });
        /*
        */
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
