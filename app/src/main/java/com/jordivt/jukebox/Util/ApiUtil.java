package com.jordivt.jukebox.Util;

import java.util.HashMap;
import java.util.Map;

public class ApiUtil {

    public static Map<String,String> getQueryMap(String id, String entity) {
        Map<String,String> map = new HashMap<>();
        map.put("id", id);
        map.put("entity", entity);
        return map;
    }
}
