package net.insomniakitten.colourful.item;

import com.google.common.collect.ImmutableMap;
import net.insomniakitten.colourful.block.ColouredBlock;
import net.insomniakitten.colourful.block.ColouredPillarBlock;
import net.insomniakitten.colourful.client.model.PackedModel;
import net.insomniakitten.colourful.data.ColorVariant;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class PillarBlockItem<E extends Enum<E> & IStringSerializable> extends VariantBlockItem<E> {

    private final ImmutableMap<EnumFacing.Axis, ColouredPillarBlock> rotations;

    public PillarBlockItem(ColouredPillarBlock block, String prefix, E[] variants) {
        super(block, prefix, variants);
        this.rotations = block.getRotations();
    }

    @Override
    public void addModels(Set<PackedModel> models) {
        for (E variant : variants) {
            int meta = variant.ordinal();
            String var = prefix + "=" + variant.getName();
            models.add(new PackedModel.Builder(this, meta, var)
                    .addVariant("axis=y").build());
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Block block = world.getBlockState(pos).getBlock();
        pos = block.isReplaceable(world, pos) ? pos : pos.offset(facing);
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty() && player.canPlayerEdit(pos, facing, stack) && world.mayPlace(this.block, pos, false, facing, null)) {
            Block blockToPlace = getBlockFromAxis(facing.getAxis());
            IBlockState stateToPlace = blockToPlace.getDefaultState().withProperty(
                    ColouredBlock.COLOR, ColorVariant.getColor(stack.getMetadata())
            );
            if (placeBlockAt(stack, player, world, pos, facing, hitX, hitY, hitZ, stateToPlace)) {
                stateToPlace = world.getBlockState(pos);
                SoundType soundtype = stateToPlace.getBlock().getSoundType(stateToPlace, world, pos, player);
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
                        (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                stack.shrink(1);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    private Block getBlockFromAxis(EnumFacing.Axis axis) {
        return rotations.getOrDefault(axis, (ColouredPillarBlock) block);
    }

}
