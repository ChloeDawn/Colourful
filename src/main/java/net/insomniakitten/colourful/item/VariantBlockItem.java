package net.insomniakitten.colourful.item;

import net.insomniakitten.colourful.block.AbstractBlock;
import net.insomniakitten.colourful.client.model.PackedModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

import java.util.Set;

public class VariantBlockItem<E extends Enum<E> & IStringSerializable> extends SimpleBlockItem {

    protected final String prefix;
    protected final E[] variants;

    public VariantBlockItem(AbstractBlock block, String prefix, E[] variants) {
        super(block);
        this.prefix = prefix;
        this.variants = variants;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        E variant = variants[stack.getMetadata()];
        return getUnlocalizedName() + "." + variant.getName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (E variant : variants) {
                items.add(new ItemStack(this, 1, variant.ordinal()));
            }
        }
    }

    @Override
    public void addModels(Set<PackedModel> models) {
        for (E variant : variants) {
            int meta = variant.ordinal();
            String var = prefix + "=" + variant.getName();
            models.add(new PackedModel.Builder(this, meta, var).build());
        }
    }

}
