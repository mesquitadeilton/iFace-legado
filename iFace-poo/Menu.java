import java.io.*;

public class Menu {
    private static User login() throws IOException, InterruptedException {
        Main.clear();
        System.out.print("Email: ");
        String email = Main.input.next();
        User user = Main.search(Main.users, email);
        if(user != null) {
            System.out.print("Senha: ");
            String password = Main.input.next();
            
            Main.clear();
            if(user.getPassword().equals(password))
                return user;
        }

        Main.clear();
        System.out.println("USUÁRIO NÃO ENCONTRADO");

        return null;
    }

    private static void createAccount() throws IOException, InterruptedException {
        Main.clear();
        System.out.print("Nome: ");
        String name = Main.input.next();
        Main.clear();

        System.out.println("Olá "+name);
        System.out.print("Email: ");
        String email = Main.input.next();
        if(Main.search(Main.users, email) == null) {
            System.out.print("Senha: ");
            String password = Main.input.next();

            User user = new User(name, email, password);
            Main.users.add(user);

            Main.clear();
            System.out.println("CONTA CRIADA");
        }
        else {
            Main.clear();
            System.out.println("USUÁRIO JÁ CADASTRADO");
        }
    }

    public void print() throws IOException, InterruptedException {
        int option;
        do {
            System.out.println("------------------------------");
            System.out.println("      Bem-vindo ao iFace      ");
            System.out.println("------------------------------");
            System.out.println("|1| Fazer login");
            System.out.println("|2| Criar conta");
            System.out.println("|0| Sair");
            System.out.println();
            System.out.print("> ");

            option = Main.input.nextInt();
            switch(option) {
                case 1:
                    User user = login();
                    if(user != null) {
                        Profile profile = new Profile(user);
                        profile.menu();
                    }
                    break;
                case 2:
                    createAccount();
                    break;
                default:
                    Main.clear();
            }
        } while(option != 0);
    }
}