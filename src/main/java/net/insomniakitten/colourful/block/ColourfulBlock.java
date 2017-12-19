package net.insomniakitten.colourful.block;

import net.insomniakitten.colourful.Colourful;
import net.insomniakitten.colourful.client.model.ICustomStateMapper;
import net.insomniakitten.colourful.client.model.PackedModel;
import net.insomniakitten.colourful.data.BlockColor;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.data.BlockPattern;
import net.insomniakitten.colourful.data.BlockType;
import net.insomniakitten.colourful.item.IItemSupplier;
import net.insomniakitten.colourful.item.SimpleBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Set;

public class ColourfulBlock extends Block implements IItemSupplier<ColourfulBlock>, ICustomStateMapper {

    protected final ResourceLocation modelPath;
    protected final String modelVariants;

    protected final BlockType type;
    protected final BlockMaterial material;
    protected final BlockColor color;
    protected final BlockPattern pattern;

    public ColourfulBlock(BlockType type, BlockMaterial material, BlockColor color, BlockPattern pattern) {
        super(material.getMaterial(), color.getMapColor());
        this.modelPath = new ResourceLocation(Colourful.ID, type.getName() + "_" + material.getName());
        this.modelVariants = "color=" + color.getName() + ",pattern=" + pattern.getName();
        this.type = type;
        this.material = material;
        this.color = color;
        this.pattern = pattern;
        setSoundType(material.getSound());
        setHardness(material.getHardness());
        setResistance(material.getResistance());
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public String getUnlocalizedName() {
        String name = super.getUnlocalizedName();
        return name.replace("tile.", "block.");
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this);
    }

    @Override
    public SimpleBlockItem getItem() {
        return new SimpleBlockItem(this) {
            @Override
            public void addModels(Set<PackedModel> models) {
                models.add(new PackedModel.Builder(this)
                        .setResourceLocation(modelPath)
                        .addVariant(modelVariants)
                        .build()
                );
            }
        };
    }

    @Override
    public IStateMapper getStateMapper() {
        return new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(modelPath, modelVariants);
            }
        };
    }

}
