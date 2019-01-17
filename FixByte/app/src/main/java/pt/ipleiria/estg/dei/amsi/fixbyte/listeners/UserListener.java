package pt.ipleiria.estg.dei.amsi.fixbyte.listeners;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.amsi.fixbyte.modelo.User;

public interface UserListener {
    void onRefreshListaUser(ArrayList<User> userdata);
}
