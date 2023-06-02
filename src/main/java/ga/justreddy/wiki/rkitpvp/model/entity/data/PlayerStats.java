package ga.justreddy.wiki.rkitpvp.model.entity.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class PlayerStats implements ConfigurationSerializable {

    int kills;
    int deaths;
    int streak;

    public PlayerStats() {
        this.kills = 0;
        this.deaths = 0;
        this.streak = 0;
    }

    public void addKill() {
        kills+=1;
        streak+=1;
    }

    public void addDeath() {
        deaths+=1;
    }

    public void resetStreak() {
        streak = 0;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("kills", kills);
        map.put("deaths", deaths);
        map.put("streak", streak);
        return map;
    }

    public static PlayerStats deserialize(Map<String, Object> args) {
        PlayerStats stats = new PlayerStats();
        stats.kills = (int) args.getOrDefault("kills", 0);
        stats.deaths = (int) args.getOrDefault("deaths", 0);
        stats.streak = (int) args.getOrDefault("streak", 0);
        return stats;
    }

}
