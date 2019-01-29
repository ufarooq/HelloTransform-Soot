public class Test {
    public static void main(String[] args) {
        test(10, 11, 25, 33, 43,26);
    }

    private static void test(int a, int b, int c, int d, int e, int f) {
        a = b * c;
        d = b;
        b = b +1;
        e = d * c;
        f = b * c;
    }

}