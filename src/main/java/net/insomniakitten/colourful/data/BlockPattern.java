package net.insomniakitten.colourful.data;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum BlockPattern implements IStringSerializable {

    NONE,
    BRICKS,
    CHISELED;

    public static final BlockPattern[] VALUES = BlockPattern.values();

    public String getSuffix() {
        return !equals(NONE) ? "_" + getName() : "";
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

}
