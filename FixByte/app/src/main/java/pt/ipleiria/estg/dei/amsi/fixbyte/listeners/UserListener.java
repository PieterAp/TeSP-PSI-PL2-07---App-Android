package pt.ipleiria.estg.dei.amsi.fixbyte.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.User;

public interface UserListener {
    void onRefreshListaUsers(ArrayList<User> listausers);
    void onUpdateListaUsersBD (User user, int operacao);
}
