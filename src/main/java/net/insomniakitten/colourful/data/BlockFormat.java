package net.insomniakitten.colourful.data;

import net.insomniakitten.colourful.block.ColourfulBlock;
import net.insomniakitten.colourful.block.ColourfulPillarBlock;
import net.minecraft.block.Block;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum BlockFormat implements IStringSerializable {

    BLOCK {
        @Override
        public Block makeInstance(BlockMaterial material, BlockColor color) {
            return new ColourfulBlock(material, color, this);
        }
    },

    BRICKS {
        @Override
        public Block makeInstance(BlockMaterial material, BlockColor color) {
            return new ColourfulBlock(material, color, this);
        }
    },

    CHISELED {
        @Override
        public Block makeInstance(BlockMaterial material, BlockColor color) {
            return new ColourfulBlock(material, color, this);
        }
    },

    PILLAR {
        @Override
        public Block makeInstance(BlockMaterial material, BlockColor color) {
            return new ColourfulPillarBlock(material, color, this);
        }
    };

    public static final BlockFormat[] VALUES = BlockFormat.values();

    public abstract Block makeInstance(BlockMaterial material, BlockColor color);

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

}
