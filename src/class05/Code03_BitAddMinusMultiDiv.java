package class05;

// 测试链接：https://leetcode.com/problems/divide-two-integers
public class Code03_BitAddMinusMultiDiv {

    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            //^运算是无进位相加 相同为0 不同为1
            sum = a ^ b;
            //&运算是都是1则为1，否则为0
            b = (a & b) << 1;
            a = sum;
        }
        //无进位相加的结果 + 进位信息 的结果（直到进位信息消失的时候）
        return sum;
    }

    //相反数
    public static int negNum(int n) {
        return add(~n, 1);
    }

    //a-b等同于a加上b的相反数
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }

    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a; //全部取正数
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        //x / y
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res; //补符号，如果是负数，取反
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                /*
                a / b
                (a+1) / b = c
                a - (b * c) = d
                d / b = e
                ans = c + e
                 */
                int c = div(add(a, 1), b);
                return add(c, div(minus(a, multi(c, b)), b));
            }
        } else {
            return div(a, b);
        }
    }

}
