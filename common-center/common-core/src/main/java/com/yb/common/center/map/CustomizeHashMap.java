package com.yb.common.center.map;

/**
 * @author yebing
 */
public class CustomizeHashMap<K,V> implements CustomizeMap<K,V>  {
    /** 默认初始化大小*/
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    /** 默认加载因子*/
    static final float DEFAULT_LOAD_FACTOR = 0.75F;
    /** map数组 */
    EntrySet[] table = null;
    final float loadFactor;
    /** map实际大小 */
    int size = 0;
    /** map规定大小 */
    int capacity;


    public CustomizeHashMap(){
        this(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOAD_FACTOR);
    }

    public CustomizeHashMap(int initialSize,float loadFactor){
        this.capacity = initialSize;
        this.loadFactor = loadFactor;
        table = new EntrySet[capacity];
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
    public int hash(K key) {
        int h;
        return (key == null) ? 0 : Math.abs(key.hashCode())%capacity;
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
        CustomizeMap<String,String> customizeMap = new CustomizeHashMap<>();
        customizeMap.put("yebing","I Love You!");
        String yebing = customizeMap.getKey("yebing");
        System.out.print(yebing);
        int t = (4 & -2);
        System.out.println(t);
        String a = Integer.toBinaryString(4);
        String b = Integer.toBinaryString(-2);
        System.out.println(b);
    }
}
