package com.galeforce.quake1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class USGSService extends AsyncTask<Void,Void,Void> {

    StringBuffer data = new StringBuffer();
    List<Quake> quakes = new ArrayList<Quake>();
    Context context;

    public USGSService(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-08-19&endtime=2018-09-09&minmagnitude=6&minlatitude=-30&maxlatitude=-10&minlongitude=-180&maxlongitude=-170");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while (line != null) {
                line = reader.readLine();
                data.append(line);
            }

            JSONObject top = new JSONObject(data.toString());

            JSONArray features = top.getJSONArray("features");
            for(int i=0;i<features.length();i++) {
                JSONObject quake = ((JSONObject) features.get(i)).getJSONObject("properties");
                quakes.add(new Quake(quake.getString("title"), quake.getString("url")));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.list.setAdapter(new QuakeAdapter(context, R.layout.quake_listview, quakes));
    }
}
