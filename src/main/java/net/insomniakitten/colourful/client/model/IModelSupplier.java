package net.insomniakitten.colourful.client.model;

import net.insomniakitten.colourful.item.SimpleBlockItem;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Set;

public interface IModelSupplier {

    static Set<PackedModel> getModels(IModelSupplier provider) {
        Set<PackedModel> models = new HashSet<>();
        provider.addModels(models);
        return models;
    }

    @SideOnly(Side.CLIENT)
    static void registerModel(SimpleBlockItem item) {
        for (PackedModel model : getModels(item)) {
            ModelLoader.setCustomModelResourceLocation(
                    item, model.getMetadata(), model.getMRL()
            );
        }
    }

    void addModels(Set<PackedModel> models);

}
