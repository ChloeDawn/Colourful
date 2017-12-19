package net.insomniakitten.colourful.block;

import net.insomniakitten.colourful.client.model.PackedModel;
import net.insomniakitten.colourful.data.BlockColor;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.data.BlockPattern;
import net.insomniakitten.colourful.data.BlockType;
import net.insomniakitten.colourful.item.SimpleBlockItem;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class ColourfulPillarBlock extends ColourfulBlock {

    private static final PropertyEnum<Axis> PROPERTY = PropertyEnum.create("axis", Axis.class);
    private static final Axis[] VALUES = Axis.values();

    public ColourfulPillarBlock(BlockType type, BlockMaterial material, BlockColor color, BlockPattern pattern) {
        super(type, material, color, pattern);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(PROPERTY).ordinal();
    }

    @Override
    public SimpleBlockItem getItem() {
        return new SimpleBlockItem(this) {
            @Override
            public void addModels(Set<PackedModel> models) {
                models.add(new PackedModel.Builder(this)
                        .setResourceLocation(modelPath)
                        .addVariant("axis=y," + modelVariants)
                        .build()
                );
            }
        };
    }

    @Override
    public IStateMapper getStateMapper() {
        return new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                String axis = "axis=" + state.getValue(PROPERTY).getName();
                return new ModelResourceLocation(modelPath, axis + "," + modelVariants);
            }
        };
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(PROPERTY, VALUES[meta]);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTY);
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        IBlockState state = world.getBlockState(pos);
        state = state.withProperty(PROPERTY, axis.getAxis());
        return world.setBlockState(pos, state);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState().withProperty(PROPERTY, side.getAxis());
    }

}
