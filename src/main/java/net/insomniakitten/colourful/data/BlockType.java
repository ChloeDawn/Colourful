package net.insomniakitten.colourful.data;

import com.google.common.collect.ImmutableMap;
import net.insomniakitten.colourful.block.ColourfulBlock;
import net.insomniakitten.colourful.block.ColourfulPillarBlock;
import net.minecraft.block.Block;

import java.util.Locale;

public enum BlockType implements IJsonData {

    BLOCK {
        @Override
        public Block getBlock(BlockMaterial material, BlockColor color, BlockPattern pattern) {
            return new ColourfulBlock(this, material, color, pattern);
        }

        @Override
        public Object getJsonData() {
            return ImmutableMap.of("model", "cube_all");
        }
    },

    PILLAR {
        @Override
        public Block getBlock(BlockMaterial material, BlockColor color, BlockPattern pattern) {
            return new ColourfulPillarBlock(this, material, color, pattern);
        }
        @Override
        public Object getJsonData() {
            return ImmutableMap.of("model", "cube_column");
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
