package net.insomniakitten.colourful;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Colourful.ID, name = Colourful.NAME, version = Colourful.VERSION)
public final class Colourful {

    public static final String ID = "colourful";
    public static final String NAME = "Colourful";
    public static final String VERSION = "%VERSION%";

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @GameRegistry.ItemStackHolder("colourful:green_bricks_quartz_block")
    public static final ItemStack ICON = ItemStack.EMPTY;

    public static final CreativeTabs TAB = new CreativeTabs(Colourful.ID) {
        @Override
        @SideOnly(Side.CLIENT)
        public String getTranslatedTabLabel() {
            return "tab." + ID + ".label";
        }

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return ICON;
        }
    };

}
