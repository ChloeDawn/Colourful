package net.insomniakitten.colourful.block;

import net.insomniakitten.colourful.Colourful;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.item.IItemSupplier;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;

public abstract class AbstractBlock extends Block implements IItemSupplier<AbstractBlock> {

    protected final BlockMaterial material;

    public AbstractBlock(BlockMaterial material) {
        super(material.getMaterial());
        this.material = material;
        setSoundType(material.getSound());
        setHardness(material.getHardness());
        setResistance(material.getResistance());
        setCreativeTab(Colourful.TAB);
    }

    public abstract int serialize(IBlockState state);

    public abstract IBlockState deserialize(int meta);

    public abstract MapColor getColor(IBlockState state);

    public abstract void getVariants(NonNullList<ItemStack> variants);

    public abstract void getProperties(BlockStateContainer.Builder builder);

    public void register(String name, RegistryEvent.Register<Block> event) {
        event.getRegistry().register(this.setRegistryName(Colourful.ID, name));
    }

    @Override
    @Deprecated
    public final MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getColor(state);
    }

    @Override
    @Deprecated
    public final IBlockState getStateFromMeta(int meta) {
        return deserialize(meta);
    }

    @Override
    public final int getMetaFromState(IBlockState state) {
        return serialize(state);
    }

    @Override
    public String getUnlocalizedName() {
        if (getRegistryName() != null) {
            String name = getRegistryName().getResourcePath();
            return "tile." + Colourful.ID + "." + name;
        }
        return "null";
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (getCreativeTabToDisplayOn().equals(tab)) {
            getVariants(items);
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        BlockStateContainer.Builder builder = new BlockStateContainer.Builder(this);
        getProperties(builder);
        return builder.build();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }

}
