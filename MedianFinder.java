import java.util.PriorityQueue;
import java.util.Collections;

public class MedianFinder { // [cite: 16]

    private PriorityQueue<Integer> lowerHalf; // Max-Heap
    private PriorityQueue<Integer> upperHalf; // Min-Heap

    public MedianFinder() {
        // Metade inferior usa Max-Heap para termos acesso rápido ao maior dessa metade
        lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
        // Metade superior usa Min-Heap para termos acesso rápido ao menor dessa metade
        upperHalf = new PriorityQueue<>();
    }

    public void addNum(int num) { // [cite: 16]
        // 1. Adiciona no lowerHalf primeiro
        lowerHalf.offer(num);

        // 2. Move o maior do lowerHalf para o upperHalf (para manter a ordenação relativa)
        upperHalf.offer(lowerHalf.poll());

        // 3. Rebalanceamento: O lowerHalf pode ter no máximo 1 elemento a mais que o upperHalf, 
        // mas o upperHalf não deve ter mais elementos que o lowerHalf.
        if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }

    public double findMedian() { // [cite: 17]
        // Se o lowerHalf tem mais elementos, a mediana é o topo dele (ímpar)
        if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek();
        } 
        // Se tamanhos iguais, é a média dos topos (par)
        else {
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        }
    }
    
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian()); // 1.5
        mf.addNum(3); 
        System.out.println(mf.findMedian()); // 2.0
    }
}