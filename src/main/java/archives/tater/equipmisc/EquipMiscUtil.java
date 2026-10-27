package archives.tater.equipmisc;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BiFunction;

public class EquipMiscUtil {
    private EquipMiscUtil() {}

    public static <T, U, V> BiFunction<T, U, V> weakMemoize(BiFunction<T, U, V> function) {
        var cache = new WeakHashMap<T, Map<U, V>>();
        return (t, u) -> cache.computeIfAbsent(t, _ -> new WeakHashMap<>()).computeIfAbsent(u, u1 -> function.apply(t, u1));
    }
}
