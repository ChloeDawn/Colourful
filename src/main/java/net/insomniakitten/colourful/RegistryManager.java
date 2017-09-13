package net.insomniakitten.colourful;

import net.insomniakitten.colourful.common.block.color.pillar.BlockColorPillar;
import net.insomniakitten.colourful.common.block.color.pillar.ItemBlockColorPillar;
import net.insomniakitten.colourful.common.block.color.pillar.PillarResourceManager;
import net.insomniakitten.colourful.common.item.ItemPaintbrush;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber
public class RegistryManager {

    private static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

    public static void registerItemBlock(ItemBlock iblock) {
        ITEM_BLOCKS.add(iblock);
    }

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        for (Colourful.Blocks block : Colourful.Blocks.values()) {
            event.getRegistry().register(block.get());
        }
        for (Block pillar : PillarResourceManager.PILLAR_SUBTYPES) {
            event.getRegistry().register(pillar);
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        // TODO: Paintbrush in-world and in-recipe dye system
        event.getRegistry().register(new ItemPaintbrush());
        for (Colourful.Blocks block : Colourful.Blocks.values()) {
            if (block.get() instanceof BlockColorPillar) {
                // Pillar ItemBlocks need to be instantiated post-onBlockRegistry
                RegistryManager.registerItemBlock(new ItemBlockColorPillar(block.get()));
            }
        }
        ITEM_BLOCKS.forEach(iblock -> event.getRegistry().register(iblock));
    }

}
