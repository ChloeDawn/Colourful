package net.insomniakitten.colourful;

import net.insomniakitten.colourful.client.model.IModelSupplier;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Colourful.ID)
public final class ColourfulRegistry {

    private ColourfulRegistry() {}

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        for (ColourfulBlocks block : ColourfulBlocks.VALUES) {
            block.register(event);
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        for (ColourfulBlocks block : ColourfulBlocks.VALUES) {
            event.getRegistry().register(block.getItem());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(ModelRegistryEvent event) {
        for (ColourfulBlocks block : ColourfulBlocks.VALUES) {
            IModelSupplier.registerModel(block.getItem());
        }
    }

}
