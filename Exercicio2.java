import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Exercicio2 {

    // Classe auxiliar (Tupla) conforme a dica [cite: 7]
    static class ArrayEntry implements Comparable<ArrayEntry> {
        int value;
        int listIndex; // De qual lista veio
        int elementIndex; // Índice dentro da lista original

        public ArrayEntry(int value, int listIndex, int elementIndex) {
            this.value = value;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(ArrayEntry other) {
            return Integer.compare(this.value, other.value);
        }
    }

    public static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>();

        // 1. Adiciona o primeiro elemento de cada lista ao Heap
        for (int i = 0; i < lists.size(); i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.add(new ArrayEntry(lists.get(i).get(0), i, 0));
            }
        }

        // 2. Processa o Heap
        while (!minHeap.isEmpty()) {
            ArrayEntry current = minHeap.poll();
            result.add(current.value);

            // Pega o próximo elemento da mesma lista de onde veio o 'current'
            int nextIndex = current.elementIndex + 1;
            List<Integer> sourceList = lists.get(current.listIndex);

            if (nextIndex < sourceList.size()) {
                minHeap.add(new ArrayEntry(sourceList.get(nextIndex), current.listIndex, nextIndex));
            }
        }

        return result;
    }
}