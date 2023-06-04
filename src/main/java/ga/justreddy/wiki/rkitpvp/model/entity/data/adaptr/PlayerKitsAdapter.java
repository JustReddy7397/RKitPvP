package ga.justreddy.wiki.rkitpvp.model.entity.data.adaptr;

import com.google.gson.*;
import ga.justreddy.wiki.rkitpvp.model.entity.data.PlayerKits;
import ga.justreddy.wiki.rkitpvp.model.kit.layout.KitLayout;
import ga.justreddy.wiki.rkitpvp.util.Base64Util;
import ga.justreddy.wiki.rkitpvp.util.GsonUtil;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerKitsAdapter implements JsonSerializer<PlayerKits>, JsonDeserializer<PlayerKits> {

    @Override
    public PlayerKits deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Map<String, Map<String, String>> layoutMap = GsonUtil.fromJson(object.get("layouts"), Map.class);

        String lastUsedKit = object.get("lastUsedKit").getAsString();
        PlayerKits kits = new PlayerKits();
        for (Map.Entry<String, Map<String, String>> entry : layoutMap.entrySet()) {
            KitLayout layOut = new KitLayout(entry.getKey());
            Map<Integer, ItemStack> items = new HashMap<>();
            for (Map.Entry<String, String> k : entry.getValue().entrySet()) {
                ItemStack itemStack = Base64Util.decode(k.getValue());
                if (itemStack == null) continue;
                items.put(Integer.parseInt(k.getKey()), Base64Util.decode(k.getValue()));
            }
            layOut.setItemsLayout(items);
            kits.addKitLayout(layOut);
        }
        kits.setLastUsedKit(lastUsedKit);
        return kits;
    }

    @Override
    public JsonElement serialize(PlayerKits playerKits, Type type, JsonSerializationContext context) {
        List<KitLayout> layouts = playerKits.getKitLayouts();
        String lastUsedKit = playerKits.getLastUsedKit();
        JsonObject result = new JsonObject();
        Map<String, Map<Integer, String>> layoutMap = new HashMap<>();

        for (KitLayout layout : layouts) {
            Map<Integer, String> map = new HashMap<>();
            for (Map.Entry<Integer, ItemStack> entry : layout.getItemsLayout().entrySet()) {
                map.put(entry.getKey(), Base64Util.encode(entry.getValue()));
            }
            layoutMap.put(layout.getKitName(), map);
        }
        result.add("layouts", context.serialize(layoutMap));
        result.add("lastUsedKit", context.serialize(lastUsedKit));
        return result;
    }
}
