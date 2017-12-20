package net.insomniakitten.colourful.block;

import net.insomniakitten.colourful.Colourful;
import net.insomniakitten.colourful.client.color.IColorSupplier;
import net.insomniakitten.colourful.client.model.IStateRemapper;
import net.insomniakitten.colourful.client.model.PackedModel;
import net.insomniakitten.colourful.data.BlockColor;
import net.insomniakitten.colourful.data.BlockFormat;
import net.insomniakitten.colourful.data.BlockMaterial;
import net.insomniakitten.colourful.util.IItemSupplier;
import net.insomniakitten.colourful.item.ColourfulBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public class ColourfulBlock extends Block implements IItemSupplier, IStateRemapper, IColorSupplier {

    protected final ResourceLocation modelPath;
    protected final BlockMaterial material;
    protected final BlockColor color;
    protected final BlockFormat format;

    public ColourfulBlock(BlockMaterial material, BlockColor color, BlockFormat format) {
        super(material.getMaterial(), color.getMapColor());
        this.modelPath = new ResourceLocation(Colourful.ID, material.getName() + "_" + format.getName());
        this.material = material;
        this.color = color;
        this.format = format;
        setSoundType(material.getSound());
        setHardness(material.getHardness());
        setResistance(material.getResistance());
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return material.getRenderLayer();
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
    public Item getItem() {
        return new ColourfulBlockItem(this) {
            @Override
            public void addModels(Set<PackedModel> models) {
                models.add(new PackedModel.Builder(this)
                        .setResourceLocation(modelPath)
                        .addVariant("color=" + color.getName())
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
                return new ModelResourceLocation(modelPath, "color=" + color.getName());
            }
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColor() {
        return color.getDyeColor().getColorValue();
    }

}
