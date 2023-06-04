package ga.justreddy.wiki.rkitpvp.model.entity.data;

import ga.justreddy.wiki.rkitpvp.model.kit.Kit;
import ga.justreddy.wiki.rkitpvp.model.kit.layout.KitLayout;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerKits implements ConfigurationSerializable {

    private String lastUsedKit;
    private final List<KitLayout> kitLayouts;

    public PlayerKits() {
        this.kitLayouts = new ArrayList<>();
    }

    public String getLastUsedKit() {
        return lastUsedKit != null ? lastUsedKit : KitManager.getManager().getDefaultKit().getName();
        // TODO
    }

    public void setLastUsedKit(String lastUsedKit) {
        this.lastUsedKit = lastUsedKit;
    }

    public List<KitLayout> getKitLayouts() {
        return kitLayouts;
    }

    public void addKitLayout(KitLayout layout) {
        kitLayouts.removeIf(loopLayout -> layout.getKitName().equalsIgnoreCase(loopLayout.getKitName()));
        kitLayouts.add(layout);
    }

    public KitLayout getKitLayout(Kit kit) {
        return kitLayouts.stream().filter(kitLayout -> kitLayout.getKitName().equalsIgnoreCase(kit.getName())).findFirst().orElse(null);
    }

    public void setKitLayout(KitLayout kitLayOut) {
        addKitLayout(kitLayOut);
    }

    public void removeKitLayout(Kit kit) {
        kitLayouts.remove(getKitLayout(kit));
    }


    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("lastUsedKit", lastUsedKit);
        map.put("kitLayouts", kitLayouts);
        return map;
    }
}
