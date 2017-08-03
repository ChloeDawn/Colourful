package net.insomniakitten.colourful.common.item;

import net.insomniakitten.colourful.Colourful;
import net.insomniakitten.colourful.client.ModelRegistry;
import net.insomniakitten.colourful.client.WrappedModel.ModelBuilder;
import net.insomniakitten.colourful.common.EnumColors;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemPaintbrush extends Item {

    public ItemPaintbrush(){
        setRegistryName("paintbrush");
        setUnlocalizedName(Colourful.MOD_ID + ".paintbrush");
        setCreativeTab(Colourful.CTAB);
        setHasSubtypes(true);
        generateModels();
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

    @Override @SideOnly(Side.CLIENT)
    public void addInformation(
            ItemStack stack, @Nullable World world,
            List<String> tooltip, ITooltipFlag flag) {
        EnumColors color = EnumColors.getColor(stack.getMetadata());
        tooltip.add(color.getTooltip());
    }

}
