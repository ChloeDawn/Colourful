package net.insomniakitten.colourful;

import net.insomniakitten.colourful.RegistryManager.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod(   modid = Colourful.MOD_ID,
        name = Colourful.MOD_NAME,
        version = Colourful.VERSION,
        acceptedMinecraftVersions = Colourful.MC_VERSION,
        dependencies = Colourful.DEPENDENCIES)

public class Colourful {

    @Mod.Instance(Colourful.MOD_ID)
    public static Colourful instance;

    public static final String MOD_ID = "colourful";
    public static final String MOD_NAME = "Colourful";
    public static final String VERSION = "%mod_version%";
    public static final String MC_VERSION = "%mc_version%";
    public static final String DEPENDENCIES = "required-after:forge@[14.21.1.2387,)";
    public static final CreativeTabs CTAB = new CreativeTabs(Colourful.MOD_ID) {
        @Override @Nonnull
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.BRICKS_TERRACOTTA.get());
        }
    };

}
