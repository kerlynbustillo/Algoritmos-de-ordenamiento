/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_análisis_algoritmos;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author kerlyn
 */
public class Tarea_análisis_algoritmos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int op = 0;
        int A[];
        A = llenar_arreglo();
        //selectionSort(A);
        //System.out.println(Arrays.toString(A));
        do {
         op = menu();
         switch (op) {
         case 1:
                    
         bubbleSort(A);
         System.out.println(Arrays.toString(A));
         break;
         case 2:
         //A = llenar_arreglo();
         insertionSort(A);
         System.out.println(Arrays.toString(A));
         break;
         case 3:
         selectionSort(A);
         System.out.println(Arrays.toString(A));
         break;
         case 4:
         quickSort(A, A[0],A[A.length]);
         System.out.println(Arrays.toString(A));
         break;
         case 5:
         HEAP_SORT(A);
         System.out.println(Arrays.toString(A));
         break;
         case 6:
         mergeSort(A);
         System.out.println(Arrays.toString(A));
         break;
         case 7:
         radixSort(A);
         System.out.println(Arrays.toString(A));
         break;
         }
         } while (op == 8);

        /*long start = System.currentTimeMillis();
        // calcular tiempo transcurrido
        long end = System.currentTimeMillis();
        long res = end - start;
        System.out.println("Segundos: " + res / 1000);*/

        /*long time_start, time_end;
         time_start = System.currentTimeMillis();
         ReallyHeavyTask(); // llamamos a la tarea
         time_end = System.currentTimeMillis();
         System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");*/

    }

    static int menu() {
        Scanner e = new Scanner(System.in);
        System.out.println("----------MENU--------");
        System.out.println("1 Bubblesort");
        System.out.println("2 Insertionsort");
        System.out.println("3 Selectionsort");
        System.out.println("4 Quicksort");
        System.out.println("5 Heapsort");
        System.out.println("6 Mergesort");
        System.out.println("7 Radixsort");
        System.out.println("8 Salir");
        System.out.println("Ingrese una opción: ");
        int opc = e.nextInt();
        return opc;
    }

    static int[] llenar_arreglo() {
        Scanner e = new Scanner(System.in);
        System.out.println("Tamaño del arreglo: ");
        int n = e.nextInt();
        //int n = 10000000;
        Random r = new Random();
        int array[] = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = r.nextInt(n + 1);
        }
        return array;
    }

    public static void bubbleSort(int[] arr) {
        boolean swapped = true;
        int j = 0;
        int tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arr.length - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    tmp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
    }

    public static void insertionSort(int[] arr) {
        int i, j, newValue;
        for (i = 1; i < arr.length; i++) {
            newValue = arr[i];
            j = i;
            while (j > 0 && arr[j - 1] > newValue) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = newValue;
        }
    }

    public static void selectionSort(int[] arr) {
        int i, j, minIndex, tmp;
        int n = arr.length;
        for (i = 0; i < n - 1; i++) {
            minIndex = i;
            for (j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }

    static int partition(int arr[], int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        };

        return i;
    }

    public static void quickSort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }
    //TODO LO DEL HEAPSORT
    public static int heapSize;

    public static int LEFT(int i) {
        //returns left index of a zero index based array
        return 2 * i + 1;
    }

    public static int RIGHT(int i) {
        //returns right index of a zero based array
        return 2 * i + 2;
    }

    public static void BUILD_MAX_HEAP(int A[]) {
        heapSize = A.length;//heap size initialised
        for (int i = A.length / 2; i >= 0; i--)//since n/2, n/2+1 ... are leaves we can start from n/2 step downwards
        {
            //call MAX_HEAPIFY on each root node starting from n/2
            MAX_HEAPIFY(A, i);
        }
    }

    public static void MAX_HEAPIFY(int A[], int i) {
        int l = LEFT(i);//the left element's index which is 2*i+1 (for zero based indexed array)
        int r = RIGHT(i);//right index = 2*i+2;
        int largestElementIndex = -1;//index can't be negative so initialise largest index , it will be used later
        //heapSize is the current size of the heap being worked upon
        //check if left index lies within the heap.
        //if element at left index is greater than root(A[i]) element, max heap property is violated
        //copy the index of violated element in largestElementIndex
        if (l < heapSize && A[l] > A[i]) {
            largestElementIndex = l;
        } else //if max heap property is not violated copy the root's index in largestElementIndex
        {
            largestElementIndex = i;
        }
        //check to see the right sub tree for max heap property violation
        //here the largestElementIndex is calculated from previous step
        if (r < heapSize && A[r] > A[largestElementIndex]) {
            largestElementIndex = r;
        }
        //if root doesn't has the largest index then swap the largest element with root element

        if (largestElementIndex != i) {
            int temp = A[i];
            A[i] = A[largestElementIndex];
            A[largestElementIndex] = temp;
            //after swap, recursively call the MAX_HEAPIFY on the largest index(root element)
            MAX_HEAPIFY(A, largestElementIndex);
        }

    }

    public static void HEAP_SORT(int A[]) {
        //max heap is built with heapSize initialised
        BUILD_MAX_HEAP(A);
        //starting from end loop through entire array
        for (int i = A.length - 1; i >= 0; i--) {
            //since heap is already heapified and maintains max heap property
            //the first element of the array is root of max heap
            //swap it with the extreme element of the array i.e. setting the largest element to the end of array
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            //reduce the heap window by 1
            heapSize = heapSize - 1;
            //call max heapify on the reduced heap
            MAX_HEAPIFY(A, 0);
        }
    }

    //TODO LO DEL MERGE SORT
    public static void mergeSort(int a[]) {
        int tmpArray[] = new int[a.length];

        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    private static void mergeSort(int a[], int tmpArray[], int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    private static void merge(int a[], int tmpArray[], int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[ leftPos] < (a[ rightPos])) {
                tmpArray[ tmpPos++] = a[ leftPos++];
            } else {
                tmpArray[ tmpPos++] = a[ rightPos++];
            }
        }
        while (leftPos <= leftEnd) {    // Copy rest of first half
            tmpArray[ tmpPos++] = a[ leftPos++];
        }
        while (rightPos <= rightEnd) {  // Copy rest of right half
            tmpArray[ tmpPos++] = a[ rightPos++];
        }
        // Copy TmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a[ rightEnd] = tmpArray[ rightEnd];
        }
    }

    //TODO LO DEL RADIX SORT
    public static void radixSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int[][] np = new int[arr.length][2];
        int[] q = new int[0x100];
        int i, j, k, l, f = 0;
        for (k = 0; k < 4; k++) {
            for (i = 0; i < (np.length - 1); i++) {
                np[i][1] = i + 1;
            }
            np[i][1] = -1;
            for (i = 0; i < q.length; i++) {
                q[i] = -1;
            }
            for (f = i = 0; i < arr.length; i++) {
                j = ((0xFF << (k << 3)) & arr[i]) >> (k << 3);
                if (q[j] == -1) {
                    l = q[j] = f;
                } else {
                    l = q[j];
                    while (np[l][1] != -1) {
                        l = np[l][1];
                    }
                    np[l][1] = f;
                    l = np[l][1];
                }
                f = np[f][1];
                np[l][0] = arr[i];
                np[l][1] = -1;
            }
            for (l = q[i = j = 0]; i < 0x100; i++) {
                for (l = q[i]; l != -1; l = np[l][1]) {
                    arr[j++] = np[l][0];
                }
            }
        }
    }
}
