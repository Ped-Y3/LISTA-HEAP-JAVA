public class Exercicio4 {

    public static boolean isMinHeap(int[] arr) {
        int n = arr.length;
        
        // Iteramos apenas até a metade, pois folhas não têm filhos para verificar
        // O loop para em (n / 2) - 1, que é o último nó pai.
        for (int i = 0; i <= (n / 2) - 1; i++) {
            
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            // Verifica filho da esquerda
            if (leftChild < n && arr[i] > arr[leftChild]) {
                return false; // Pai maior que filho esquerdo viola Min-Heap
            }

            // Verifica filho da direita
            if (rightChild < n && arr[i] > arr[rightChild]) {
                return false; // Pai maior que filho direito viola Min-Heap
            }
        }
        
        return true;
    }
}