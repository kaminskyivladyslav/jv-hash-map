package core.basesyntax;

import java.util.Map;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;
    private int size;
    private int capacity;

    public MyHashMap() {
        this.table = new Node[capacity];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void put(K key, V value) {
            int bucket = hashCode(key) & (capacity - 1);
        Node<K, V> node = new Node<>(hashCode(key), key, value, null);
        if (table[bucket] == null) {
            table[bucket] = node;
            size++;
        } else {
            Node<K, V> prev = table[bucket];
            while (prev != null ) {
                if (prev.key == null ? key == null : prev.key.equals(key)) {
                    prev.value = value;
                    break;
                } else if (prev.next == null) {
                    prev.next = node;
                    size++;
                    break;
                }
                prev = prev.next;
            }
        }
        resize();
    }



    @Override
    public V getValue(K key) {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    private int hashCode(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode();
    }

    private boolean checkSize(int size) {
        return size > (capacity * LOAD_FACTOR);
    }

    private void resize() {
        if (size > (capacity * LOAD_FACTOR)) {
            capacity *= 2;
            Node<K, V>[] oldTable = table;
            table = new Node[capacity];
            for (Node<K, V> node : oldTable) {

            }
        }
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }


        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

    }

}

