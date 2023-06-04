package ga.justreddy.wiki.rkitpvp.model.storage;

import ga.justreddy.wiki.rkitpvp.RKitPvP;
import ga.justreddy.wiki.rkitpvp.model.entity.KitPlayer;
import ga.justreddy.wiki.rkitpvp.model.storage.type.Storage;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.UUID;

public class YMLStorage implements Storage {

    private final File folder;

    public YMLStorage() {
        this.folder = new File(RKitPvP.getInstance().getDataFolder().getAbsolutePath(), "users");
        if (!folder.exists()) folder.mkdir();
    }

    @Override
    public void connect() {

    }

    @SneakyThrows
    @Override
    public void savePlayer(KitPlayer player) {
        if (!existsPlayer(player)) {
            return;
        }
        File file = getUserFile(player.getUniqueId());
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("stats", player.getStats());
        config.set("kits", player.getKits());
    }

    @Override
    public void loadPlayer(KitPlayer player) {

    }

    @Override
    public void deletePlayer(KitPlayer player) {
        File file = getUserFile(player.getUniqueId());
        if (file == null) return;

    }

    @Override
    public boolean existsPlayer(KitPlayer player) {
        return getUserFile(player.getUniqueId()) != null;
    }

    @Override
    public KitPlayer loadOfflinePlayer(UUID uniqueId) {
        return null;
    }

    @Override
    public void disconnect() {
        // Empty
    }

    private File getUserFile(UUID uniqueId) {
        File file = new File(folder.getAbsolutePath(), uniqueId + ".yml");
        if (file == null) return null;
        return file;
    }

}
