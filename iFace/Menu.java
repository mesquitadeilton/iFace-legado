import java.io.*;

public class Menu {
    private static User login() throws IOException, InterruptedException {
        Main.clear();
        System.out.println("--------------------------------------------------");
        System.out.println("         Bem-vindo ao iFace | Fazer login         ");
        System.out.println("--------------------------------------------------");
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
        System.out.println("--------------------------------------------------");
        System.out.println("         Bem-vindo ao iFace | Criar conta         ");
        System.out.println("--------------------------------------------------");
        System.out.print("Nome: ");

        try {
            String name = Main.input.next();
            if(!name.matches("[A-z]+")) throw new Exception("NOME NÃO PODE TER ACENTOS OU NÚMEROS");

            
            Main.clear();
            System.out.println("--------------------------------------------------");
            System.out.println("         Bem-vindo ao iFace | Criar conta         ");
            System.out.println("--------------------------------------------------");
            System.out.println("Olá "+name);
            System.out.println();
            System.out.print("Email: ");
            String email = Main.input.next();
            if(!email.matches("^([\\w\\-]+.)*[\\w\\-]+@([\\w\\-]+.)+([\\w\\-]{2,3})")) throw new Exception("EMAIL INVALIDO");

            if(Main.search(Main.users, email) == null) {
                System.out.print("Senha: ");
                String password = Main.input.next();

                User user = new User(name, email, password);
                Main.users.add(user);

                Main.clear();
                System.out.println("Conta criada com sucesso");
            }
            else {
                Main.clear();
                System.out.println("EMAIL JÁ CADASTRADO");
            }
        } catch(Exception e) {
            Main.clear();
            System.out.println(e.getMessage());
        }
    }

    public void print() throws IOException, InterruptedException {
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("                Bem-vindo ao iFace                ");
            System.out.println("--------------------------------------------------");
            System.out.println("|1| Fazer login");
            System.out.println("|2| Criar conta");
            System.out.println("|0| Sair");
            System.out.println();
            System.out.print("> ");

            try {
                option = Integer.parseInt(Main.input.next());
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
                    case 0:
                        Main.clear();
                        return;
                    default:
                        Main.clear();
                        System.out.println("OPCAO INVALIDA");
                }
            } catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while(option != 0);
    }
}