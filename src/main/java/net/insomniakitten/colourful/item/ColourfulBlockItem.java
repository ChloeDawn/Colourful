package net.insomniakitten.colourful.item;

import net.insomniakitten.colourful.block.ColourfulBlock;
import net.insomniakitten.colourful.client.color.IColorSupplier;
import net.insomniakitten.colourful.client.model.IModelSupplier;
import net.insomniakitten.colourful.client.model.PackedModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Set;

public class ColourfulBlockItem extends ItemBlock implements IModelSupplier, IColorSupplier {

    public ColourfulBlockItem(ColourfulBlock block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        for (int i = 0; I18n.hasKey(getUnlocalizedName(stack) + ".desc" + i); i++) {
            tooltip.add(I18n.format(getUnlocalizedName(stack) + ".desc" + i));
        }
    }

    @Override
    public void addModels(Set<PackedModel> models) {
        models.add(new PackedModel.Builder(this, "normal").build());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColor() {
        return ((ColourfulBlock) block).getColor();
    }

}
