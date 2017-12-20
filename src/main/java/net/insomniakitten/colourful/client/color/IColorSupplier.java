package net.insomniakitten.colourful.client.color;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IColorSupplier {

    @SideOnly(Side.CLIENT)
    int getColor();

    @SideOnly(Side.CLIENT)
    default int getTintIndex() {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    default void register(ColorRegistryEvent event) {
        if (this instanceof Item) {
            event.register((stack, tintIndex)
                    -> tintIndex == getTintIndex() ? getColor() : 0, (Item) this);
        }
        if (this instanceof Block) {
            event.register((state, world, pos, tintIndex)
                    -> tintIndex == getTintIndex() ? getColor() : 0, (Block) this);
        }
    }

}
