package comp352_a3;

import java.util.Arrays;

public class SmarterPQ<K extends Comparable<K>, V> {
    private static int default_size = 1;
    private Entry<K, V>[] heap;
    private int size;
    private boolean isMinHeap;

    public SmarterPQ() {
        heap = new Entry[default_size];
        size = 0;
        isMinHeap = true; // default to min-heap
    }


    
    public Entry<K, V> insert(K key, V value) {
    	checkCapacity();
        Entry<K, V> entry = new Entry<>(key, value);
        heap[size] = entry;
        size++;
        upHeap(size - 1);
        return entry;
    }

    public Entry<K, V> top() {
        if (isEmpty()) {
            return null;
        }
        return heap[0];
    }
    
    public Entry<K, V> removeTop() {
        if (isEmpty()) {
            return null;
        }
        Entry<K, V> top = heap[0];
        heap[0] = heap[size - 1];
        size--;
        downHeap(0);
        return top;
    }

    public Entry<K, V> remove(Entry<K, V> entry) {
        int index = findEntry(entry);
        if (index == -1) {
            return null;
        }
        Entry<K, V> removed = heap[index];
        heap[index] = heap[size - 1];
        size--;
        downHeap(index);
        return removed;
    }
    
    public V replaceValue(Entry<K, V> entry, V newValue) {
        int index = findEntry(entry);
        if (index == -1) {
            return null;
        }
        V oldValue = heap[index].getValue();
        heap[index].setValue(newValue);
        return oldValue;
    }

    public K replaceKey(Entry<K, V> entry, K newKey) {
        int index = findEntry(entry);
        if (index == -1) {
            return null;
        }
        K oldKey = heap[index].getKey();
        heap[index].setKey(newKey);
        if (isMinHeap ? newKey.compareTo(oldKey) < 0 : newKey.compareTo(oldKey) > 0) {
            upHeap(index);
        } else {
            downHeap(index);
        }
        if (!isMinHeap ?  newKey.compareTo(oldKey) > 0 : newKey.compareTo(oldKey) < 0) {
            upHeap(index);
        } else {
            downHeap(index);
        }
        return oldKey;
    }
    
    public void toggle() {
        isMinHeap = !isMinHeap;
        rebuildHeap();
    }


    public String state() {
        return isMinHeap ? "MIN" : "MAX";
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void checkCapacity() {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    private void rebuildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            downHeap(i);
        }
    }

    private void upHeap(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap[index], heap[parentIndex])) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void downHeap(int index) {
        while (index < size / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            int smallestChild = leftChild;

            if (rightChild < size && compare(heap[rightChild], heap[leftChild])) {
                smallestChild = rightChild;
            }

            if (compare(heap[smallestChild], heap[index])) {
                swap(index, smallestChild);
                index = smallestChild;
            } else {
                break;
            }
        }
    }

    private boolean compare(Entry<K, V> e1, Entry<K, V> e2) {
        return isMinHeap ? e1.getKey().compareTo(e2.getKey()) < 0 : e1.getKey().compareTo(e2.getKey()) > 0;
    }

    private void swap(int i, int j) {
        Entry<K, V> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int findEntry(Entry<K, V> entry) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(entry)) {
                return i;
            }
        }
        return -1;
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        
    }

    public static void main(String[] args) {
    	 SmarterPQ<Integer, String> pq = new SmarterPQ<>();
         
         // Example 1: Insert Elements
         pq.insert(10, "ten");
         pq.insert(20, "twenty");
         pq.insert(5, "five");
         pq.insert(15, "fifteen");

         // Example 2: Check Top Element
         System.out.println("Top element: " + pq.top().getKey() + ", " + pq.top().getValue());

         // Example 3: Remove Top Element
         Entry temp=pq.removeTop();
         System.out.println("Removed top: " + temp.getKey() + ", " + temp.getValue());

         // Example 4: Toggle Heap Type (Min to Max)
         pq.toggle();
         System.out.println("State after toggle: " + pq.state());
         System.out.println("Top element: " + pq.top().getKey() + ", " + pq.top().getValue());

         
         // Example 5: Replace Key of an Element
         Entry<Integer, String> entry = pq.insert(40, "forty");
         pq.replaceKey(entry, 45);
         System.out.println("Top element after replacing key: " + pq.top().getKey() );

         // Example 6: Remove Specific Element
         pq.remove(entry);
         System.out.println("Top element after removal: " + pq.top().getKey() + ", " + pq.top().getValue());


         // Example 7: Check Size of the Heap
         System.out.println("Heap size: " + pq.size());

         // Example 8: Check if Heap is Empty
         System.out.println("Is heap empty? " + pq.isEmpty());


         // Example 9: Removing from Middle of Heap
         Entry<Integer, String> middleEntry = pq.insert(17, "seventeen");
         pq.insert(16, "sixteen");
         pq.remove(middleEntry);
         System.out.println("Heap size: " + pq.size());
         Entry temp1=pq.removeTop();
         System.out.println("Removed top: " + temp1.getKey() + ", " + temp1.getValue());
         Entry temp2=pq.removeTop();
         System.out.println("Removed top: " + temp2.getKey() + ", " + temp2.getValue());
         Entry temp3=pq.removeTop();
         System.out.println("Removed top: " + temp3.getKey() + ", " + temp3.getValue());
         Entry temp4=pq.removeTop();
         System.out.println("Removed top: " + temp4.getKey() + ", " + temp4.getValue());
         

    }
}
