package util.service;

import java.util.HashMap;
import java.util.Map;

public enum MobilePrefix {
    BELTELECOM(24), LIFE(25), MTS_VELCOM(29), MTS(33), VELCOM(44);

    private final int id;
    private static Map<Integer, MobilePrefix> map =
            new HashMap<Integer, MobilePrefix>();
    
    private MobilePrefix(int id) {
        this.id = id;
    }

    static {
        for (MobilePrefix pref : MobilePrefix.values()) {
            map.put(pref.id, pref);
        }
    }
    
    public int getId() {
        return id;
    }

    public static MobilePrefix valueOf(int id) {
        return map.get(id);
    }
}
