package net.insomniakitten.colourful;

import net.insomniakitten.colourful.client.color.ColorRegistryEvent;
import net.insomniakitten.colourful.client.color.IColorSupplier;
import net.insomniakitten.colourful.client.model.IModelSupplier;
import net.insomniakitten.colourful.client.model.IStateRemapper;
import net.insomniakitten.colourful.data.BlockColor;
import net.insomniakitten.colourful.data.BlockFormat;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.util.IItemSupplier;
import net.insomniakitten.colourful.util.BlockFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
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

    private static final boolean DEOBF = (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    private ColourfulRegistry() {}

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        for (BlockColor color : BlockColor.VALUES) {
            for (BlockMaterial material : BlockMaterial.VALUES) {
                for (BlockFormat format : BlockFormat.VALUES) {
                    BlockFactory.create(format, material, color).register(Colourful.TAB, event);
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
            if (block instanceof IStateRemapper) {
                ((IStateRemapper) block).register();
            }
        }
        for (Item item : IItemSupplier.ENTRIES.values()) {
            if (item instanceof IModelSupplier) {
                ((IModelSupplier) item).register();
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onColorRegistry(ColorRegistryEvent event) {
        for (Block block : BlockFactory.ENTRIES.values()) {
            if (block instanceof IColorSupplier) {
                ((IColorSupplier) block).register(event);
            }
        }
        for (Item item : IItemSupplier.ENTRIES.values()) {
            if (item instanceof IColorSupplier) {
                ((IColorSupplier) item).register(event);
            }
        }
    }

}
