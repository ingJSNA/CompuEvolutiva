package util;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

public class RandomCollectionsUtils {

	private RandomCollectionsUtils() {
	}

	public static <T> T getItem(Set<T> set) {
		int r = RandomUtils.nextInt(0, set.size());
		return (T) set.toArray()[r];
	}

	private static <K, V> V getRandomValue(Map<K, V> map) {
		K key = RandomCollectionsUtils.getItem(map.keySet());
		return map.get(key);
	}

}
