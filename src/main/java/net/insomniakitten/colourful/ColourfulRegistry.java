package net.insomniakitten.colourful;

import net.insomniakitten.colourful.client.model.ICustomStateMapper;
import net.insomniakitten.colourful.client.model.IModelSupplier;
import net.insomniakitten.colourful.data.BlockColor;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.data.BlockPattern;
import net.insomniakitten.colourful.data.BlockType;
import net.insomniakitten.colourful.item.IItemSupplier;
import net.insomniakitten.colourful.util.BlockFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Colourful.ID)
public final class ColourfulRegistry {

    private ColourfulRegistry() {}

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        for (BlockType type : BlockType.VALUES) {
            for (BlockMaterial material : BlockMaterial.VALUES) {
                for (BlockColor color : BlockColor.VALUES) {
                    for (BlockPattern pattern : BlockPattern.VALUES) {
                        BlockFactory.create(type, material, color, pattern).register(event);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        for (Map.Entry<ResourceLocation, Block> entry : BlockFactory.ENTRIES.entrySet()) {
            if (entry.getValue() instanceof IItemSupplier) {
                ((IItemSupplier) entry.getValue()).register(entry.getKey(), event);
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(ModelRegistryEvent event) {
        for (Block block : BlockFactory.ENTRIES.values()) {
            if (block instanceof ICustomStateMapper) {
                ((ICustomStateMapper) block).register();
            }
        }
        for (Item item : IItemSupplier.ENTRIES.values()) {
            if (item instanceof IModelSupplier) {
                ((IModelSupplier) item).register();
            }
        }
    }

}
