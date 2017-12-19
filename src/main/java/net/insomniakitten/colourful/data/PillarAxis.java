package net.insomniakitten.colourful.data;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.EnumFacing;

import java.util.Locale;
import java.util.Optional;

public enum PillarAxis implements IJsonData {

    X(EnumFacing.Axis.X) {
        @Override
        public Object getJsonData() {
            return ImmutableMap.of("x", 90, "y", 90);
        }
    },

    Y(EnumFacing.Axis.Y) {
        @Override
        public Object getJsonData() {
            return ImmutableMap.of("x", 0, "y", 0);
        }
    },

    Z(EnumFacing.Axis.Z) {
        @Override
        public Object getJsonData() {
            return ImmutableMap.of("x", 90, "y", 0);
        }
    };

    public static final PillarAxis[] VALUES = PillarAxis.values();

    private final EnumFacing.Axis facingAxis;

    PillarAxis(EnumFacing.Axis facingAxis) {
        this.facingAxis = facingAxis;
    }

    public EnumFacing.Axis getFacingAxis() {
        return facingAxis;
    }

    public static Optional<PillarAxis> getAxis(EnumFacing.Axis axis) {
        for (PillarAxis pillarAxis : VALUES) {
            if (pillarAxis.facingAxis == axis) {
                return Optional.of(pillarAxis);
            }
        }
        return Optional.empty();
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

}
