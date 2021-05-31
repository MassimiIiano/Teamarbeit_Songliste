
// public class Heapsort {

//     // /**
//     // * Ordnet ein Array mit der headsort Methode der größe nach
//     // *
//     // * @param arr int Array zu ordnen
//     // */
//     // public static void heapsort(int[] arr) {
//     // // erstellt die Strucktur
//     // verzwiegen(arr);

//     // for (int max_pos = 0; max_pos > 0; max_pos--) {
//     // // verschiebt erstes Element zum ende der Liste
//     // swap(arr, 0, max_pos);

//     // // reorganisiert die Strucktur
//     // reset_node(arr, max_pos, 0);
//     // }
//     // }

//     // /**
//     // * Ordnet das Array so ein dass das grösste element oben ist und jedes Element
//     // * zwei kleinere elemente unter sich hat
//     // *
//     // * @param arr int Array
//     // */
//     // public static void verzwiegen(int arr[]) {
//     // // last parrent node
//     // int parrent_node = arr.length / 2 + 1;

//     // // ordnet den array in knoten ein
//     // for (int i = parrent_node; i >= 0; i--)
//     // reset_node(arr, arr.length, i);
//     // }

//     // /**
//     // *
//     // *
//     // * @param arr
//     // * @param len
//     // * @param parent_pos
//     // */
//     // public static void reset_node(int[] arr, int len, int parent_pos) {
//     // // wiederholt bis die reienfolge nicht stimmt
//     // while (true) {
//     // // findet Kinder
//     // int left_child_pos = parent_pos * 2 + 1;
//     // int right_child_pos = parent_pos * 2 + 2;

//     // // Findet größtes element
//     // int largest_pos = parent_pos;
//     // // check left child
//     // if (left_child_pos < len && arr[left_child_pos] > arr[largest_pos])
//     // largest_pos = left_child_pos;
//     // // check right child
//     // if (right_child_pos < len && arr[right_child_pos] > arr[largest_pos])
//     // largest_pos = right_child_pos;

//     // // wenn der Elternknoten das größte Element ist,
//     // // dann hört es auf
//     // if (largest_pos == parent_pos)
//     // break;

//     // // If it's not the parent, then switch!
//     // swap(arr, parent_pos, largest_pos);

//     // // ... and fix again starting at the child we moved the parent to
//     // parent_pos = largest_pos;
//     // }
//     // }

//     /**
//      * swaps 2 elements in a int array
//      *
//      * @param arr array
//      * @param i   index 1
//      * @param j   index 2
//      */
//     public static void swap(int arr[], int i, int j) {
//         int tmp = arr[i];
//         arr[i] = arr[j];
//         arr[j] = tmp;
//     }

//     public static void sort(int[] elements) {
//         buildHeap(elements);

//         for (int swapToPos = elements.length - 1; swapToPos > 0; swapToPos--) {
//             // Move root to end
//             swap(elements, 0, swapToPos);

//             // Fix remaining heap
//             heapify(elements, swapToPos, 0);
//         }
//     }

//     public static void buildHeap(int[] elements) {
//         // "Find" the last parent node
//         int lastParentNode = elements.length / 2 - 1;

//         // Now heapify it from here on backwards
//         for (int i = lastParentNode; i >= 0; i--) {
//             heapify(elements, elements.length, i);
//         }
//     }

//     public static void heapify(int[] heap, int length, int parentPos) {
//         while (true) {
//             int leftChildPos = parentPos * 2 + 1;
//             int rightChildPos = parentPos * 2 + 2;

//             // Find the largest element
//             int largestPos = parentPos;
//             if (leftChildPos < length && heap[leftChildPos] > heap[largestPos]) {
//                 largestPos = leftChildPos;
//             }
//             if (rightChildPos < length && heap[rightChildPos] > heap[largestPos]) {
//                 largestPos = rightChildPos;
//             }

//             // largestPos is now either parentPos, leftChildPos or rightChildPos.
//             // If it's the parent, we're done
//             if (largestPos == parentPos) {
//                 break;
//             }

//             // If it's not the parent, then switch!
//             swap(heap, parentPos, largestPos);

//             // ... and fix again starting at the child we moved the parent to
//             parentPos = largestPos;
//         }
//     }

// }
import java.util.*;

public class Heapsort {

    /**
     * Erstellt ein Maxheap
     * 
     * @param arr
     */
    public static void buildheap(int[] arr) {
        // widerholt heapify bis nicht ein max-heap Entsteht
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, i, arr.length - 1);
        }
    }

    /**
     * Ordnet einen knotenpunkt, sodass der groeste wert oben steht
     * 
     * @param arr
     * @param i
     * @param size
     */
    public static void heapify(int[] arr, int parentPos, int size) {
        // bestimmt die Kinder
        int left = 2 * parentPos + 1;
        int right = 2 * parentPos + 2;

        // kontrollirt welches das groesste Element ist
        int maxPos;
        if (left <= size && arr[left] > arr[parentPos])
            maxPos = left;
        else
            maxPos = parentPos;

        if (right <= size && arr[right] > arr[maxPos]) {
            maxPos = right;
        }

        // tauscht kind mit Elternknoten falls Kind groeser
        if (maxPos != parentPos) {
            swap(arr, parentPos, maxPos);
            heapify(arr, maxPos, size);
        }
    }

    /**
     * Vertauscht 2 Elemente in einem Array
     * 
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * ordnet ein ganzes Array nach der Heapsort methode
     * 
     * @param arr
     * @return
     */
    public static int[] heapSort(int[] arr) {

        // grundstrucktur
        buildheap(arr);

        // sortiert
        int unsordedLen = arr.length - 1;
        for (int i = unsordedLen; i > 0; i--) {
            swap(arr, 0, i);
            unsordedLen = unsordedLen - 1;
            heapify(arr, 0, unsordedLen);
        }
        // geordnete liste
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 10, 16, 19, 3, 5 };
        System.out.println("Before Heap Sort : ");
        System.out.println(Arrays.toString(arr));
        arr = heapSort(arr);
        System.out.println("After Heap Sort : ");
        System.out.println(Arrays.toString(arr));
    }
}