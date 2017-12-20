package net.insomniakitten.colourful.util;

import net.insomniakitten.colourful.Colourful;
import net.insomniakitten.colourful.data.BlockColor;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.data.BlockFormat;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public final class BlockFactory {

    public static final Map<ResourceLocation, Block> ENTRIES = new LinkedHashMap<>();

    private final ResourceLocation name;
    private final String locale;
    private final Block block;

    private BlockFactory(String name, Block block) {
        this.name = new ResourceLocation(Colourful.ID, name);
        this.locale = Colourful.ID + "." + name;
        this.block = block;
    }

    public static BlockFactory create(BlockFormat format, BlockMaterial material, BlockColor color) {
        String name = color.getName() + "_" + material.getName() + "_" + format.getName();
        BlockFactory entry = new BlockFactory(name, format.makeInstance(material, color));
        ENTRIES.putIfAbsent(entry.name, entry.block);
        return entry;
    }

    public void register(CreativeTabs tab, RegistryEvent.Register<Block> event) {
        try {
            event.getRegistry().register(block
                    .setRegistryName(name)
                    .setUnlocalizedName(locale)
                    .setCreativeTab(tab)
            );
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
