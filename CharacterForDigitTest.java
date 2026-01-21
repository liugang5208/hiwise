public class CharacterForDigitTest {
    public static void main(String[] args) {
        System.out.println("Character.forDigit 方法中 radix 参数的作用演示：");

        // radix = 10 (十进制)
        System.out.println("radix = 10:");
        for (int i = 0; i < 10; i++) {
            System.out.print(Character.forDigit(i, 10) + " ");
        }
        System.out.println();

        // radix = 16 (十六进制)
        System.out.println("radix = 16:");
        for (int i = 0; i < 16; i++) {
            System.out.print(Character.forDigit(i, 16) + " ");
        }
        System.out.println();

        // radix = 2 (二进制)
        System.out.println("radix = 2:");
        for (int i = 0; i < 2; i++) {
            System.out.print(Character.forDigit(i, 2) + " ");
        }
        System.out.println();

        // radix = 8 (八进制)
        System.out.println("radix = 8:");
        for (int i = 0; i < 8; i++) {
            System.out.print(Character.forDigit(i, 8) + " ");
        }
        System.out.println();

        // radix = 36
        System.out.println("radix = 36:");
        for (int i = 0; i < 36; i++) {
            System.out.print(Character.forDigit(i, 36) + " ");
        }
        System.out.println();
    }
}

