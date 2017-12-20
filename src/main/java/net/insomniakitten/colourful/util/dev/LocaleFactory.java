package net.insomniakitten.colourful.util.dev;

import com.google.common.base.CaseFormat;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;

@SideOnly(Side.CLIENT)
public final class LocaleFactory {

    private final String modid;
    private final String path;
    private final Item item;

    private LocaleFactory(Minecraft mc, String modid, Item item) {
        String path = Paths.get(mc.mcDataDir.getAbsolutePath()).getParent().getParent().toString();
        this.modid = modid;
        this.path = path + "/src/main/resources/assets/" + modid + "/lang/en_us.lang";
        this.item = item;
    }

    private LocaleFactory(Minecraft mc, String modid, Block block) {
        this(mc, modid, Item.getItemFromBlock(block));
    }

    public static LocaleFactory create(Item item) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        String modid = item.getRegistryName().getResourceDomain();
        return new LocaleFactory(mc, modid, item);
    }

    public static LocaleFactory create(Block block) {
        return create(Item.getItemFromBlock(block));
    }

    public void build(CreativeTabs tab) {
        NonNullList<ItemStack> subitems = NonNullList.create();
        Set<String> keys = new TreeSet<>();
        File file = new File(path);
        try {
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                item.getSubItems(tab, subitems);
                if (!subitems.isEmpty()) {
                    for (ItemStack stack : subitems) {
                        keys.add(stack.getUnlocalizedName());
                    }
                } else keys.add(item.getUnlocalizedName());
                file.createNewFile();
                FileWriter writer = new FileWriter(file, true);
                for (String key : keys) {
                    String value = key
                            .replace("item.", "")
                            .replace("tile.", "")
                            .replace("block.", "")
                            .replace(modid + ".", "");
                    value = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, value);
                    value = value.replaceAll("([A-Z])", " $1").trim();
                    writer.write(System.lineSeparator());
                    writer.write(key + ".name=" + value);
                }
                writer.flush();
                writer.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
