package net.insomniakitten.colourful.common.block.color.pillar;

import net.insomniakitten.colourful.common.EnumColors;
import net.insomniakitten.colourful.common.block.color.BlockColor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PillarResourceManager {

    public static final List<Block> PILLAR_SUBTYPES = new ArrayList<>();

    public static void registerComponents(
            Block parent, String name, Material material,
            float hardness, float resistance) {
        PILLAR_SUBTYPES.add(new BlockColor(name + "_x", material, hardness, resistance) {
            @Override
            public void registerItemBlock() {
                // no-op
            }

            @Override
            public ItemStack getPickBlock(
                    IBlockState state, RayTraceResult target,
                    World world, BlockPos pos, EntityPlayer player) {
                return new ItemStack(parent, 1, parent.getMetaFromState(state));
            }

            @Override
            public void getDrops(
                    NonNullList<ItemStack> drops, IBlockAccess world,
                    BlockPos pos, IBlockState state, int fortune) {
                drops.add(new ItemStack(parent, 1, parent.getMetaFromState(state)));
            }

            @Override
            public EnumFacing.Axis getAxis() {
                return EnumFacing.Axis.X;
            }
        });
        PILLAR_SUBTYPES.add(new BlockColor(name + "_z", material, hardness, resistance) {
            @Override
            public void registerItemBlock() {
                // no-op
            }

            @Override
            public ItemStack getPickBlock(
                    IBlockState state, RayTraceResult target,
                    World world, BlockPos pos, EntityPlayer player) {
                return new ItemStack(parent, 1, parent.getMetaFromState(state));
            }

            @Override
            public void getDrops(
                    NonNullList<ItemStack> drops, IBlockAccess world,
                    BlockPos pos, IBlockState state, int fortune) {
                drops.add(new ItemStack(parent, 1, parent.getMetaFromState(state)));
            }

            @Override
            public EnumFacing.Axis getAxis() {
                return EnumFacing.Axis.Z;
            }
        });
    }

    public static class StateMapperPillar extends StateMapperBase {

        @Override
        protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
            EnumFacing.Axis prop = ((BlockColor) state.getBlock()).getAxis();
            String axis = prop != null ? prop.getName().toLowerCase(Locale.ROOT) : "null";
            //noinspection ConstantConditions
            String name = state.getBlock().getRegistryName().toString();
            ResourceLocation path = new ResourceLocation(name.substring(0, name.length() - 2));
            String color = EnumColors.getColor(state.getBlock().getMetaFromState(state)).getName();
            String variant = "axis=" + axis + ",color=" + color;
            return new ModelResourceLocation(path, variant);
        }

    }

}
