package view;

import java.util.Scanner;

public class View {
    private Scanner sc;

    public View() {
        sc = new Scanner(System.in);
    }

    public int readInt(String message) {
        System.out.println(message);
        return sc.nextInt();
    }

    public String readString(String message) {
        System.out.println(message);
        return sc.next();
    }

    public void print(String message) {
        System.out.println(message);
    }
}
