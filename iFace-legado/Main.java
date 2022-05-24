import java.io.*;
import java.util.*;

public class Main {
    static Scanner input = new Scanner(System.in);

    static ArrayList<User> list = new ArrayList<User>();

    static void clear() throws IOException, InterruptedException{
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            new ProcessBuilder("clear").inheritIO().start().waitFor();
    }

    static void edit_perfil_name(User connected) {
        System.out.print("Informe o novo nome: ");
        connected.setName(input.next());
    }
    
    static void edit_perfil_lastname(User connected) {
        System.out.print("Informe o novo sobrenome: ");
        connected.setLastName(input.next());
    }
    
    static void edit_perfil_login(User connected) {
        System.out.print("Informe o novo login: ");
        connected.setLogin(input.next()+"@iface.com");
    }
    
    static void edit_perfil_password(User connected) {
        System.out.print("Informe a nova senha: ");
        connected.setPassword(input.next());
    }

    static void edit_perfil(User connected) throws IOException, InterruptedException{
        clear();

        System.out.println("|1| Nome");
        System.out.println("|2| Sobrenome");
        System.out.println("|3| Login");
        System.out.println("|4| Senha");
        System.out.println("|0| Voltar");
        System.out.println();

        int option = input.nextInt();
        System.out.println();
        switch(option) {
            case 1:
                edit_perfil_name(connected);
                break;
            case 2:
                edit_perfil_lastname(connected);
                break;
            case 3:
                edit_perfil_login(connected);
                break;
            case 4:
                edit_perfil_password(connected);
                break;
            case 0:
                return;
        }
    }

    static void delete_account(User connected) {
        connected.setStatus(false);
    }

    static void perfil(User connected) throws IOException, InterruptedException {
        clear();
        
        System.out.println("Nome: "+connected.getName());
        String lastName = (connected.getLastName() == null) ? "" : connected.getLastName();
        System.out.println("Sobrenome: "+lastName);
        System.out.println("Login: "+connected.getLogin());
        System.out.println();

        System.out.println("|1| Editar perfil");
        System.out.println("|2| Remover contar");
        System.out.println("|0| Voltar");
        System.out.println();

        int option = input.nextInt();
        switch(option) {
            case 1:
                edit_perfil(connected);    
                break;
            case 2:
                delete_account(connected);
                return;
            case 0:
                return;
        }
    }
    
    static void friends_add(User connected) throws IOException, InterruptedException {
        System.out.print("Buscar pelo login: ");
        String name = input.next();

        boolean find = false;
        int i = 0;
        for(User user : list) {
            if(name.equals(list.get(i).getLogin())) {
                find = true;
                break;
            }
            i++;
        }

        clear();

        if(find == true) {
            list.get(i).setInvitation(connected);

            System.out.print("Convite enviado para "+list.get(i).getName());
            String lastName = (list.get(i).getLastName() == null) ? "" : list.get(i).getLastName();
            System.out.print(" "+lastName);
            System.out.println();
        }
        else System.err.println("USUÁRIO NÃO ENCONTRADO");

        System.out.println("----------------------------------------");
    }
    
    static void pending(User connected) throws IOException, InterruptedException {
        clear();

        connected.getInvitationsName();

        System.out.println("|0| Voltar");
        System.out.println();

        int i = input.nextInt();
        
        if(i == 0) {
            clear();
            return;
        }
        else {
            User pending = connected.getInvitation(i-1);

            System.out.println();
            System.out.print("Convite de "+pending.getName());
            String lastName = (pending.getLastName() == null) ? "" : pending.getLastName();
            System.out.println(" "+lastName);
            System.out.println();
            
            System.out.println("|1| Aceitar");
            System.out.println("|2| Recusar");
            System.out.println("|0| Voltar");

            int option = input.nextInt();
            switch(option) {
                case 1:
                    connected.setFriend(pending);
                    pending.setFriend(connected);
                    connected.removeInvitation(i-1);
                    break;
                case 2:
                    connected.removeInvitation(i-1);
                    break;
                case 0:
                    clear();
                    return;
            }
        }
    }

    static void friends(User connected) throws IOException, InterruptedException{
        clear();

        while(true) {
            connected.getFriendsName();
            
            System.out.println();
            System.out.println("|1| Adicionar amigo");
            if(connected.getInvitations())
                System.out.println("|2| Solicitação de amizade");
            System.out.println("|0| Voltar");
            System.out.println();

            int option = input.nextInt();
            System.out.println();
            switch(option) {
                case 1:
                    friends_add(connected);
                    break;
                case 2:
                    pending(connected);
                    clear();
                    break;
                case 0:
                    clear();
                    return;
            }
        }
    }

    static void message_send(User connected) throws IOException, InterruptedException{
        System.out.println();
        System.out.println("Informe o usuário");
        System.out.println();
        System.out.print("Enviar para: ");
        String receiver = input.next();

        boolean find = false;
        int i = 0;
        for(User u : list) {
            if(receiver.equals(list.get(i).getLogin())) {
                find = true;
                break;
            }
            i++;
        }

        if(find == false) {
            clear();
            System.err.println("USUÁRIO NÃO ENCONTRADO");
        }
        else {
            input.nextLine();
            System.out.print("Mensagem: ");
            String message = input.nextLine();

            list.get(i).setMessage(connected, message);

            clear();
            System.out.println("Mensagem enviada para "+list.get(i).getName());
        }
        System.out.println("------------------------------");
    }

