package net.insomniakitten.colourful.common.block.color;

import net.insomniakitten.colourful.Colourful;
import net.insomniakitten.colourful.RegistryManager;
import net.insomniakitten.colourful.common.EnumColors;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockColor extends Block {

    public static final PropertyEnum<EnumColors> COLOR = PropertyEnum.create("color", EnumColors.class);

    public BlockColor(String name, Material material, float hardness, float resistance) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Colourful.MOD_ID + "." + name);
        setCreativeTab(Colourful.CTAB);
        setHardness(hardness);
        setResistance(resistance);
        registerItemBlock();
    }

    protected void registerItemBlock() {
        RegistryManager.registerItemBlock(new ItemBlockColor(this));
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(COLOR, EnumColors.getColor(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(COLOR).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, COLOR);
    }

    @Override
    public void getDrops(
            NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(new ItemStack(this, 1, getMetaFromState(state)));
    }

    @Override
    public ItemStack getPickBlock(
            IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, getMetaFromState(state));
    }

    @Nullable
    public EnumFacing.Axis getAxis() {
        return null; // Overridden by pillar subtypes
    }

}
