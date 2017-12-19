package net.insomniakitten.colourful.block;

import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.item.SimpleBlockItem;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SimpleBlock extends AbstractBlock {

    private final SimpleBlockItem item = new SimpleBlockItem(this);
    private final MapColor mapColor;

    public SimpleBlock(BlockMaterial material, MapColor mapColor) {
        super(material);
        this.mapColor = mapColor;
    }

    @Override
    public int serialize(IBlockState state) {
        return 0;
    }

    @Override
    public IBlockState deserialize(int meta) {
        return getDefaultState();
    }

    @Override
    public MapColor getColor(IBlockState state) {
        return mapColor;
    }

    @Override
    public void getVariants(NonNullList<ItemStack> variants) {
        variants.add(new ItemStack(this));
    }

    @Override
    public void getProperties(BlockStateContainer.Builder builder) {}

    @Override
    public SimpleBlockItem getItem() {
        return item;
    }

}
