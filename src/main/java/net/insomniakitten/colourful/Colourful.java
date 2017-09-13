package net.insomniakitten.colourful;

import net.insomniakitten.colourful.common.block.base.BlockBasic;
import net.insomniakitten.colourful.common.block.color.BlockColor;
import net.insomniakitten.colourful.common.block.color.pillar.BlockColorPillar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

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
    public static final String VERSION = "%VERSION%";
    public static final String MC_VERSION = "[1.12,1.13)";
    public static final String DEPENDENCIES = "required-after:forge@[14.21.1.2387,)";

    public static final CreativeTabs CTAB = new CreativeTabs(Colourful.MOD_ID) {

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.BRICKS_TERRACOTTA.get());
        }

    };

    public enum Blocks {

        BRICKS_TERRACOTTA(new BlockBasic("bricks_terracotta", Material.ROCK, 1.25f, 21.0f)),
        BRICKS_TERRACOTTA_STAINED(new BlockColor("bricks_terracotta_stained", Material.ROCK, 1.25f, 21.0f)),
        BRICKS_TERRACOTTA_GLAZED(new BlockColor("bricks_terracotta_glazed", Material.ROCK, 1.25f, 21.0f)),
        QUARTZ_STAINED(new BlockColor("quartz_stained", Material.ROCK, 0.8f, 4.0f)),
        QUARTZ_STAINED_CHISELED(new BlockColor("quartz_stained_chiseled", Material.ROCK, 0.8f, 4.0f)),
        QUARTZ_STAINED_PILLAR(new BlockColorPillar("quartz_stained_pillar", Material.ROCK, 0.8f, 4.0f));

        private final Block block;

        Blocks(Block block) {
            this.block = block;
        }

        public Block get() {
            return block;
        }

    }

}
