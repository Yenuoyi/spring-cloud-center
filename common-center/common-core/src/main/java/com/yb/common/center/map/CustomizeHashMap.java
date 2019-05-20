package com.yb.common.center.map;

public class CustomizeHashMap<K,V> implements CustomizeMap<K,V>  {
    public int initial = 16;
    public int size = 0;
    EntrySet[] table = new EntrySet[initial];

    public CustomizeHashMap(){
        new CustomizeHashMap(initial);
    }
    public CustomizeHashMap(int initialSize){
        this.initial = initial;
    }
    @Override
    public K getKey(K k) {
        int hash = hash(k);
        EntrySet entrySet = table[hash];
        boolean nextNode = true;
        while(nextNode){
            if(entrySet.k == k){
                return k;
            }else if(entrySet.next != null){
                entrySet = entrySet.next;
            }else {
                nextNode = false;
            }
        }
        return null;
    }

    @Override
    public V getValue(K k) {
        int hash = hash(k);
        EntrySet entrySet = table[hash];
        boolean nextNode = true;
        while(nextNode){
            if(entrySet.k == k){
                return (V) entrySet.v;
            }else if(entrySet.next != null){
                entrySet = entrySet.next;
            }else {
                nextNode = false;
            }
        }
        return null;
    }

    @Override
    public V put(K k, V v){
        if(size >= initial){
            return null;
        }
        int hash = hash(k);
        EntrySet entry = table[hash];
        EntrySet entryNew = new EntrySet(k, v);
        if(entry != null){
            entryNew.next = entry;
            entry.pre = entryNew;
        }
        table[hash] = entryNew;
        ++size;
        return v;
    }

    @Override
    public V remove(K k) {
        int hash = hash(k);
        EntrySet entrySet = table[hash];
        boolean nextNode = true;
        while(nextNode){
            if(entrySet.k == k){
                EntrySet pre = entrySet.pre;
                EntrySet next = entrySet.next;
                if(pre == null && next != null){
                    table[hash] = next;
                }else if(pre == null && next == null){
                    table[hash] = null;
                }else if(pre != null && next != null){
                    pre.next = next;
                    next.pre = pre;
                }else if(pre != null && next == null){
                    pre.next = null;
                }
                return (V)entrySet.v;
            }else if(entrySet.next != null){
                entrySet = entrySet.next;
            }else {
                nextNode = false;
            }
        }
        return null;
    }

    @Override
    public int hash(K k) {
        int index = Math.abs(k.hashCode() % (initial - 1));
        return index;
    }

    public class EntrySet<K,V> implements Entry<K,V>{
        EntrySet<K,V> next;
        EntrySet<K,V> pre;
        K k;
        V v;
        EntrySet(K k,V v){
            this.k = k;
            this.v = v;
        }
        @Override
        public K getKey(K k) {
            return this.k;
        }

        @Override
        public V getValue(K k) {
            return this.v;
        }
    }

    public static void main(String[] args){
        CustomizeMap<String,String> customizeMap = new CustomizeHashMap<>(16);
        customizeMap.put("yebing","I Love You!");
        String yebing = customizeMap.getKey("yebing");
        System.out.print(yebing);

    }
}
