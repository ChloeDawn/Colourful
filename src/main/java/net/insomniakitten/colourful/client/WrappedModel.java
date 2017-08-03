package net.insomniakitten.colourful.client;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class WrappedModel {

    private final Item item;
    private final int meta;
    private final ResourceLocation resource;
    private final String variants;

    private WrappedModel(ModelBuilder builder) {
        this.item = builder.item;
        this.meta = builder.meta;
        this.resource = builder.resource;
        StringBuilder k = new StringBuilder();
        for (String variant : builder.variants)
            k.append(variant).append(",");
        this.variants = k.toString().replaceFirst("\\W$", "");
    }

    public Item getItem() {
        return item;
    }

    public int getMetadata() {
        return meta;
    }

    public ResourceLocation getResourceLocation() {
        return resource;
    }

    public String getVariants() {
        return variants;
    }

    public static class ModelBuilder {
        private Item item;
        private int meta;
        private ResourceLocation resource;
        private ArrayList<String> variants = new ArrayList<>();

        public ModelBuilder(Item item, int meta) {
            this.item = item;
            this.meta = meta;
            this.resource = item.getRegistryName();
        }

        public ModelBuilder(Block block, int meta) {
            this(Item.getItemFromBlock(block), meta);
        }

        public ModelBuilder(Item item) {
            this(item, 0);
        }

        public ModelBuilder(Block block) {
            this(Item.getItemFromBlock(block), 0);
        }

        public ModelBuilder setResourceLocation(ResourceLocation resource) {
            this.resource = resource;
            return this;
        }

        public ModelBuilder addVariant(String variant) {
            this.variants.add(variant);
            return this;
        }

        public WrappedModel build() {
            if (variants.isEmpty())
                variants.add("inventory");
            return new WrappedModel(this);
        }

    }

}
