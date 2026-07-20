package archives.tater.equipmisc.mixin.shears;

//@Mixin(ItemStack.class)
//public abstract class ItemStackMixin implements ItemInstance {
//    @ModifyReturnValue(
//            method = "is(Lnet/minecraft/world/item/Item;)Z",
//            at = @At("RETURN")
//    )
//    private boolean shearCheck(boolean original, @Local(argsOnly = true) Item item) {
//        return original || item == Items.SHEARS && is(ConventionalItemTags.SHEAR_TOOLS);
//    }
//
//    @ModifyReturnValue(
//            method = "is(Lnet/minecraft/core/HolderSet;)Z",
//            at = @At("RETURN")
//    )
//    private boolean shearCheck(boolean original, @Local(argsOnly = true) HolderSet<Item> list) {
//        return original || list.isBound() && list.size() == 1 && list.get(0).value() == Items.SHEARS && this.is(ConventionalItemTags.SHEAR_TOOLS);
//    }
//}
