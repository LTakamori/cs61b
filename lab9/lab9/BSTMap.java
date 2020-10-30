package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) == 0) {
            return p.value;
        }
        else if (key.compareTo(p.key) < 0){
            return getHelper(key, p.left);
        }
        else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null){
            size = size + 1;
            p = new Node(key, value);
            return p;
        }
        if (key.compareTo(p.key) == 0) {
            p.key = key;
            return p;
        }
        else if (key.compareTo(p.key) < 0){
            p.left = putHelper(key, value, p.left);
            return p;
        }
        else {
            p.right = putHelper(key, value, p.right);
            return p;
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V retValue = root.value;
        removeHelper(root, key, Integer retValue);
        return retValue;
    }

    /** Removes Key from the tree at node p
     *  returns the node after remove
     *  returns null if the p is null;
     *
     */
    private Node removeHelper(Node p, K key, V retValue){
        if(p == null) {
            retValue = null;
            return null;
        }
        if (key.compareTo(p.key) == 0){
            retValue = p.value;
            doRemove(p);
            return p;
        }
        else if(key.compareTo(p.key) < 0){
            p.left = removeHelper(p.left, key, retValue);
            return p;
        }
        else{
            p.right = removeHelper(p.right, key, value);
            return p;
        }
    }

    private void doRemove(Node p){
        size = size - 1;
        if (p.left == null){
            p = p.right;
        }
        else if (p.right == null){
            p = p.left;
        }
        else {
            p.key = findMinNode(p.right).key;
            p.value = findMinNode(p.right).value;
            removeHelper(p.right, p.key);
        }
    }

    private Node findMinNode (Node p){
        if (p == null) return null;
        while (p.left != null){
            p = p.left;
        }
        return p;
    }



    /** Removes the key-value  for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
