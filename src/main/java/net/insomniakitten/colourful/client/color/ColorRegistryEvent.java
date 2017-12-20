package net.insomniakitten.colourful.client.color;

import net.insomniakitten.colourful.Colourful;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ColorRegistryEvent extends Event {

    private final ItemColors itemColors;
    private final BlockColors blockColors;

    private ColorRegistryEvent(ItemColors itemColors, BlockColors blockColors) {
        this.itemColors = itemColors;
        this.blockColors = blockColors;
    }

    @SideOnly(Side.CLIENT)
    public void register(IItemColor itemColor, Item... items) {
        itemColors.registerItemColorHandler(itemColor, items);
    }

    @SideOnly(Side.CLIENT)
    public void register(IBlockColor blockColor, Block... blocks) {
        blockColors.registerBlockColorHandler(blockColor, blocks);
    }

    @Mod.EventBusSubscriber(modid = Colourful.ID, value = Side.CLIENT)
    public static final class Invoker {
        private Invoker() {}

        @SubscribeEvent
        protected static void onItemColorHandler(ColorHandlerEvent.Item event) {
            MinecraftForge.EVENT_BUS.post(new ColorRegistryEvent(
                    event.getItemColors(), event.getBlockColors()
            ));
        }
    }

}
