package com.lucianpiros.bakingapp.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class used to convert Strings
 *
 * @author Lucian Piros
 * @version 1.0
 */
public class ValuesUtil {

    // Map used to beatify recipe ingredients display
    public static final Map<String, String> meassureMap;
    static
    {
        meassureMap = new HashMap<>();
        meassureMap.put("G", "grams");
        meassureMap.put("UNIT", "");
        meassureMap.put("TSP", "tea spoon");
        meassureMap.put("TBLSP", "table spoon");
        meassureMap.put("CUP", "cup");
    }
}
