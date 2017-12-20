package net.insomniakitten.colourful.data;

import net.minecraft.block.material.MapColor;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum BlockColor implements IStringSerializable {

    BLACK(MapColor.BLACK, EnumDyeColor.BLACK),
    RED(MapColor.RED, EnumDyeColor.RED),
    GREEN(MapColor.GREEN, EnumDyeColor.GREEN),
    BROWN(MapColor.BROWN, EnumDyeColor.BROWN),
    BLUE(MapColor.BLUE, EnumDyeColor.BLUE),
    PURPLE(MapColor.PURPLE, EnumDyeColor.PURPLE),
    CYAN(MapColor.CYAN, EnumDyeColor.CYAN),
    LIGHT_GRAY(MapColor.SILVER, EnumDyeColor.SILVER),
    GRAY(MapColor.GRAY, EnumDyeColor.GRAY),
    PINK(MapColor.PINK, EnumDyeColor.PINK),
    LIME(MapColor.LIME, EnumDyeColor.LIME),
    YELLOW(MapColor.YELLOW, EnumDyeColor.YELLOW),
    LIGHT_BLUE(MapColor.LIGHT_BLUE, EnumDyeColor.LIGHT_BLUE),
    MAGENTA(MapColor.MAGENTA, EnumDyeColor.MAGENTA),
    ORANGE(MapColor.GOLD, EnumDyeColor.ORANGE),
    WHITE(MapColor.SNOW, EnumDyeColor.WHITE);

    public static final BlockColor[] VALUES = BlockColor.values();

    private final MapColor mapColor;
    private final EnumDyeColor dyeColor;

    BlockColor(MapColor mapColor, EnumDyeColor dyeColor) {
        this.mapColor = mapColor;
        this.dyeColor = dyeColor;
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
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
