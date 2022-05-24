import java.io.*;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);

    static ArrayList<User> users = new ArrayList<User>();
    static ArrayList<Community> communities = new ArrayList<Community>();

    public static <T extends Interface1>T search(ArrayList<T> list, String key) {
        for(T element : list) 
            if(element.getKey().equals(key)) return element;

        return null;
    }

    public static Chat searchChat(User connected, User user) {
        for(Chat chat : connected.getChats()) {
            if(chat.getUsers()[0].equals(connected) || chat.getUsers()[1].equals(connected))
                if(chat.getUsers()[0].equals(user) || chat.getUsers()[1].equals(user))
                    return chat;
        }
        
        return null;
    }

    public static void clear() throws IOException, InterruptedException {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            new ProcessBuilder("clear").inheritIO().start().waitFor();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        clear();

        Menu menu = new Menu();
        menu.print();
    }
}