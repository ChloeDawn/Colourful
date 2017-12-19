package net.insomniakitten.colourful.data;

import net.minecraft.block.material.MapColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum BlockColor implements IStringSerializable {

    BLACK("blockColoredBlack", MapColor.BLACK, EnumDyeColor.BLACK),
    RED("blockColoredRed", MapColor.RED, EnumDyeColor.RED),
    GREEN("blockColoredGreen", MapColor.GREEN, EnumDyeColor.GREEN),
    BROWN("blockColoredBrown", MapColor.BROWN, EnumDyeColor.BROWN),
    BLUE("blockColoredBlue", MapColor.BLUE, EnumDyeColor.BLUE),
    PURPLE("blockColoredPurple", MapColor.PURPLE, EnumDyeColor.PURPLE),
    CYAN("blockColoredCyan", MapColor.CYAN, EnumDyeColor.CYAN),
    SILVER("blockColoredLightGray", MapColor.SILVER, EnumDyeColor.SILVER),
    GRAY("blockColoredGray", MapColor.GRAY, EnumDyeColor.GRAY),
    PINK("blockColoredPink", MapColor.PINK, EnumDyeColor.PINK),
    LIME("blockColoredLime", MapColor.LIME, EnumDyeColor.LIME),
    YELLOW("blockColoredYellow", MapColor.YELLOW, EnumDyeColor.YELLOW),
    LIGHT_BLUE("blockColoredLightBlue", MapColor.LIGHT_BLUE, EnumDyeColor.LIGHT_BLUE),
    MAGENTA("blockColoredMagenta", MapColor.MAGENTA, EnumDyeColor.MAGENTA),
    ORANGE("blockColoredOrange", MapColor.GOLD, EnumDyeColor.ORANGE),
    WHITE("blockColoredWhite", MapColor.SNOW, EnumDyeColor.WHITE);

    public static final BlockColor[] VALUES = BlockColor.values();

    private final String oreDict;
    private final MapColor mapColor;
    private final EnumDyeColor dyeColor;

    BlockColor(String oreDict, MapColor mapColor, EnumDyeColor dyeColor) {
        this.oreDict = oreDict;
        this.mapColor = mapColor;
        this.dyeColor = dyeColor;
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

    public EnumDyeColor getDyeColor() {
        return dyeColor;
    }

    public int getMetadata() {
        return ordinal();
    }

}
