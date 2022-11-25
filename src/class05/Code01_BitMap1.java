package class05;

import java.util.HashSet;

// 这个类的实现是错误的
// 请问为什么？
public class Code01_BitMap1 {

    public static class BitMap {

        private long[] bits; //64位 long类型是64位

        //max确定数组的大小，决定申请多长的数组
        public BitMap(int max) {
            bits = new long[(max + 64) >> 6]; //等同于 (max + 64) / 64
        }


        //确定是数组中第几个数 num >> 6 -> num / 64
        //确定是数组中第几个数的第几位 num & 63 -> num % 64 num % 64 只保留后7位的结果（从左到右）和num & 63的结果一致
        //如何将第几位标记位1 1L代表从最开始的位置为1 ，1L << (num& 63) 代表1左移 (num& 63)位 然后和第几位的数进行或运算
        public void add(int num) {
            bits[num >> 6] |= (1 << (num & 63));
        }

        public void delete(int num) {
            bits[num >> 6] &= ~(1 << (num & 63));
        }

        public boolean contains(int num) {
            return (bits[num >> 6] & (1 << (num & 63))) != 0;
        }

    }

    public static void main(String[] args) {
        System.out.println("测试开始！");
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        HashSet<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

}
