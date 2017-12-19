package net.insomniakitten.colourful.data;

import com.google.common.collect.ImmutableMap;

import java.util.Locale;

public enum BlockPattern implements IJsonData {

    NONE {
        @Override
        public Object getJsonData() {
            return ImmutableMap.of("textures", ImmutableMap.of(
                    "all", "blocks/stone",
                    "end", "blocks/stone",
                    "side", "blocks/stone"
            ));
        }
    },
    BRICKS {
        @Override
        public Object getJsonData() {
            return ImmutableMap.of("textures", ImmutableMap.of(
                    "all", "blocks/stone",
                    "end", "blocks/stone",
                    "side", "blocks/stone"
            ));
        }
    },
    CHISELED {
        @Override
        public Object getJsonData() {
            return ImmutableMap.of("textures", ImmutableMap.of(
                    "all", "blocks/stone",
                    "end", "blocks/stone",
                    "side", "blocks/stone"
            ));
        }
    };

    public static final BlockPattern[] VALUES = BlockPattern.values();

    public String getSuffix() {
        return !equals(NONE) ? "_" + getName() : "";
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

}
