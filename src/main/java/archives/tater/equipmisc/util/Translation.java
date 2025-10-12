package archives.tater.equipmisc.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public sealed class Translation {
    public final String key;
    protected final @Nullable String fallback;
    protected final Consumer<MutableText> init;

    public Translation(String key, @Nullable String fallback, Consumer<MutableText> init) {
        this.key = key;
        this.fallback = fallback;
        this.init = init;
    }

    public static final class Unit extends Translation {
        public final Text text = text();

        public Unit(String key, @Nullable String fallback, Consumer<MutableText> init) {
            super(key, fallback, init);
        }

        public MutableText text() {
            var text = Text.translatableWithFallback(key, fallback);
            init.accept(text);
            return text;
        }
    }

    public static final class Arg extends Translation {
        public Arg(String key, @Nullable String fallback, Consumer<MutableText> init) {
            super(key, fallback, init);
        }

        public MutableText text(Object... args) {
            var text =  Text.translatableWithFallback(key, fallback, args);
            init.accept(text);
            return text;
        }
    }

    private static final Consumer<MutableText> NO_OP = ignored -> {};

    public static Unit unit(String key, String fallback, Consumer<MutableText> init) {
        return new Unit(key, fallback, init);
    }

    public static Unit unit(String key, String fallback, Formatting... formatting) {
        return new Unit(key, fallback, text -> text.formatted(formatting));
    }

    public static Unit unit(String key, Consumer<MutableText> init) {
        return unit(key, null, init);
    }

    public static Unit unit(String key, Formatting... formatting) {
        return unit(key, null, text -> text.formatted(formatting));
    }

    public static Unit unit(String key, String fallback) {
        return unit(key, fallback, NO_OP);
    }

    public static Unit unit(String key) {
        return unit(key, NO_OP);
    }

    public static Arg arg(String key, String fallback, Consumer<MutableText> init) {
        return new Arg(key, fallback, init);
    }

    public static Arg arg(String key, String fallback, Formatting... formatting) {
        return new Arg(key, fallback, text -> text.formatted(formatting));
    }

    public static Arg arg(String key, Consumer<MutableText> init) {
        return arg(key, null, init);
    }

    public static Arg arg(String key, Formatting... formatting) {
        return arg(key, null, text -> text.formatted(formatting));
    }

    public static Arg arg(String key, String fallback) {
        return arg(key, fallback, NO_OP);
    }

    public static Arg arg(String key) {
        return arg(key, NO_OP);
    }
}
