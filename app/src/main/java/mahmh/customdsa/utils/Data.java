package mahmh.customdsa.utils;
import java.util.HashMap;
import java.util.Map;

/** Arbitrary data model shared between the algorithms. */
public class Data {
    private final Map<String, Object> fields = new HashMap<>();

    public Data() {}

    public Data(String name) {
        set("name", name);
    }

    public void set(String key, Object value) {
        fields.put(key, value);
    }

    public Object get(String key) {
        return fields.get(key);
    }

    public boolean has(String key) {
        return fields.containsKey(key);
    }

    public Map<String, Object> asMap() {
        return fields;
    }
}