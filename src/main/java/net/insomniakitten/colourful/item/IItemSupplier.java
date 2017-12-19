package net.insomniakitten.colourful.item;

import net.insomniakitten.colourful.block.ColourfulBlock;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public interface IItemSupplier<B extends ColourfulBlock> {

    Map<ResourceLocation, Item> ENTRIES = new LinkedHashMap<>();

    SimpleBlockItem getItem();

    default void register(ResourceLocation name, RegistryEvent.Register<Item> event) {
        Item item = getItem().setRegistryName(name);
        ENTRIES.put(item.getRegistryName(), item);
        event.getRegistry().register(item);
    }

}
