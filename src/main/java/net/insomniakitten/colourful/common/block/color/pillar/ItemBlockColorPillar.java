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
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemBlockColorPillar extends ItemBlockColor {

    protected final Block rotationX;
    protected final Block rotationY;
    protected final Block rotationZ;

    public ItemBlockColorPillar(Block block) {
        super(block);
        ResourceLocation x = new ResourceLocation(getRegistryName() + "_x");
        rotationX = ForgeRegistries.BLOCKS.getValue(x);
        ResourceLocation y = new ResourceLocation(getRegistryName() + "_y");
        rotationY = ForgeRegistries.BLOCKS.getValue(y);
        ResourceLocation z = new ResourceLocation(getRegistryName() + "_z");
        rotationZ = ForgeRegistries.BLOCKS.getValue(z);
    }

    @Override
    public ResourceLocation getActualRegistryName(Block block) {
        //noinspection ConstantConditions
        String base = block.getRegistryName().toString();
        return new ResourceLocation(base.substring(0, base.length() - 2));
    }

    @Override
    protected void generateModels() {
        for (EnumColors color : EnumColors.values()) {
            ModelRegistry.registerModel(new ModelBuilder(this, color.getMetadata())
                    .addVariant("axis=y")
                    .addVariant("color=" + color.getName())
                    .build()
            );
        }
    }

    @Override
    public EnumActionResult onItemUse(
            EntityPlayer player, World world, BlockPos pos,
            EnumHand hand, EnumFacing facing,
            float hitX, float hitY, float hitZ) {
        Block block = world.getBlockState(pos).getBlock();
        pos = block.isReplaceable(world, pos) ? pos : pos.offset(facing);
        ItemStack stack = player.getHeldItem(hand);
        Block blockToPlace = getBlockFromAxis(facing.getAxis());
        if (blockToPlace != null && !stack.isEmpty()
                && player.canPlayerEdit(pos, facing, stack)
                && world.mayPlace(this.block, pos, false, facing, null)) {
            EnumColors color = EnumColors.getColor(stack.getMetadata());
            IBlockState stateToPlace = blockToPlace.getDefaultState().withProperty(BlockColor.COLOR, color);
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
        switch (axis) {
            case X: return rotationX;
            case Y: return rotationY;
            case Z: return rotationZ;
        }
        return null;
    }

}
