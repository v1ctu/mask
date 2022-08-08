package me.v1ctu.mask;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MaskCache {

    private final Map<String, Mask> masks = new ConcurrentHashMap<>();

    public void put(String name, Mask mask) {
        masks.put(name, mask);
    }

    public void remove(String name) {
        masks.remove(name);
    }

    public Mask get(String name) {
        return masks.get(name);
    }

    public Collection<Mask> get() {
        return masks.values();
    }

    public boolean contains(String name) {
        return masks.containsKey(name);
    }
}
