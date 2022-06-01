import java.io.*;
import java.text.*;
import java.util.*;

public class Profile {
    private User connected;

    public Profile(User connected) {
        this.connected = connected;
    }

    private void editProfile() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Perfil > Editar perfil");
            System.out.println("--------------------------------------------------");
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
                        break;
                    case 2:
                        System.out.println();
                        System.out.print("Sobrenome: ");
                        String lastName = Main.input.next();
                        if(!lastName.matches("[A-z]+")) throw new Exception("NOME NÃO PODE TER ACENTOS OU NÚMEROS");

                        connected.setLastName(lastName);
                        Main.clear();
                        System.out.println("Sobrenome alterado com sucesso");
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
                        }
                        else {
                            Main.clear();
                            System.out.println("EMAIL JÁ CADASTRADO");
                        }
                        break;
                    case 4:
                        System.out.println();
                        System.out.print("Senha: ");
                        String password = Main.input.next();
                        connected.setPassowrd(password);
                        Main.clear();
                        System.out.println("Senha alterada com sucesso");
                        break;
                    case 0:
                        Main.clear();
                        return;
                    default:
                        Main.clear();
                        System.out.println("OPCAO INVALIDA");
                }
            } catch(NumberFormatException e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            } catch(Exception e) {
                Main.clear();
                System.out.println(e.getMessage());
            }
        } while(option != 0);
    }

    private boolean removeAccount() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Perfil > Encerrar conta");
            System.out.println("--------------------------------------------------");
            System.out.println();
            System.out.println("|1| Desejo encerrar minha conta");
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
                        
                        for(Post p : Main.feed)
                            if(p.getSender().equals(connected)) Main.feed.remove(p);

                        Main.users.remove(connected);

                        Main.clear();
                        System.out.println("Conta encerrada com sucesso");
                        return false;
                    case 0:
                        Main.clear();
                        return true;
                    default:
                        Main.clear();
                        System.out.println("OPCAO INVALIDA");
                }
            } catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while(option != 0);
        
        return true;
    }

    private boolean profile() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Perfil");
            System.out.println("--------------------------------------------------");
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
                }
            }
            catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while(option != 0);
        return true;
    }

    private void invitationFriend() throws IOException, InterruptedException {
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Amigos > Solicitações de amizade");
            System.out.println("--------------------------------------------------");
            int i = 0;
            for(User user : connected.getInvitations()) {
                System.out.println("|"+(i+1)+"| "+user.getNameLastName());
                i++;
            }
            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            try {
                option = Integer.parseInt(Main.input.next());

                if(option != 0) {
                    System.out.println();
                    System.out.println("Solicitação de "+connected.getInvitations().get(option-1).getNameLastName()+":");
                    System.out.println();
                    System.out.println("|1| Aceitar");
                    System.out.println("|2| Recusar");
                    System.out.println("|0| Voltar");
                    System.out.println();
                    System.out.print("> ");

                    int action = Integer.parseInt(Main.input.next());
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
                            Main.clear();
                            return;
                        default:
                            Main.clear();
                            System.out.println("OPCAO INVALIDA");
                    }
                }
            } catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while (option != 0);
    }

    private void friends() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Amigos");
            System.out.println("--------------------------------------------------");
            if(!connected.getFriends().isEmpty()) {
                for(User u : connected.getFriends())
                    System.out.println(u.getNameLastName());
            }
            else System.out.println("Sem amigos");

            System.out.println();
            System.out.println("|1| Buscar novo amigo");
            if(!connected.getInvitations().isEmpty()) System.out.println("|2| Solicitações de amizade");
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            try {
                option = Integer.parseInt(Main.input.next());
                switch(option) {
                    case 1:
                        System.out.println();
                        System.out.print("Buscar novo amigo pelo email: ");
                        String email = Main.input.next();
                        if(!email.matches("^([\\w\\-]+.)*[\\w\\-]+@([\\w\\-]+.)+([\\w\\-]{2,3})"))
                            throw new Exception("EMAIL INVALIDO");
                        else if(email.equals(connected.getEmail()))
                            throw new Exception("USUÁRIO NÃO ENCONTRADO");

                        User user = Main.search(Main.users, email);
                        if(user != null) {
                            if(user.getInvitations().contains(connected) || connected.getInvitations().contains(user))
                                throw new Exception("Convite para "+user.getNameLastName()+" já enviado");
                            else if(connected.getFriends().contains(user))
                                throw new Exception(user.getNameLastName()+" já faz parte da sua lista de amigos");

                            user.setInvitation(connected);
                            Main.clear();
                            System.out.println("Convite enviado para "+user.getNameLastName());
                        }
                        else {
                            Main.clear();
                            System.out.println("USUÁRIO NÃO ENCONTRADO");
                        }
                        break;
                    case 2:
                        Main.clear();
                        if(!connected.getInvitations().isEmpty())
                            invitationFriend();
                        else
                            System.out.println("OPCAO INVALIDA");
                        break;
                    case 0:
                        Main.clear();
                        return;
                    default:
                        Main.clear();
                        System.out.println("OPCAO INVALIDA");
                }
            } catch(NumberFormatException e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            } catch(Exception e) {
                Main.clear();
                System.out.println(e.getMessage());
            }
        } while(option != 0);
    }

    private void chats() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Recados");
            System.out.println("--------------------------------------------------");
            if(!connected.getFriends().isEmpty()) {
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
            
            try {
                option = Integer.parseInt(Main.input.next());
                Main.clear();

                User friend = null;
                if(option !=0) friend = connected.getFriends().get(option-1);

                if((option != 0) && (friend != null)) {
                    String text;
                    do {
                        Main.clear();
                        System.out.println("--------------------------------------------------");
                        System.out.println("iFace | Recados > "+connected.getFriends().get(option-1).getNameLastName());
                        System.out.println("--------------------------------------------------");
                        
                        if(connected.getChats().get(friend) != null && !connected.getChats().get(friend).isEmpty()) {
                            for(Message message : connected.getChats().get(friend))
                                System.out.println(message.getDate()+" "+message.getSender().getNameLastName()+" disse: "+message.getText());
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

                        if(!text.equals("0") && !text.isEmpty()) {
                            String date = new SimpleDateFormat("[dd/MM/yyyy][HH:mm]").format(Calendar.getInstance().getTime());
                            Message message = new Message(connected, text, date);

                            connected.setChatMessage(friend, message);
                            friend.setChatMessage(connected, message);
                        }
                    } while(!text.equals("0"));
                    Main.clear();
                }
            } catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while(option != 0);
    }

    private void addComunity() throws IOException, InterruptedException {
        Main.input.nextLine();
        System.out.println();
        System.out.print("Buscar comunidade pelo nome: ");
        String name = Main.input.nextLine();
        Main.clear();
        Community community = Main.search(Main.communities, name);
        try {
            if(community != null) {
                if(community.getCreator().equals(connected) || connected.getCommunities().contains(community))
                    throw new Exception("Você já faz parte de "+community.getName());
                community.setInvitation(connected);
                System.out.println("Convite enviado para fazer parte de "+community.getName());
            }
            else {
                System.out.println("COMUNIDADE NÃO ENCONTRADA");
            }
        } catch(Exception e) {
            Main.clear();
            System.out.println(e.getMessage());
        }
    }

    private void createComunity() throws IOException, InterruptedException {
        Main.clear();
        Main.input.nextLine();
        System.out.println("--------------------------------------------------");
        System.out.println("iFace | Comunidades > Criar comunidade");
        System.out.println("--------------------------------------------------");
        System.out.print("Nome: ");
        String name = Main.input.nextLine();
    
        Community community = Main.search(Main.communities, name);
        if(community == null) {
            System.out.print("Descrição: ");
            String description = Main.input.nextLine();

            Community newCommunity = new Community(connected, name, description);
            Main.communities.add(newCommunity);
            connected.setMyComunity(newCommunity);
            connected.setComunity(newCommunity);

            Main.clear();
            System.out.println("Comunidade criada");
        }
        else {
            Main.clear();
            System.out.println("COMUNIDADE JÁ EXISTE");
        }
    }

    private boolean selectInvitationComunity(Community community) throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Comunidades > Convites para "+community.getName());
            System.out.println("--------------------------------------------------");
            int j = 0;
            for(User u : community.getInvitations()) {
                System.out.println("|"+(j+1)+"| "+u.getNameLastName());
                j++;
            }
            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            try {
                option = Integer.parseInt(Main.input.next());
                if(option != 0) {
                    int action;
                    System.out.println();
                    System.out.println("Convite de "+community.getInvitations().get(option-1).getNameLastName()+" para fazer parte de "+community.getName()+":");
                    System.out.println();
                    System.out.println("|1| Aceitar");
                    System.out.println("|2| Recusar");
                    System.out.println("|0| Voltar");
                    System.out.println();
                    System.out.print("> ");

                    action = Integer.parseInt(Main.input.next());
                    Main.clear();
                    switch(action) {
                        case 1:
                            community.setMember(community.getInvitations().get(option-1));
                            community.getInvitations().get(option-1).setComunity(community);
                            community.getInvitations().remove(option-1);
                
                            System.out.println("Novo membro adicionada na comunidade "+community.getName());
                            return false;
                        case 2:
                            community.getInvitations().remove(option-1);

                            System.out.println("Membro adicionada na comunidade "+community.getName());
                            return false;
                        case 0:
                            Main.clear();
                            return false;
                        default:
                            Main.clear();
                            System.out.println("OPCAO INVALIDA");
                    }
                }
            } catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while(option != 0);

        return true;
    }

    private void invitationComunity() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            int i = 0;
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Comunidades > Minhas comunidades > Convites");
            System.out.println("--------------------------------------------------");
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

            try {
                option = Integer.parseInt(Main.input.next());
                if(option != 0) {
                    boolean find = selectInvitationComunity(connected.getMyCommunities().get(option-1));
                    if(find == false) return;
                }
            } catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while (option != 0);
        Main.clear();
    }

    private void myCommunities() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Comunidades > Minhas comunidades");
            System.out.println("--------------------------------------------------");
            if(!connected.getMyCommunities().isEmpty()) {
                for(Community c : connected.getMyCommunities())
                        System.out.println(c.getName());
            }
            else System.out.println("Sem comunidades");
            
            System.out.println();
            System.out.println("|1| Criar comunidade");
            boolean invitation = false;
            for(Community c : connected.getMyCommunities()) {
                if(!c.getInvitations().isEmpty()) {
                    invitation = true;
                    System.out.println("|2| Convites das minhas comunidades");
                    break;
                }
            }
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");
    
            try {
                option = Integer.parseInt(Main.input.next());
                switch(option) {
                    case 1:
                        createComunity();
                        break;
                    case 2:
                        if(invitation)
                            invitationComunity();
                        else {
                            Main.clear();
                            System.out.println("OPCAO INVALIDA");
                        }
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

    private void chatCommunity() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Comunidades > Recados");
            System.out.println("--------------------------------------------------");
            int i = 0;
            for(Community community : connected.getCommunities()) {
                System.out.println("|"+(i+1)+"| "+community.getName());
                i++;
            }

            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");
            
            try {
                option = Integer.parseInt(Main.input.next());
                Main.clear();

                if((option != 0)) {
                    Community community = connected.getCommunities().get(option-1);
                    String text;
                    do {
                        Main.clear();
                        System.out.println("--------------------------------------------------");
                        System.out.println("iFace | Comunidades > Recados > "+community.getName());
                        System.out.println("--------------------------------------------------");
                        
                        if(!connected.getCommunities().isEmpty()) {
                            for(Message message : community.getChat())
                                System.out.println(message.getDate()+" "+message.getSender().getNameLastName()+" disse: "+message.getText());
                        }

                        System.out.println();
                        System.out.println("|0| Voltar");
                        System.out.println();
                        System.out.print("> ");
                        text = Main.input.nextLine();

                        if(!text.equals("0") && !text.isEmpty()) {
                            String date = new SimpleDateFormat("[dd/MM/yyyy][HH:mm]").format(Calendar.getInstance().getTime());
                            Message message = new Message(connected, text, date);

                            community.setMessage(message);
                        }
                    } while(!text.equals("0"));
                    Main.clear();
                }
            } catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while(option != 0);
    }

    private void communities() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Comunidades");
            System.out.println("--------------------------------------------------");
            if(!connected.getCommunities().isEmpty()) {
                for(Community c : connected.getCommunities())
                    System.out.println(c.getName());
            }
            else System.out.println("Sem comunidades");

            System.out.println();
            System.out.println("|1| Buscar comunidade");
            System.out.println("|2| Minhas comunidades");
            if(!connected.getCommunities().isEmpty()) System.out.println("|3| Recados");
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");

            try {
                option = Integer.parseInt(Main.input.next());
                switch(option) {
                    case 1:
                        addComunity();
                        break;
                    case 2:
                        myCommunities();
                        break;
                    case 3:
                        if(!connected.getCommunities().isEmpty())
                            chatCommunity();
                        else
                            throw new Exception();
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

    private void feed() throws IOException, InterruptedException {
        Main.clear();
        String text;
        do {
            Main.input.nextLine();
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | Feed de Notícias");
            System.out.println("--------------------------------------------------");
                for(Post post : Main.feed) {
                    if(post.getSender().equals(connected) || post.getVisibility() || connected.getFriends().contains(post.getSender()))
                        System.out.println(post.getDate()+" "+post.getSender().getNameLastName()+" publicou: "+post.getText());
                }
            System.out.println();
            System.out.println("|0| Voltar");
            System.out.println();
            System.out.print("> ");
            text = Main.input.nextLine();

            if(!text.equals("0") && !text.isEmpty()) {
                int option;
                System.out.println();
                System.out.println("Quem pode ver esta publicação:");
                System.out.println();
                System.out.println("|1| Amigos");
                System.out.println("|2| Todos");
                System.out.println();
                System.out.print("> ");

                try {
                    option = Integer.parseInt(Main.input.next());
                    Main.clear();

                    if(option == 1 || option == 2) {
                        String date = new SimpleDateFormat("[dd/MM/yyyy][HH:mm]").format(Calendar.getInstance().getTime());
                        Post post = new Post(connected, text, date);
                        if(option == 1) 
                            post.setVisibility(false);
                        else
                            post.setVisibility(true);

                        Main.feed.add(post);
                    }
                    else System.out.println("OPCAO INVALIDA");
                } catch(Exception e) {
                    Main.clear();
                    System.out.println("OPCAO INVALIDA");
                }
            }
        } while(!text.equals("0"));
        Main.clear();
    }

    public void menu() throws IOException, InterruptedException {
        Main.clear();
        int option = -1;
        do {
            System.out.println("--------------------------------------------------");
            System.out.println("iFace | "+connected.getNameLastName());
            System.out.println("--------------------------------------------------");
            System.out.println("Amigos: "+connected.getFriends().size());
            System.out.println("Comunidades: "+connected.getCommunities().size());
            System.out.println();

            System.out.println("|1| Perfil");
            System.out.println("|2| Amigos");
            System.out.println("|3| Recados");
            System.out.println("|4| Comunidades");
            System.out.println("|5| Feed");
            System.out.println("|0| Sair");
            System.out.println();
            System.out.print("> ");

            try {
                option = Integer.parseInt(Main.input.next());
                switch(option) {
                    case 1:
                        if(!profile()) return;
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
                }
            }
            catch(Exception e) {
                Main.clear();
                System.out.println("OPCAO INVALIDA");
            }
        } while(option != 0);
    }
}