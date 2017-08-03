package net.insomniakitten.colourful.common.block.base;

import net.insomniakitten.colourful.Colourful;
import net.insomniakitten.colourful.RegistryManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block {

    public BlockBasic(String name, Material material, float hardness, float resistance) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Colourful.MOD_ID + "." + name);
        setCreativeTab(Colourful.CTAB);
        setHardness(hardness);
        setResistance(resistance);
        registerItemBlock();
    }

    public void registerItemBlock() {
        RegistryManager.registerItemBlock(new ItemBlockBasic(this));
    }

}
