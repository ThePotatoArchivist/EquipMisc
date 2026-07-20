package archives.tater.equipmisc.mixin.shears;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.cow.MushroomCow;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.TripWireBlock;

@Mixin({
        BeehiveBlock.class,
        Bogged.class,
        CopperGolem.class,
        Entity.class,
        LeashFenceKnotEntity.class,
        MushroomCow.class,
        PumpkinBlock.class,
        Sheep.class,
        SnowGolem.class,
        SulfurCube.class,
        TripWireBlock.class,
})
public class HardcodedShearsCheckMixin {
    @Definition(id = "is", method = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z")
    @Definition(id = "SHEARS", field = "Lnet/minecraft/world/item/Items;SHEARS:Lnet/minecraft/world/item/Item;")
    @Expression("?.is(SHEARS)")
    @WrapOperation(
            method = "*",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private boolean checkBronzeShears(ItemStack instance, Object rawType, Operation<Boolean> original) {
        return original.call(instance, rawType) || instance.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}