    static void messages(User connected) throws IOException, InterruptedException {
        clear();
        while(true) {
            connected.getChat();

            System.out.println("|1| Enviar mensagem");
            System.out.println("|0| Voltar");
            System.out.println();

            int option = input.nextInt();
            switch(option) {
                case 1:
                    message_send(connected);
                    break;
                case 0:
                    return;
            }
        }
    }

    static void communities_create(User connected) {
        System.out.println();
        System.out.println("Criando comunidade");
        System.out.println();
        System.out.print("Nome: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.print("Descrição: ");
        String description = input.nextLine();

        connected.setComunity(connected, name, description);
    }

    static void communities(User connected) throws IOException, InterruptedException{
        while(true) {
            clear();

            connected.getComunititesName();
            System.out.println();

            System.out.println("|1| Adcionar comunidade");
            System.out.println("|2| Criar comunidade");
            System.out.println("|0| Voltar");

            int option = input.nextInt();
            switch(option) {
                case 1:
                    //communities_add(connected);
                    break;
                case 2:
                    communities_create(connected);
                    break;
                case 0:
                    return;
            }
        }
    }

    static void feed_post(User connected) throws IOException, InterruptedException{
        input.nextLine();
        System.out.println();
        System.out.print("Publicar: ");
        String post = input.nextLine();

        connected.setFeed(post);
    }

    static void feed(User connected) throws IOException, InterruptedException{
        while(true) {
            clear();

            connected.getFeed();

            System.out.println("|1| Publicar no feed");
            System.out.println("|0| Voltar");
            System.out.println();

            int option = input.nextInt();
            switch(option) {
                case 1:
                    feed_post(connected);
                    break;
                case 0:
                    return;
            }
        }
    }

    static User login() throws IOException, InterruptedException{
        clear();
        
        System.out.print("Login: ");
        String login = input.next();

        boolean find = false;
        int i = 0;
        for(User u : list) {
            if(list.get(i).getStatus()) {
                if(login.equals(list.get(i).getLogin())) {
                    find = true;
                    break;
                }
            }
            i++;
        }

        if(find == true) {
            System.out.print("Senha: ");
            String password = input.next();

            if(password.equals(list.get(i).getPassword())){
                User connected = new User();
                connected = list.get(i);
                return connected;
            }
            else {
                clear();
                System.err.println("SENHA INCORRETA");
                return null;
            }
        }
        else {
            clear();
            System.err.println("LOGIN INCORRETO");
            return null;
        }
    }

    static void logged(User connected) throws IOException, InterruptedException{
        while(true) {
            clear();
            
            System.out.println("|1| Perfil");
            System.out.println("|2| Amigos");
            System.out.println("|3| Mensagens");
            System.out.println("|4| Comunidades");
            System.out.println("|5| Feed");
            System.out.println("|0| Sair");
            System.out.println();

            int option = input.nextInt();
            switch(option) {
                case 1:
                    perfil(connected);
                    break;
                case 2:
                    friends(connected);
                    break;
                case 3:
                    messages(connected);
                    break;
                case 4:
                    communities(connected);
                    break;
                case 5:
                    feed(connected);
                    break;
                case 0:
                    clear();
                    return;
            }
        }
    }

    static void create() throws IOException, InterruptedException {
        clear();

        User user = new User();
        
        System.out.print("Informe o seu nome: ");
        String name = input.next();
        user.setName(name);
        clear();

        System.out.println("Olá "+user.getName());
        System.out.print("Cadastre o login: ");
        String login = input.next();

        boolean find = false;
        int i = 0;
        for(User u : list) {
            if(list.get(i).getLogin().equals(login+"@iface.com")) {
                find = true;
                break;
            }
            i++;
        }

        if(find == true) {
            clear();
            System.err.println("LOGIN JÁ EXISTE");
        }
        else {
            user.setLogin(login+"@iface.com");
            System.out.println("Seu login é "+user.getLogin());
            System.out.println();

            System.out.print("Cadastre a Senha: ");
            String password = input.next();
            user.setPassword(password);

            list.add(user);

            clear();
            System.out.println("CONTA CRIADA");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        clear();
        
        while(true) {
            System.out.println("------------------------");
            System.out.println("   Bem-vindo ao iFace   ");
            System.out.println("------------------------");

            System.out.println("|1| Login");
            System.out.println("|2| Criar conta");
            System.out.println("|0| Sair");
            System.out.println();

            int option = input.nextInt();
            switch(option) {
                case 1:
                    User connected = login();
                    if(connected != null) logged(connected);
                    break;
                case 2:
                    create();
                    break;
                case 3:
                    int i = 0;
                    for(User u : list) {
                        if(list.get(i).getStatus())
                            System.out.println(list.get(i).getName());
                        i++;
                    }
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
