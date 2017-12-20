package net.insomniakitten.colourful.block;

import net.insomniakitten.colourful.client.model.PackedModel;
import net.insomniakitten.colourful.data.BlockColor;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.data.BlockFormat;
import net.insomniakitten.colourful.data.PillarAxis;
import net.insomniakitten.colourful.item.ColourfulBlockItem;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.Set;

public class ColourfulPillarBlock extends ColourfulBlock {

    private static final PropertyEnum<PillarAxis> PROPERTY = PropertyEnum.create("axis", PillarAxis.class);

    public ColourfulPillarBlock(BlockMaterial material, BlockColor color, BlockFormat format) {
        super(material, color, format);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(PROPERTY).ordinal();
    }

    @Override
    public Item getItem() {
        return new ColourfulBlockItem(this) {
            @Override
            public void addModels(Set<PackedModel> models) {
                models.add(new PackedModel.Builder(this)
                        .setResourceLocation(modelPath)
                        .addVariant("axis=y,color=" + color.getName())
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
                String axis = state.getValue(PROPERTY).getName();
                String variant = "axis=" + axis + ",color=" + color.getName();
                return new ModelResourceLocation(modelPath, variant);
            }
        };
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(PROPERTY, PillarAxis.VALUES[meta]);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTY);
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        Optional<PillarAxis> pillarAxis = PillarAxis.getAxis(axis.getAxis());
        if (pillarAxis.isPresent()) {
            IBlockState state = world.getBlockState(pos);
            state = state.withProperty(PROPERTY, pillarAxis.get());
            return world.setBlockState(pos, state);
        }
        return false;
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState().withProperty(PROPERTY, PillarAxis.getAxis(side.getAxis()).orElse(PillarAxis.Y));
    }

}
