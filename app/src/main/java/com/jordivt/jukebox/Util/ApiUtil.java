package com.jordivt.jukebox.Util;

import java.util.HashMap;
import java.util.Map;

public class ApiUtil {

    public static Map<String,String> getQueryMap() {
        Map<String,String> map = new HashMap<>();
        map.put("id", "909253");
        map.put("entity", "album");
        return map;
    }
}
