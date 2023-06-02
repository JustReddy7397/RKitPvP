package ga.justreddy.wiki.rkitpvp.model.storage;

import ga.justreddy.wiki.rkitpvp.model.entity.KitPlayer;
import ga.justreddy.wiki.rkitpvp.model.storage.type.Storage;

import java.util.UUID;

public class MongoStorage implements Storage {
    @Override
    public void connect() {

    }

    @Override
    public void savePlayer(KitPlayer player) {

    }

    @Override
    public void loadPlayer(KitPlayer player) {

    }

    @Override
    public void deletePlayer(KitPlayer player) {

    }

    @Override
    public boolean existsPlayer(KitPlayer player) {
        return false;
    }

    @Override
    public KitPlayer loadOfflinePlayer(UUID uniqueId) {
        return null;
    }

    @Override
    public void disconnect() {

    }
}
