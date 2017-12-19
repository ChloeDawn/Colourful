package net.insomniakitten.colourful;

import net.insomniakitten.colourful.block.AbstractBlock;
import net.insomniakitten.colourful.block.ColouredBlock;
import net.insomniakitten.colourful.block.ColouredPillarBlock;
import net.insomniakitten.colourful.block.SimpleBlock;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.item.SimpleBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.event.RegistryEvent;

import java.util.Locale;

public enum ColourfulBlocks implements IStringSerializable {

    BRICKS_TERRACOTTA(new SimpleBlock(BlockMaterial.ROCK, MapColor.BROWN)),
    BRICKS_TERRACOTTA_STAINED(new ColouredBlock(BlockMaterial.ROCK)),
    BRICKS_TERRACOTTA_GLAZED(new ColouredBlock(BlockMaterial.ROCK)),
    QUARTZ_STAINED(new ColouredBlock(BlockMaterial.QUARTZ)),
    QUARTZ_STAINED_CHISELED(new ColouredBlock(BlockMaterial.QUARTZ)),
    QUARTZ_STAINED_PILLAR(new ColouredPillarBlock(BlockMaterial.QUARTZ));

    public static final ColourfulBlocks[] VALUES = ColourfulBlocks.values();

    private final AbstractBlock block;

    ColourfulBlocks(AbstractBlock block) {
        this.block = block;
    }

    public SimpleBlockItem getItem() {
        if (block.getItem().getRegistryName() == null) {
            block.getItem().setRegistryName(Colourful.ID, getName());
        }
        return block.getItem();
    }

    public void register(RegistryEvent.Register<Block> event) {
        block.register(getName(), event);
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

}
