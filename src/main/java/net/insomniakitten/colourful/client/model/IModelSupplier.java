package net.insomniakitten.colourful.client.model;

import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;
import java.util.Set;

public interface IModelSupplier {

    default Set<PackedModel> getModels() {
        Set<PackedModel> models = new HashSet<>();
        addModels(models);
        return models;
    }

    @SideOnly(Side.CLIENT)
    default void register() {
        if (this instanceof Item) {
            for (PackedModel model : getModels()) {
                ModelLoader.setCustomModelResourceLocation(
                        (Item) this, model.getMetadata(), model.getMRL()
                );
            }
        }
    }

    void addModels(Set<PackedModel> models);

}
