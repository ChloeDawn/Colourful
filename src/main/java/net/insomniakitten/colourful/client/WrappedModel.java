package net.insomniakitten.colourful.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public final class WrappedModel {

    private final Item item;
    private final int meta;
    private final ResourceLocation resource;
    private final String variants;
    private final ModelResourceLocation mrl;

    private WrappedModel(ModelBuilder model) {
        this.item = model.item;
        this.meta = model.meta;
        this.resource = model.resource;
        this.variants = String.join(",", model.variants);
        this.mrl = new ModelResourceLocation(this.resource, this.variants);
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

    public ModelResourceLocation getMRL() {
        return mrl;
    }

    public static final class ModelBuilder {

        private Item item;
        private int meta;
        private ResourceLocation resource;
        private Set<String> variants = new HashSet<>();

        public ModelBuilder(Item item, int meta) {
            this.item = item;
            this.meta = meta;
            this.resource = item.getRegistryName();
        }

        public ModelBuilder(Item item) {
            this(item, 0);
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
            if (variants.isEmpty()) {
                variants.add("inventory");
            }
            return new WrappedModel(this);
        }

    }

}
