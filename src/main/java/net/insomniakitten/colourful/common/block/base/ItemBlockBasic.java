package net.insomniakitten.colourful.common.block.base;

import net.insomniakitten.colourful.client.ModelRegistry;
import net.insomniakitten.colourful.client.WrappedModel.ModelBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;


public class ItemBlockBasic extends ItemBlock {

    public ItemBlockBasic(Block block) {
        super(block);
        assert block.getRegistryName() != null;
        setRegistryName(block.getRegistryName());
        setCreativeTab(block.getCreativeTabToDisplayOn());
        ModelRegistry.registerModel(new ModelBuilder(this).build());
    }

}
