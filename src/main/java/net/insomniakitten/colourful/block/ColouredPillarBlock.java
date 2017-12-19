package net.insomniakitten.colourful.block;

import com.google.common.collect.ImmutableMap;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.data.ColorVariant;
import net.insomniakitten.colourful.item.PillarBlockItem;
import net.insomniakitten.colourful.item.SimpleBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;

import java.util.Map;

public class ColouredPillarBlock extends ColouredBlock {

    private final SimpleBlockItem item = new PillarBlockItem<>(this, "color", ColorVariant.VALUES);

    private ImmutableMap<Axis, ColouredPillarBlock> rotations;

    public ColouredPillarBlock(BlockMaterial material) {
        super(material);
    }

    @Override
    public SimpleBlockItem getItem() {
        return item;
    }

    public ImmutableMap<Axis, ColouredPillarBlock> getRotations() {
        if (rotations == null) {
            rotations = ImmutableMap.of(
                    Axis.X, new Subtype(this, material),
                    Axis.Y, this,
                    Axis.Z, new Subtype(this, material)
            );
        }
        return rotations;
    }

    @Override
    public void register(String name, RegistryEvent.Register<Block> event) {
        for (Map.Entry<Axis, ColouredPillarBlock> entry : getRotations().entrySet()) {
            String id = name + "_" + entry.getKey().getName();
            event.getRegistry().register(entry.getValue().setRegistryName(id));
        }
    }

    private final class Subtype extends ColouredPillarBlock {
        private final ColouredPillarBlock delegate;

        private Subtype(ColouredPillarBlock delegate, BlockMaterial material) {
            super(material);
            this.delegate = delegate;
        }

        @Override
        public ImmutableMap<Axis, ColouredPillarBlock> getRotations() {
            return ImmutableMap.of();
        }

        @Override
        public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
            delegate.getDrops(drops, world, pos, state, fortune);
        }

        @Override
        public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
            return delegate.getPickBlock(state, target, world, pos, player);
        }
    }

}
