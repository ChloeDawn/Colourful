package net.insomniakitten.colourful.common.block.color.pillar;

import net.insomniakitten.colourful.client.ModelRegistry;
import net.insomniakitten.colourful.client.WrappedModel.ModelBuilder;
import net.insomniakitten.colourful.common.EnumColors;
import net.insomniakitten.colourful.common.block.color.BlockColor;
import net.insomniakitten.colourful.common.block.color.ItemBlockColor;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

public class ItemBlockColorPillar extends ItemBlockColor {

    protected final Block rotationX;
    protected final Block rotationY;
    protected final Block rotationZ;

    public ItemBlockColorPillar(Block block) {
        super(block);
        ResourceLocation x = new ResourceLocation(this.getRegistryName() + "_x");
        rotationX = ForgeRegistries.BLOCKS.getValue(x);
        ResourceLocation y = new ResourceLocation(this.getRegistryName() + "_y");
        rotationY = ForgeRegistries.BLOCKS.getValue(y);
        ResourceLocation z = new ResourceLocation(this.getRegistryName() + "_z");
        rotationZ = ForgeRegistries.BLOCKS.getValue(z);
    }

    @Override @SuppressWarnings("ConstantConditions")
    public ResourceLocation getActualRegistryName(Block block) {
        String base = block.getRegistryName().toString();
        return new ResourceLocation(base.substring(0, base.length() - 2));
    }

    @Override
    public void generateModels() {
        for (EnumColors color : EnumColors.values()) {
            ModelBuilder builder = new ModelBuilder(this, color.getMetadata());
            builder.addVariant("axis=y");
            builder.addVariant("color=" + color.getName());
            ModelRegistry.registerModel(builder.build());
        }
    }

    @Override @Nonnull
    public EnumActionResult onItemUse(
            EntityPlayer player, World world, @Nonnull BlockPos pos,
            @Nonnull EnumHand hand, @Nonnull EnumFacing facing,
            float hitX, float hitY, float hitZ) {
        Block block = world.getBlockState(pos).getBlock();
        if (!block.isReplaceable(world, pos)) {
            pos = pos.offset(facing);
        }
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty() && player.canPlayerEdit(pos, facing, stack)
                && world.mayPlace(this.block, pos, false, facing, null)) {
            Block blockToPlace = rotationY;
            switch (facing) {
                case NORTH: case SOUTH:
                    blockToPlace = rotationZ;
                    break;
                case WEST: case EAST:
                    blockToPlace = rotationX;
                    break;
            }
            IBlockState stateToPlace = blockToPlace.getDefaultState()
                    .withProperty(BlockColor.COLOR, EnumColors.getColor(stack.getMetadata()));
            if (placeBlockAt(stack, player, world, pos, facing, hitX, hitY, hitZ, stateToPlace)) {
                stateToPlace = world.getBlockState(pos);
                SoundType soundtype = stateToPlace.getBlock().getSoundType(stateToPlace, world, pos, player);
                world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
                        (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                stack.shrink(1);
            }
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }

}
