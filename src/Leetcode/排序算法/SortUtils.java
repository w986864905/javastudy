package Leetcode.排序算法;

import java.util.Arrays;
import java.util.Collection;

/**
 * @ClassName SortUtils
 * @Description 排序
 * @Author 王品峰
 * @Date 2021/3/11 11:20
 * @Version 1.0
 */
public class SortUtils {

    public static void bubbleSort(int[] nums) {
        boolean swapped;
        for (int i = 0; i < nums.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void selectionSort(int[] nums) {
        int mid;
        for (int i = 0; i < nums.length - 1; ++i) {
            mid = i;
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[j] < nums[mid]) {
                    mid = j;
                }
            }
            swap(nums, mid, i);
        }
    }

    public static void insertionSort(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; --j) {
                swap(nums, j, j - 1);
            }
        }
    }

    public static void shellSort(int[] nums) {
        //step:步长
        for (int step = nums.length / 2; step > 0; step /= 2) {
            //对一个步长区间进行比较 [step,arr.length)
            for (int i = step; i < nums.length; i++) {
                int value = nums[i];
                int j;
                //对步长区间中具体的元素进行比较
                for (j = i - step; j >= 0 && nums[j] > value; j -= step) {
                    //j为左区间的取值，j+step为右区间与左区间的对应值。
                    nums[j + step] = nums[j];
                }
                //此时step为一个负数，[j + step]为左区间上的初始交换值
                nums[j + step] = value;
            }
        }
    }

    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    public static void quickSort(int[] nums) {
        quickSort(nums,0,nums.length);
    }

    public static void heapSort(int []arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            //将堆顶元素与末尾元素进行交换
            swap(arr,0,j);
            //重新对堆进行调整
            adjustHeap(arr,0,j);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,2,6};

        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void swap(int[] nums, int a, int b) {
        int temp;
        temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private static void mergeSort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            //左右归并
            merge(nums, low, mid, high);
        }
    }

    private static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        //将a[low...mid]和a[mid+1...high]归并
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        if (temp.length >= 0) {
            System.arraycopy(temp, 0, a, low, temp.length);
        }
    }

    private static void quickSort(int[] nums,int l,int r) {
        if (l + 1 >= r) {
            return;
        }
        int first = l, last = r - 1, key = nums[first];
        while (first < last) {
            while (first < last && nums[last] >= key) {
                --last;
            }
            nums[first] = nums[last];
            while (first < last && nums[first] <= key) {
                ++first;
            }
            nums[last] = nums[first];
        }
        nums[first] = key;
        quickSort(nums, l, first);
        quickSort(nums, first + 1, r);
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    private static void adjustHeap(int []arr,int i,int length){
        //先取出当前元素i
        int temp = arr[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for(int k=i*2+1;k<length;k=k*2+1){
            //如果左子结点小于右子结点，k指向右子结点
            if(k+1<length && arr[k]<arr[k+1]){
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if(arr[k] >temp){
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        //将temp值放到最终的位置
        arr[i] = temp;
    }
}
