package net.insomniakitten.colourful.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.insomniakitten.colourful.data.IJsonData;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SideOnly(Side.CLIENT)
public final class JsonStateFactory {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final String assets;
    private final String path;

    private JsonStateFactory(Minecraft mc, String modid, String file) {
        String path = Paths.get(mc.mcDataDir.getAbsolutePath()).getParent().getParent().toString();
        this.assets = path + "/src/main/resources/assets/";
        this.path = modid + "/blockstates/" + file;
    }

    public static JsonStateFactory create(ResourceLocation name) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        String modid = name.getResourceDomain();
        String file = name.getResourcePath() + ".json";
        return new JsonStateFactory(mc, modid, file);
    }

    public <E extends Enum & IStringSerializable> void build(List<Object> defaults, Map<String, E[]> objects) {
        Map<String, Object> json = new LinkedHashMap<>();
        Map<String, Object> variants = new TreeMap<>();
        File file = new File(assets + path);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            json.put("forge_marker", 1);
            if (!defaults.isEmpty()) {
                defaults.forEach(data -> json.put("defaults", data));
            }
            objects.forEach((name, constants) -> {
                Map<String, Object> values = new HashMap<>();
                for (E constant : constants) {
                    if (constant instanceof IJsonData) {
                        Object data = ((IJsonData) constant).getJsonData();
                        values.put(constant.getName(), data);
                    } else {
                        values.put(constant.getName(), new Object());
                    }
                }
                if (!values.isEmpty()) {
                    variants.put(name, values);
                }
            });
            json.put("variants", variants);

            try (FileWriter writer = new FileWriter(file)) {
                GSON.toJson(json, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
