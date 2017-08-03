package net.insomniakitten.colourful;

import net.insomniakitten.colourful.common.block.base.BlockBasic;
import net.insomniakitten.colourful.common.block.color.BlockColor;
import net.insomniakitten.colourful.common.block.color.pillar.BlockColorPillar;
import net.insomniakitten.colourful.common.block.color.pillar.ItemBlockColorPillar;
import net.insomniakitten.colourful.common.block.color.pillar.PillarResourceManager;
import net.insomniakitten.colourful.common.item.ItemPaintbrush;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class RegistryManager {

    public enum Blocks {
        BRICKS_TERRACOTTA(new BlockBasic("bricks_terracotta", Material.ROCK, 1.25f, 21.0f)),
        BRICKS_TERRACOTTA_STAINED(new BlockColor("bricks_terracotta_stained", Material.ROCK, 1.25f, 21.0f)),
        BRICKS_TERRACOTTA_GLAZED(new BlockColor("bricks_terracotta_glazed", Material.ROCK, 1.25f, 21.0f)),
        QUARTZ_STAINED(new BlockColor("quartz_stained", Material.ROCK, 0.8f, 4.0f)),
        QUARTZ_STAINED_CHISELED(new BlockColor("quartz_stained_chiseled", Material.ROCK, 0.8f, 4.0f)),
        QUARTZ_STAINED_PILLAR(new BlockColorPillar("quartz_stained_pillar", Material.ROCK, 0.8f, 4.0f)),
        ;

        private final Block block;
        Blocks(Block block) { this.block = block; }
        public Block get() { return block; }
    }

    private static final List<ItemBlock> ITEM_BLOCKS = new ArrayList<>();
    public static void registerItemBlock(ItemBlock iblock) {
        if (!ITEM_BLOCKS.contains(iblock))
            ITEM_BLOCKS.add(iblock);
    }

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        for (Blocks block : Blocks.values()) {
            event.getRegistry().register(block.get());
        }
        for (Block pillar : PillarResourceManager.PILLAR_SUBTYPES) {
            event.getRegistry().register(pillar);
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemPaintbrush());
        // TODO: Paintbrush in-world and in-recipe dye system
        for (Blocks block : Blocks.values()) {
            if (block.get() instanceof BlockColorPillar) {
                // Pillar ItemBlocks need to be instantiated post-onBlockRegistry
                RegistryManager.registerItemBlock(new ItemBlockColorPillar(block.get()));
            }
        }
        ITEM_BLOCKS.forEach(iblock -> event.getRegistry().register(iblock));
    }

}
