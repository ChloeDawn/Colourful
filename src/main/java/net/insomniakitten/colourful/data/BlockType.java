package net.insomniakitten.colourful.data;

import net.insomniakitten.colourful.block.ColourfulBlock;
import net.insomniakitten.colourful.block.ColourfulPillarBlock;
import net.minecraft.block.Block;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum BlockType implements IStringSerializable {

    BLOCK {
        @Override
        public Block getBlock(BlockMaterial material, BlockColor color, BlockPattern pattern) {
            return new ColourfulBlock(this, material, color, pattern);
        }
    },

    PILLAR {
        @Override
        public Block getBlock(BlockMaterial material, BlockColor color, BlockPattern pattern) {
            return new ColourfulPillarBlock(this, material, color, pattern);
        }
    };

    public static final BlockType[] VALUES = BlockType.values();

    public abstract Block getBlock(BlockMaterial material, BlockColor color, BlockPattern pattern);

    public String getSuffix() {
        return "_" + getName();
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

}
