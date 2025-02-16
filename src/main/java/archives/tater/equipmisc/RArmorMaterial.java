package archives.tater.equipmisc;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

import java.util.EnumMap;
import java.util.function.Supplier;

public record RArmorMaterial(
        String name,
        int durabilityMultiplier,
        EnumMap<Type, Integer> protectionAmounts,
        int enchantability,
        SoundEvent equipSound,
        float toughness,
        float knockbackResistance,
        Supplier<Ingredient> repairIngredientSupplier
) implements ArmorMaterial {
    private static final EnumMap<Type, Integer> BASE_DURABILITY = armorTypeMap(13, 15, 16, 11);

    @Override
    public int getDurability(Type type) {
        return durabilityMultiplier * BASE_DURABILITY.get(type);
    }

    @Override
    public int getProtection(Type type) {
        return protectionAmounts.get(type);
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredientSupplier.get();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public static <T> EnumMap<Type, T> armorTypeMap(T helmet, T chestplate, T leggings, T boots) {
        var map = new EnumMap<Type, T>(Type.class);
        map.put(Type.HELMET, helmet);
        map.put(Type.CHESTPLATE, chestplate);
        map.put(Type.LEGGINGS, leggings);
        map.put(Type.BOOTS, boots);
        return map;
    }
}
