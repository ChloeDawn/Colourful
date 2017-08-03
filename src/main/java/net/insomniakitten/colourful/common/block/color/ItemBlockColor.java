package net.insomniakitten.colourful.common.block.color;

import net.insomniakitten.colourful.client.ModelRegistry;
import net.insomniakitten.colourful.client.WrappedModel.ModelBuilder;
import net.insomniakitten.colourful.common.EnumColors;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemBlockColor extends ItemBlock {

    public ItemBlockColor(Block block) {
        super(block);
        assert block.getRegistryName() != null;
        setRegistryName(getActualRegistryName(block));
        setCreativeTab(block.getCreativeTabToDisplayOn());
        setHasSubtypes(true);
        generateModels();
    }

    public ResourceLocation getActualRegistryName(Block block) {
        return block.getRegistryName();
    }

    public void generateModels() {
        for (EnumColors color : EnumColors.values()) {
            ModelBuilder builder = new ModelBuilder(this, color.getMetadata());
            builder.addVariant("color=" + color.getName());
            ModelRegistry.registerModel(builder.build());
        }
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumColors colors : EnumColors.values()) {
                items.add(new ItemStack(this, 1, colors.getMetadata()));
            }
        }
    }

    @Override @Nonnull @SideOnly(Side.CLIENT)
    public String getUnlocalizedName(ItemStack stack) {
        String color = EnumColors.getColor(stack.getMetadata()).getName();
        return super.getUnlocalizedName(stack) + "." + color;
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

}
