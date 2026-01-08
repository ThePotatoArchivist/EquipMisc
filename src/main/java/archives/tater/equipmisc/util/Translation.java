package archives.tater.equipmisc.util;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public sealed class Translation {
    public final String key;
    protected final @Nullable String fallback;
    protected final Consumer<MutableComponent> init;

    public Translation(String key, @Nullable String fallback, Consumer<MutableComponent> init) {
        this.key = key;
        this.fallback = fallback;
        this.init = init;
    }

    public static final class Unit extends Translation {
        public final Component text = text();

        public Unit(String key, @Nullable String fallback, Consumer<MutableComponent> init) {
            super(key, fallback, init);
        }

        public MutableComponent text() {
            var text = Component.translatableWithFallback(key, fallback);
            init.accept(text);
            return text;
        }
    }

    public static final class Arg extends Translation {
        public Arg(String key, @Nullable String fallback, Consumer<MutableComponent> init) {
            super(key, fallback, init);
        }

        public MutableComponent text(Object... args) {
            var text =  Component.translatableWithFallback(key, fallback, args);
            init.accept(text);
            return text;
        }
    }

    private static final Consumer<MutableComponent> NO_OP = ignored -> {};

    public static Unit unit(String key, String fallback, Consumer<MutableComponent> init) {
        return new Unit(key, fallback, init);
    }

    public static Unit unit(String key, String fallback, ChatFormatting... formatting) {
        return new Unit(key, fallback, text -> text.withStyle(formatting));
    }

    public static Unit unit(String key, Consumer<MutableComponent> init) {
        return unit(key, null, init);
    }

    public static Unit unit(String key, ChatFormatting... formatting) {
        return unit(key, null, text -> text.withStyle(formatting));
    }

    public static Unit unit(String key, String fallback) {
        return unit(key, fallback, NO_OP);
    }

    public static Unit unit(String key) {
        return unit(key, NO_OP);
    }

    public static Arg arg(String key, String fallback, Consumer<MutableComponent> init) {
        return new Arg(key, fallback, init);
    }

    public static Arg arg(String key, String fallback, ChatFormatting... formatting) {
        return new Arg(key, fallback, text -> text.withStyle(formatting));
    }

    public static Arg arg(String key, Consumer<MutableComponent> init) {
        return arg(key, null, init);
    }

    public static Arg arg(String key, ChatFormatting... formatting) {
        return arg(key, null, text -> text.withStyle(formatting));
    }

    public static Arg arg(String key, String fallback) {
        return arg(key, fallback, NO_OP);
    }

    public static Arg arg(String key) {
        return arg(key, NO_OP);
    }
}
