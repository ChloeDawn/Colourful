package net.insomniakitten.colourful.util;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public interface IItemSupplier {

    Map<ResourceLocation, Item> ENTRIES = new LinkedHashMap<>();

    Item getItem();

    default void register(ResourceLocation name, RegistryEvent.Register<Item> event) {
        Item item = getItem().setRegistryName(name);
        ENTRIES.put(item.getRegistryName(), item);
        event.getRegistry().register(item);
    }

}
