package com.yb.common.center.map;

public interface CustomizeMap<K,V> {
    K getKey(K k);

    V getValue(K k);

    V put(K k, V v);

    V remove(K k);

    int hash(K k);
    interface Entry<K,V>{
        K getKey(K k);

        V getValue(K k);
    }
}
