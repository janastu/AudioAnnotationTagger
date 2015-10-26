package annotation.servelots.audiotaganotation;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adarsh on 10/12/2015.
 */

public class JsonParse extends AsyncTask {

    String[] str;


    int i;
    JSONObject jsonFile = null, jsonObject;
    JSONArray jArray = null;

    public void AssignTheJson() {
        try {
            jsonFile = readJsonFromUrl("http://da.pantoto.org/api/files");
            jArray = jsonFile.getJSONArray("files");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int numberOfFiles() {
        AssignTheJson();
        return jArray.length();
    }

    public String[] StringDataGetter(String key) {
        try {
            AssignTheJson();
            str = new String[jArray.length()];
            for (i = 0; i < jArray.length(); i++) {
                jsonObject = jArray.getJSONObject(i);
                str[i] = jsonObject.get(key).toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public int TagDataSize(int i) {
        String[] stringArray = new String[100];
        try {
            AssignTheJson();
            jsonObject = jArray.getJSONObject(i);
            JSONArray temp = jsonObject.getJSONArray("tags");
            List<String> list = new ArrayList<String>();
            for (int j = 0; i < temp.length(); i++) {
                list.add(temp.getString(i));
            }
            stringArray = list.toArray(new String[list.size()]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringArray.length;
    }

    public String[] TagDataGetter(int i) {
        String[] stringArray = new String[100];
        try {
            AssignTheJson();
            jsonObject = jArray.getJSONObject(i);
            JSONArray temp = jsonObject.getJSONArray("tags");
            List<String> list = new ArrayList<String>();
            for (int j = 0; i < temp.length(); i++) {
                list.add(temp.getString(i));
            }
            stringArray = list.toArray(new String[list.size()]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringArray;
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }
}
