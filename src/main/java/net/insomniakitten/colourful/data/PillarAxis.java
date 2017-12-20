package net.insomniakitten.colourful.data;

import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;
import java.util.Optional;

public enum PillarAxis implements IStringSerializable {

    X(Axis.X),
    Y(Axis.Y),
    Z(Axis.Z);

    public static final PillarAxis[] VALUES = PillarAxis.values();

    private final Axis facingAxis;

    PillarAxis(Axis facingAxis) {
        this.facingAxis = facingAxis;
    }

    public Axis getFacingAxis() {
        return facingAxis;
    }

    public static Optional<PillarAxis> getAxis(Axis axis) {
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
