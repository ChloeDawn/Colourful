package net.insomniakitten.colourful.client;

import net.insomniakitten.colourful.RegistryManager.Blocks;
import net.insomniakitten.colourful.common.block.color.pillar.BlockColorPillar;
import net.insomniakitten.colourful.common.block.color.pillar.PillarResourceManager;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelRegistry {

    private static final List<WrappedModel> MODELS = new ArrayList<>();
    public static void registerModel(WrappedModel model) {
        MODELS.add(model);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onStateRegistry(ModelRegistryEvent event) {
        for (Blocks block : Blocks.values()) {
            if (block.get() instanceof BlockColorPillar) {
                ModelLoader.setCustomStateMapper(block.get(),
                        new PillarResourceManager.StateMapperPillar());
            }
        }
        for (Block block : PillarResourceManager.PILLAR_SUBTYPES) {
            ModelLoader.setCustomStateMapper(block,
                    new PillarResourceManager.StateMapperPillar());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry(ModelRegistryEvent event) {
        MODELS.forEach(model -> {
            ModelResourceLocation mrl = new ModelResourceLocation(
                    model.getResourceLocation(), model.getVariants());
            ModelLoader.setCustomModelResourceLocation(
                    model.getItem(), model.getMetadata(), mrl);
        });
    }

}
