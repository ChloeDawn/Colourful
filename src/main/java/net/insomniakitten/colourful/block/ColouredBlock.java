package net.insomniakitten.colourful.block;

import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.data.ColorVariant;
import net.insomniakitten.colourful.item.SimpleBlockItem;
import net.insomniakitten.colourful.item.VariantBlockItem;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ColouredBlock extends AbstractBlock {

    private final SimpleBlockItem item = new VariantBlockItem<>(this, "color", ColorVariant.VALUES);

    public ColouredBlock(BlockMaterial material) {
        super(material);
    }

    @Override
    public int serialize(IBlockState state) {
        return state.getValue(ColorVariant.COLOR).getMetadata();
    }

    @Override
    public IBlockState deserialize(int meta) {
        ColorVariant color = ColorVariant.VALUES[meta];
        return getDefaultState().withProperty(ColorVariant.COLOR, color);
    }

    @Override
    public MapColor getColor(IBlockState state) {
        return state.getValue(ColorVariant.COLOR).getMapColor();
    }

    @Override
    public void getVariants(NonNullList<ItemStack> variants) {
        for (ColorVariant color : ColorVariant.VALUES) {
            variants.add(new ItemStack(this, 1, color.getMetadata()));
        }
    }

    @Override
    public void getProperties(BlockStateContainer.Builder builder) {
        builder.add(ColorVariant.COLOR);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, state.getValue(ColorVariant.COLOR).getMetadata());
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState().withProperty(ColorVariant.COLOR, ColorVariant.VALUES[meta]);
    }

    @Override
    public SimpleBlockItem getItem() {
        return item;
    }

}
