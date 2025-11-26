import java.util.PriorityQueue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DualPriorityQueue {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private Map<Integer, Integer> deletedCounts; // Rastreia removidos 
    private int totalElements;

    public DualPriorityQueue() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        deletedCounts = new HashMap<>();
        totalElements = 0;
    }

    public void insert(int value) { // [cite: 9]
        minHeap.offer(value);
        maxHeap.offer(value);
        totalElements++;
    }

    public boolean isEmpty() {
        return totalElements == 0;
    }

    // Método auxiliar para limpar o topo dos heaps se forem elementos deletados
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && deletedCounts.getOrDefault(heap.peek(), 0) > 0) {
            int val = heap.poll();
            deletedCounts.put(val, deletedCounts.get(val) - 1);
        }
    }

    public int getMax() { // [cite: 10]
        prune(maxHeap);
        if (maxHeap.isEmpty()) throw new IllegalStateException("Queue is empty");
        return maxHeap.peek();
    }

    public int getMin() { // [cite: 10]
        prune(minHeap);
        if (minHeap.isEmpty()) throw new IllegalStateException("Queue is empty");
        return minHeap.peek();
    }

    public int removeMax() { // [cite: 11]
        prune(maxHeap);
        if (maxHeap.isEmpty()) throw new IllegalStateException("Queue is empty");
        
        int max = maxHeap.poll();
        // "Removemos" logicamente do minHeap incrementando o contador de deletados
        // O minHeap será limpo quando este elemento chegar ao topo dele (via prune)
        deletedCounts.put(max, deletedCounts.getOrDefault(max, 0) + 1); // Marca para o minHeap
        totalElements--;
        return max;
    }

    public int removeMin() { // 
        prune(minHeap);
        if (minHeap.isEmpty()) throw new IllegalStateException("Queue is empty");

        int min = minHeap.poll();
        // Marca para o maxHeap
        deletedCounts.put(min, deletedCounts.getOrDefault(min, 0) + 1); 
        totalElements--;
        return min;
    }
}