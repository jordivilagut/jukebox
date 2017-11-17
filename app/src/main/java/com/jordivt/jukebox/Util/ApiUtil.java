package com.jordivt.jukebox.Util;

import android.content.Context;
import android.widget.Toast;

import com.jordivt.jukebox.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;

public class ApiUtil {

    public static Map<String,String> getQueryMap(String id, String entity) {
        Map<String,String> map = new HashMap<>();
        map.put("id", id);
        map.put("entity", entity);
        return map;
    }

    public static String parseErrorMessage(Context context, Response response) {
        String errorMessage = context.getString(R.string.server_error);
        try {
            JSONObject error = new JSONObject(response.errorBody().string());
            errorMessage = error.getString("message");
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return errorMessage;
    }

    public static void displayServerError(Context context) {
        Toast.makeText(context, R.string.server_error, Toast.LENGTH_LONG).show();
    }
}
