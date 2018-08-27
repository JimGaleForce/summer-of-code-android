package com.galeforce.quake1;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class USGSService extends AsyncTask<Void,Void,Void> {

    StringBuffer data = new StringBuffer();

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.txt.setText(data.toString());
    }
}
