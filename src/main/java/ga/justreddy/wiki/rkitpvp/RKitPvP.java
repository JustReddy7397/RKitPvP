package ga.justreddy.wiki.rkitpvp;

import com.google.gson.Gson;
import ga.justreddy.wiki.rkitpvp.model.entity.data.PlayerKits;
import ga.justreddy.wiki.rkitpvp.model.entity.data.PlayerStats;
import ga.justreddy.wiki.rkitpvp.model.kit.layout.KitLayout;
import ga.justreddy.wiki.rkitpvp.model.storage.YMLStorage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public final class RKitPvP extends JavaPlugin {

    @Getter static RKitPvP instance;
    Gson gson;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        gson = new Gson();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadConfigurationSerializables() {
        ConfigurationSerialization.registerClass(PlayerStats.class);
        ConfigurationSerialization.registerClass(PlayerKits.class);
        ConfigurationSerialization.registerClass(KitLayout.class);

    }

}
