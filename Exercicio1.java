import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Exercicio1 {
    
    public static List<Integer> findKthLargest(int[] arr, int k) {
        // Min-Heap (Padrão do Java)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : arr) {
            minHeap.add(num);
            
            // Se o tamanho exceder K, removemos o menor elemento presente
            // Isso garante que o heap contenha apenas os K maiores vistos até agora
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // O requisito pede para retornar uma lista [cite: 3]
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        int[] input = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(input, 2)); // Esperado: [5, 6] (ordem pode variar)
    }
}