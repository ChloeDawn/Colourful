package net.insomniakitten.colourful.client.model;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICustomStateMapper {

    IStateMapper getStateMapper();

    @SideOnly(Side.CLIENT)
    default void register() {
        if (this instanceof Block) {
            ModelLoader.setCustomStateMapper(
                    (Block) this, getStateMapper()
            );
        }
    }

}
