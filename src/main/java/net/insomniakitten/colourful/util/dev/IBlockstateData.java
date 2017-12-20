package net.insomniakitten.colourful.util.dev;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.IStringSerializable;

public interface IBlockstateData<E extends Enum & IStringSerializable> extends IStringSerializable {

    default Object getDefaults() {
        return ImmutableMap.of();
    }

    default ImmutableMap<String, E[]> getVariants() {
        return ImmutableMap.of();
    }

}
