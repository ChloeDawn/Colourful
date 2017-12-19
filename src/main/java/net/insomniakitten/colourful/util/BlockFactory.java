package net.insomniakitten.colourful.util;

import net.insomniakitten.colourful.Colourful;
import net.insomniakitten.colourful.data.BlockColor;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.data.BlockPattern;
import net.insomniakitten.colourful.data.BlockType;
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
    private final CreativeTabs tab;
    private final Block block;

    private BlockFactory(String name, CreativeTabs tab, Block block) {
        this.name = new ResourceLocation(Colourful.ID, name);
        this.locale = Colourful.ID + "." + name;
        this.tab = tab;
        this.block = block;
    }

    public static BlockFactory create(BlockType type, BlockMaterial material, BlockColor color, BlockPattern pattern) {
        String name = color.getName() + pattern.getSuffix() + material.getSuffix() + type.getSuffix();
        Block block = type.getBlock(material, color, pattern);
        BlockFactory entry = new BlockFactory(name, Colourful.TAB, block);
        ENTRIES.putIfAbsent(entry.name, entry.block);
        return entry;
    }

    public void register(RegistryEvent.Register<Block> event) {
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
