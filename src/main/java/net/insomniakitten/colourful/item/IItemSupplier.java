package net.insomniakitten.colourful.item;

import net.insomniakitten.colourful.block.AbstractBlock;

public interface IItemSupplier<B extends AbstractBlock> {

    SimpleBlockItem getItem();

}
