package ga.justreddy.wiki.rkitpvp.model.storage.type;

import ga.justreddy.wiki.rkitpvp.model.entity.KitPlayer;

import java.util.UUID;

public interface Storage {

    void connect();

    void savePlayer(KitPlayer player);

    void loadPlayer(KitPlayer player);

    void deletePlayer(KitPlayer player);

    boolean existsPlayer(KitPlayer player);

    KitPlayer loadOfflinePlayer(UUID uniqueId);

    void disconnect();

}
