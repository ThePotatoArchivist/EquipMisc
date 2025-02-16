package archives.tater.equipmisc;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public record RToolMaterial(
        int miningLevel,
        int itemDurability,
        float miningSpeed,
        float attackDamage,
        int enchantability,
        Supplier<Ingredient> repairIngredient
) implements ToolMaterial {
    @Override
    public int getDurability() {
        return itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
