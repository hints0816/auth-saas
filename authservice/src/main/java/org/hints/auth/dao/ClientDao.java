package org.hints.auth.dao;

import org.hints.common.pojo.Client;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ClientDao {
    @Autowired
    private Dao dao;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Client findByClientId(String clientId) {
        Client client = dao.fetch(Client.class, Cnd.where("client_id", "=", clientId));
        return client;
    }

    public List<Client> queryClient(Client client) {
        Cnd cnd = Cnd.from(dao, client);
        List<Client> query = dao.query(Client.class, cnd);
        return query;
    }

    public int insertClient(Client client) {
        Client insert = dao.insert(client);
        return insert != null ? 1 : 0;
    }

    public int deleteClient(String clientId) {
        int client_id = dao.clear(Client.class, Cnd.where("CLIENT_ID", "=", clientId));
        return client_id;
    }

    public int updateClient(Client client) {
        int update = dao.update(client);
        return update;
    }
}
