package bobcorn.twilightopia;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Assorted common utility code
 */
public final class ModUtil {
	public static <T> T _null() {
		return null;
	}

	public static <K, V> Set<K> getKeysByLoop(Map<K, V> map, V value) {
		Set<K> set = Sets.newHashSet();
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (Objects.equals(entry.getValue(), value)) {
				set.add(entry.getKey());
			}
		}
		return set;
	}
}
