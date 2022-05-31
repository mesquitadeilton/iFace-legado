import java.io.*;

public class Profile {
    private User connected;

    public Profile(User connected) {
        this.connected = connected;
    }

    private void editProfile() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("         Editar Perfil        ");
            System.out.println("------------------------------");
            System.out.println();
            System.out.println("|1| Nome");
            System.out.println("|2| Sobrenome");
            System.out.println("|3| Email");
            System.out.println("|4| Senha");
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");
    
            try {
                option = Integer.parseInt(Main.input.next());
                switch(option) {
                    case 1:
                        System.out.println();
                        System.out.print("Nome: ");
                        String name = Main.input.next();
                        if(!name.matches("[A-z]+")) throw new Exception("NOME NÃO PODE TER ACENTOS OU NÚMEROS");

                        connected.setName(name);
                        Main.clear();
                        System.out.println("Nome alterado com sucesso");
                        System.out.println("------------------------------");
                        break;
                    case 2:
                        System.out.println();
                        System.out.print("Sobrenome: ");
                        String lastName = Main.input.next();
                        if(!lastName.matches("[A-z]+")) throw new Exception("NOME NÃO PODE TER ACENTOS OU NÚMEROS");

                        connected.setLastName(lastName);
                        Main.clear();
                        System.out.println("Sobrenome alterado com sucesso");
                        System.out.println("------------------------------");
                        break;
                    case 3:
                        System.out.println();
                        System.out.print("Email: ");
                        String email = Main.input.next();
                        if(!email.matches("^([\\w\\-]+.)*[\\w\\-]+@([\\w\\-]+.)+([\\w\\-]{2,3})")) throw new Exception("EMAIL INVALIDO");

                        if(Main.search(Main.users, email) == null) {
                            connected.setEmail(email);
                            Main.clear();
                            System.out.println("Email alterado com sucesso");
                        System.out.println("------------------------------");
                        }
                        else {
                            Main.clear();
                            System.out.println("EMAIL JÁ CADASTRADO");
                            System.out.println("------------------------------");
                        }
                        break;
                    case 4:
                        System.out.println();
                        System.out.print("Senha: ");
                        String password = Main.input.next();
                        connected.setPassowrd(password);
                        Main.clear();
                        System.out.println("Senha alterada com sucesso");
                        System.out.println("------------------------------");
                        break;
                    case 0:
                        Main.clear();
                        return;
                    default:
                        Main.clear();
                        System.out.println("OPCAO INVALIDA");
                        System.out.println("------------------------------");
                }
            } catch(NumberFormatException e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
                System.out.println("------------------------------");
            } catch(Exception e) {
                Main.clear();
                System.out.println(e.getMessage());
                System.out.println("------------------------------");
            }
        } while(option != 0);
    }

    private boolean removeAccount() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("        Encerrar conta        ");
            System.out.println("------------------------------");
            System.out.println();
            System.out.println("|1| Desejo encerrar minha conta");
            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");
                
            try {
                option = Integer.parseInt(Main.input.next());
                switch(option) {
                    case 1:
                        for(User friend : connected.getFriends())
                            if(friend.getChats().containsKey(connected)) friend.getChats().remove(connected);

                        for(User friend : connected.getFriends())  friend.getFriends().remove(connected);

                        for(Community community : connected.getCommunities()) community.getMembers().remove(connected);

                        for(Community myCommunity : connected.getMyCommunities()) {
                            for(User member : myCommunity.getMembers())
                                member.getCommunities().remove(myCommunity);
                        }

                        Main.users.remove(connected);
                        return false;
                    case 0:
                        Main.clear();
                        return true;
                    default:
                        Main.clear();
                        System.out.println("OPCAO INVALIDA");
                        System.out.println("------------------------------");
                }
            } catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
                System.out.println("------------------------------");
            }
        } while(option != 0);
        
        return true;
    }

    private boolean profile() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("            Perfil            ");
            System.out.println("------------------------------");
            System.out.println("Nome: "+connected.getName());
            System.out.println("Sobrenome: " +connected.getLastName());
            System.out.println("Email: "+connected.getEmail());
            System.out.println();

            System.out.println("|1| Editar perfil");
            System.out.println("|2| Encerrar conta do iFace");
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            try {
                option = Integer.parseInt(Main.input.next());
                switch(option) {
                    case 1:
                        editProfile();
                        break;
                    case 2:
                        if(!removeAccount()) return false;
                        break;
                    case 0:
                        Main.clear();
                        return true;
                    default:
                        Main.clear();
                        System.out.println("OPCAO INVALIDA");
                        System.out.println("------------------------------");
                }
            }
            catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
                System.out.println("------------------------------");
            }
        } while(option != 0);
        return true;
    }

    private void invitationFriend() throws IOException, InterruptedException {
        int option;
        do {
            Main.clear();
            int i = 0;
            System.out.println("Convite de: ");
            for(User user : connected.getInvitations()) {
                System.out.println("|"+(i+1)+"| "+user.getName());
                i++;
            }
            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            option = Main.input.nextInt();
            if(option != 0) {
                Main.clear();
                System.out.println(connected.getInvitations().get(option-1).getName());
                System.out.println();
                System.out.println("|1| Aceitar");
                System.out.println("|2| Recusar");
                System.out.println("|0| Voltar");
                System.out.println();
                System.out.print("> ");

                int action = Main.input.nextInt();
                Main.clear();
                switch(action) {
                    case 1:
                        connected.setFriend(connected.getInvitations().get(option-1));
                        connected.getInvitations().get(option-1).setFriend(connected);
                        connected.getInvitations().remove(option-1);
                        return;
                    case 2:
                        connected.getInvitations().remove(option-1);
                        return;
                    case 0:
                        break;
                }
            }
        } while (option != 0);
        Main.clear();
    }

    private void friends() throws IOException, InterruptedException {
        Main.clear();
        int option;
        do {
            if(!connected.getFriends().isEmpty()) {
                System.out.println("Amigos:");
                for(User u : connected.getFriends())
                    System.out.println(u.getName());
            }
            else System.out.println("Sem amigos");

            System.out.println();
            System.out.println("|1| Buscar novo amigo");
            if(!connected.getInvitations().isEmpty()) System.out.println("|2| Solicitações de amizade");
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            option = Main.input.nextInt();
            switch(option) {
                case 1:
                    System.out.println();
                    System.out.print("Buscar pelo email: ");
                    String email = Main.input.next();
                    User user = Main.search(Main.users, email);
                    if(user != null) {
                        user.setInvitation(connected);
                        Main.clear();
                        System.out.println("Convite enviado para "+user.getName());
                        System.out.println("------------------------------");
                    }
                    else {
                        Main.clear();
                        System.out.println("USUÁRIO NÃO ENCONTRADO");
                        System.out.println("------------------------------");
                    }
                    break;
                case 2:
                    invitationFriend();
                    break;
                default:
                    Main.clear();
            }
        } while(option != 0);
    }

    private void chats() throws IOException, InterruptedException {
        int option;
        do {
            Main.clear();
            if(!connected.getFriends().isEmpty()) {
                System.out.println("Chat com:");
                int i = 0;
                for(User user : connected.getFriends()) {
                    System.out.println("|"+(i+1)+"| "+user.getName());
                    i++;
                }
            }
            else System.out.println("Sem amigos");

            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");
            option = Main.input.nextInt();

            User friend = null;
            if(option !=0) friend = connected.getFriends().get(option-1);

            if((option != 0) && (friend != null)) {
                String text;
                do {
                    Main.clear();
                    System.out.println("Conversa com "+friend.getName());
                    
                    if(connected.getChats().get(friend) != null && !connected.getChats().get(friend).isEmpty()) {
                        for(Message message : connected.getChats().get(friend))
                            System.out.println(message.getSender().getName()+" disse: "+message.getText());
                    }
                    else {
                        connected.setChat(friend);
                        friend.setChat(connected);
                    }

                    System.out.println();
                    System.out.println("|0| Voltar");
                    System.out.println();
                    System.out.print("> ");
                    text = Main.input.nextLine();

                    if(!text.equals("0") && !text.isBlank()) {
                        Message message = new Message(connected, text);

                        connected.setChatMessage(friend, message);
                        friend.setChatMessage(connected, message);
                    }
                } while(!text.equals("0"));
            }
        } while(option != 0);
    }

    private void addComunity() throws IOException, InterruptedException {
        Main.input.nextLine();
        System.out.println();
        System.out.print("Nome da comunidade: ");
        String name = Main.input.nextLine();
        Main.clear();
        Community community = Main.search(Main.communities, name);
        if(community != null) {
            community.setInvitation(connected);
            System.out.println("Convite enviado para fazer parte de "+community.getName());
            System.out.println("------------------------------");
        }
        else {
            System.out.println("COMUNIDADE NÃO ENCONTRADA");
            System.out.println("------------------------------");
        }
    }

    private void createComunity() throws IOException, InterruptedException {
        Main.clear();
        Main.input.nextLine();
        System.out.println("Nova comunidade");
        System.out.println();
        System.out.print("Nome: ");
        String name = Main.input.nextLine();
    
        Community community = Main.search(Main.communities, name);
        if(community == null) {
            System.out.print("Descrição: ");
            String description = Main.input.nextLine();

            Community newCommunity = new Community(connected, name, description);
            Main.communities.add(newCommunity);
            connected.setMyComunity(newCommunity);

            Main.clear();
            System.out.println("Comunidade criada");
            System.out.println("------------------------------");
        }
        else {
            Main.clear();
            System.out.println("COMUNIDADE JÁ EXISTE");
            System.out.println("------------------------------");
        }
    }

    private boolean selectInvitationComunity(Community community) throws IOException, InterruptedException {
        int option;
        do {
            Main.clear();

            System.out.println("Convites para a comunidade "+community.getName()+":");
            int j = 0;
            for(User u : community.getInvitations()) {
                System.out.println("|"+(j+1)+"| "+u.getName());
                j++;
            }
            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            option = Main.input.nextInt();
            if(option != 0) {
                int action;
                System.out.println();
                System.out.println("|1| Aceitar novo membro");
                System.out.println("|2| Recusar novo membro");
                System.out.println("|0| Voltar");
                System.out.println();
                System.out.print("> ");

                action = Main.input.nextInt();
                Main.clear();
                switch(action) {
                    case 1:
                        community.setMember(community.getInvitations().get(option-1));
                        community.getInvitations().get(option-1).setComunity(community);
                        community.getInvitations().remove(option-1);
            
                        System.out.println("NOVO MEMBRO ADICIONADO NA COMUNIDADE");
                        System.out.println("------------------------------");
                        return false;
                    case 2:
                        community.getInvitations().remove(option-1);

                        System.out.println("NOVO MEMBRO RECUSADO NA COMUNIDADE");
                        System.out.println("------------------------------");
                        return false;
                    default:
                        Main.clear();
                }
            }
            Main.clear();
        } while(option != 0);

        return true;
    }

    private void invitationComunity() throws IOException, InterruptedException {
        Main.clear();
        int option;
        do {
            int i = 0;
            System.out.println("Convite para: ");
            for(Community c : connected.getMyCommunities()) {
                if(!c.getInvitations().isEmpty()) {
                    System.out.println("|"+(i+1)+"| "+c.getName());
                }
                i++;
            }
            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            option = Main.input.nextInt();
            if(option != 0) {
                boolean find = selectInvitationComunity(connected.getMyCommunities().get(option-1));
                if(find == false) return;
            }
        } while (option != 0);
        Main.clear();
    }

    private void myCommunities() throws IOException, InterruptedException {
        Main.clear();
        int option;
        do {
            if(!connected.getMyCommunities().isEmpty()) {
                System.out.println("Minhas comunidades: ");
                for(Community c : connected.getMyCommunities())
                        System.out.println(c.getName());
            }
            else System.out.println("Sem comunidades");
            
            System.out.println();
            System.out.println("|1| Criar comunidade");
            for(Community c : connected.getMyCommunities()) {
                if(!c.getInvitations().isEmpty()) System.out.println("|2| Convites das minhas comunidades");
            }
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");
    
            option = Main.input.nextInt();
            switch(option) {
                case 1:
                    createComunity();
                    break;
                case 3:
                    invitationComunity();
                    break;
                default:
                    Main.clear();
            }
        } while(option != 0);
    }

    private void communities() throws IOException, InterruptedException {
        Main.clear();
        int option;
        do {
            if(!connected.getCommunities().isEmpty()) {
                System.out.println("Comunidades que participo: ");
                for(Community c : connected.getCommunities())
                    System.out.println(c.getName());
            }
            else System.out.println("Sem comunidades");

            System.out.println();
            System.out.println("|1| Buscar comunidade");
            System.out.println("|2| Minhas comunidades");
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            option = Main.input.nextInt();
            switch(option) {
                case 1:
                    addComunity();
                    break;
                case 2:
                    myCommunities();
                    break;
                default:
                    Main.clear();
            }
        } while(option != 0);
    }

    private void feed() throws IOException, InterruptedException {
        String text;
        do {
            Main.clear();
            System.out.println("       Feed de notícias       ");
            System.out.println("------------------------------");
            for(User user : Main.users) {
                for(Post post : user.getPosts()) {
                    if(user.equals(connected) || post.getVisibility())
                        System.out.println(post.getSender().getNameLastName()+" publicou: "+post.getText());
                    else
                        if(connected.getFriends().contains(user))
                            System.out.println(post.getSender().getNameLastName()+" publicou: "+post.getText());
                }
            }
            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");
            text = Main.input.nextLine();

            if(!text.equals("0") && !text.isBlank()) {
                int option;
                System.out.println();
                System.out.println("Quem pode ver esta publicação:");
                System.out.println("|1| Amigos");
                System.out.println("|2| Todos");
                System.out.println();
                System.out.print("> ");
                option = Main.input.nextInt();

                Post post = new Post(connected, text);
                if(option == 1) 
                    post.setVisibility(false);
                else
                    post.setVisibility(true);
                connected.setPost(post);
            }
        } while(!text.equals("0"));
    }

    public void menu() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println(connected.getNameLastName());
            System.out.println("------------------------------");
            System.out.println("Amigos: "+connected.getFriends().size());
            System.out.println("Comunidades: "+connected.getCommunities().size());
            System.out.println(connected.getChats());
            System.out.println();

            System.out.println("|1| Perfil");
            System.out.println("|2| Amigos");
            System.out.println("|3| Mensagens");
            System.out.println("|4| Comunidades");
            System.out.println("|5| Feed");
            System.out.println("|0| Sair");
            System.out.println();
            System.out.print("> ");

            try {
                option = Integer.parseInt(Main.input.next());
                switch(option) {
                    case 1:
                        if(!profile()) {
                            Main.clear();
                            return;
                        }
                        break;
                    case 2:
                        friends();
                        break;
                    case 3:
                        chats();
                        break;
                    case 4:
                        communities();
                        break;
                    case 5:
                        feed();
                        break;
                        case 0:
                        Main.clear();
                        return;
                    default:
                        Main.clear();
                        System.out.println("OPCAO INVALIDA");
                        System.out.println("------------------------------");
                }
            }
            catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
                System.out.println("------------------------------");
            }
        } while(option != 0);
    }
}