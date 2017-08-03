package net.insomniakitten.colourful.common;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

public enum EnumColors implements IStringSerializable {

    BLACK("dyeBlack"),
    RED("dyeRed"),
    GREEN("dyeGreen"),
    BROWN("dyeBrown"),
    BLUE("dyeBlue"),
    PURPLE("dyePurple"),
    CYAN("dyeCyan"),
    SILVER("dyeLightGray"),
    GRAY("dyeGray"),
    PINK("dyePink"),
    LIME("dyeLime"),
    YELLOW("dyeYellow"),
    LIGHT_BLUE("dyeLightBlue"),
    MAGENTA("dyeMagenta"),
    ORANGE("dyeOrange"),
    WHITE("dyeWhite"),
    ;

    private static final String PREFIX = "tooltip.colourful.color.";
    private final String oreDict;

    EnumColors(String oreDict) {
        this.oreDict = oreDict;
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public String getOreDict() {
        return oreDict;
    }

    public int getMetadata() {
        return ordinal();
    }

    @SideOnly(Side.CLIENT)
    public String getTooltip() {
        return I18n.format(PREFIX + getName());
    }

    public static EnumColors getColor(int meta) {
        return values()[meta % values().length];
    }

}
