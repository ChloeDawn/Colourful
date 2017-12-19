package net.insomniakitten.colourful.data;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

public enum ColorVariant implements IStringSerializable {

    BLACK("dyeBlack", MapColor.BLACK),
    RED("dyeRed", MapColor.RED),
    GREEN("dyeGreen", MapColor.GREEN),
    BROWN("dyeBrown", MapColor.BROWN),
    BLUE("dyeBlue", MapColor.BLUE),
    PURPLE("dyePurple", MapColor.PURPLE),
    CYAN("dyeCyan", MapColor.CYAN),
    SILVER("dyeLightGray", MapColor.SILVER),
    GRAY("dyeGray", MapColor.GRAY),
    PINK("dyePink", MapColor.PINK),
    LIME("dyeLime", MapColor.LIME),
    YELLOW("dyeYellow", MapColor.YELLOW),
    LIGHT_BLUE("dyeLightBlue", MapColor.LIGHT_BLUE),
    MAGENTA("dyeMagenta", MapColor.MAGENTA),
    ORANGE("dyeOrange", MapColor.GOLD),
    WHITE("dyeWhite", MapColor.SNOW),
    ;

    public static final ColorVariant[] VALUES = ColorVariant.values();

    public static final PropertyEnum<ColorVariant> COLOR = PropertyEnum.create("color", ColorVariant.class);

    private final String oreDict;
    private final MapColor mapColor;

    ColorVariant(String oreDict, MapColor mapColor) {
        this.oreDict = oreDict;
        this.mapColor = mapColor;
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public String getOreDict() {
        return oreDict;
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    public int getMetadata() {
        return ordinal();
    }

    @SideOnly(Side.CLIENT)
    public String getTooltip() {
        return I18n.format("tooltip.colourful.color." + getName());
    }

}
