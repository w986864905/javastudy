package Leetcode.排序算法;

import java.util.*;

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

    public static void heapSort(int[] nums){
        //1.构建大顶堆
        for(int i=nums.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(nums,i,nums.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=nums.length-1;j>0;j--){
            //将堆顶元素与末尾元素进行交换
            swap(nums,0,j);
            //重新对堆进行调整
            adjustHeap(nums,0,j);
        }
    }

    public static void countSort(int[] nums){
        int n = nums.length;
        //先定义两个变量用来存放数组中的最大值和最小值
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++){
            if (max < nums[i]){
                max = nums[i];
            }
            if (nums[i] < min){
                min = nums[i];
            }
        }
        //定义一个长度为len的数组，这样做是为了防止数组中的最小值为1000，最大值为1010
        //这样创建一个大小为10的数组就行了，不用创建大小为1010的数组，浪费空间
        int len = max - min + 1;
        //哪个数字出现了一次，就把它的数字作为下标存起来，假如1006出现了一次，就把temp[1006-1000]加一
        int[] temp = new int[len];
        for (int value : nums) {
            temp[value - min]++;
        }
        int k = 0;
        //对temp进行遍历，temp[i]的值就是i出现的次数，加入temp[5]=3，说明(5+1000)出现了3次
        for (int i = 0; i < len; i++) {
            for (int j = temp[i]; j > 0; j--){
                nums[k] = i + min;
                k++;
            }
        }
    }

    public static void bucketSort(int[] nums){
        int n = nums.length;
        //先定义两个变量用来存放数组中的最大值和最小值
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++){
            if (max < nums[i]){
                max = nums[i];
            }
            if (nums[i] < min){
                min = nums[i];
            }
        }
        int minBuckets = min/10;
        int size = max/10-min/10+1;
        //建立桶buckets 以10个为一组
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            buckets.add(new ArrayList<>());
        }
        //将数组放入桶中
        for (int value:nums){
            buckets.get(value/10-minBuckets).add(value);
        }
        //对桶进行排序并输出到新的数组temp中
        int index = 0;
        for (ArrayList<Integer> bucket:buckets){
            if (bucket.size()>0){
                Collections.sort(bucket);
                for (int num:bucket) {
                    nums[index++] = num;
                }
            }
        }
    }

    /**
     * 基数排序
     * @param arr 数组
     * @param digit 进制
     * @param maxLen 位数
     */
    public static void radixSort(int[] arr, int digit, int maxLen) {
        int[] count = new int[digit];
        int[] temp = new int[arr.length];
        int divide = 1;

        for(int i = 0; i < maxLen; i++) {
            //每次分配之前，先把arr数值的记录拷贝一份给temp数组
            System.arraycopy(arr, 0, temp, 0, arr.length);
            //然后对桶子进行清空
            Arrays.fill(count, 0);

            //把记录分配到桶子中
            for(int j = 0; j < arr.length; j++) {
                //取出下标为j的记录的第i个关键字的值
                int tempKey = (temp[j]/divide)%digit;
                count[tempKey]++;
            }

            for(int j = 1; j < digit; j++) {
                count[j] += count[j-1];
            }

            for(int j = arr.length-1; j >= 0; j--) {
                int tempKey = (temp[j]/divide)%digit;
                count[tempKey]--;
                arr[count[tempKey]] = temp[j];
            }

            divide = digit * divide;
        }
    }

    public static void main(String[] args) {
//        int[] nums = {1,3,5,2,6,10,15,13,12,23,35,26,33};
//        radixSort(nums,10,2);
//        System.out.println(Arrays.toString(nums));
        String text = "AA10(代码表)";
        System.out.println(text.substring(0,text.indexOf('(')));
        System.out.println(text.substring(text.indexOf('(')+1,text.length()-1));
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
     * @param nums
     * @param i
     * @param length
     */
    private static void adjustHeap(int []nums,int i,int length){
        //先取出当前元素i
        int temp = nums[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for(int k=i*2+1;k<length;k=k*2+1){
            //如果左子结点小于右子结点，k指向右子结点
            if(k+1<length && nums[k]<nums[k+1]){
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if(nums[k] >temp){
                nums[i] = nums[k];
                i = k;
            }else{
                break;
            }
        }
        //将temp值放到最终的位置
        nums[i] = temp;
    }
}
