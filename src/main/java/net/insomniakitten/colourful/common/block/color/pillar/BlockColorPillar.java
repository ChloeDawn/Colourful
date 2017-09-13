package net.insomniakitten.colourful.common.block.color.pillar;

import net.insomniakitten.colourful.common.block.color.BlockColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class BlockColorPillar extends BlockColor {

    public BlockColorPillar(String name, Material material, float hardness, float resistance) {
        super(name + "_y", material, hardness, resistance);
        PillarResourceManager.registerComponents(this, name, material, hardness, resistance);
    }

    @Override
    public void registerItemBlock() {
        // no-op
    }

    @Override
    public String getUnlocalizedName() {
        String base = super.getUnlocalizedName();
        return base.substring(0, base.length() - 2);
    }

    @Override
    public EnumFacing.Axis getAxis() {
        return EnumFacing.Axis.Y;
    }

}
