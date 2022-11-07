package class01.class01.test;

/**
 * @Author duanyixuan
 * @Date 2022/11/7 5:13 PM
 */
public class SelectionSort {
    /**
     * 选择排序
     * 按照从大到小排序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int maxValue = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[maxValue]) {
                    maxValue = j;
                }
            }
            swap(arr, i, maxValue);

        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 6, 81, 290, 1, 56, 89};
        printArray(arr);
        selectionSort(arr);
        System.out.println(" ");
        printArray(arr);


    }
}
