package ga.justreddy.wiki.rkitpvp.model.entity;

import ga.justreddy.wiki.rkitpvp.model.entity.data.PlayerKits;
import ga.justreddy.wiki.rkitpvp.model.entity.data.PlayerStats;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@Setter
public class KitPlayer {

    UUID uniqueId;
    String name;
    Player player;
    @NonFinal PlayerStats stats;
    @NonFinal PlayerKits kits;

    public KitPlayer(UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.player = Bukkit.getPlayer(uniqueId);
        this.stats = new PlayerStats();
        this.kits = new PlayerKits();
    }

}
