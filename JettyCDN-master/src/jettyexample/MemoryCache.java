package jettyexample;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class MemoryCache<K, V> implements Map<K, V>, Cloneable, Serializable {

	private Hashtable<K, V> table;
	private int maxSize;

	public MemoryCache(int maxSize) {
		table = new Hashtable<K, V>();
		this.maxSize = maxSize;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		table.clear();

	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub

		return table.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return table.containsValue(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return table.entrySet();
	}

	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return table.get(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return table.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return table.keySet();
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub
		if (size() == maxSize)
			return null;
		
		return table.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		// TODO Auto-generated method stub
		table.putAll(arg0);
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return table.remove(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return table.size();
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return table.values();
	}

}
