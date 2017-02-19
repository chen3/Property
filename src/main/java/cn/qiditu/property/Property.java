package cn.qiditu.property;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import cn.qiditu.signalslot.signals.Signal1;

public class Property<T> {

    T value;

    @SuppressWarnings("unused")
    public Property(@NotNull T value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    public Property(@NotNull WriteProperty<T> write) {
        this(write, null);
    }

    @SuppressWarnings("WeakerAccess")
    public Property(@NotNull WriteProperty<T> write, @Nullable T value) {
        write.read = this;
        this.value = value;
    }

    @SuppressWarnings("WeakerAccess")
    public final Signal1<T> changed = new Signal1<>(this);

    @Nullable
    @SuppressWarnings("WeakerAccess")
    public T get() {
        return value;
    }

    @Nullable
    @SuppressWarnings("unused")
    public T read() {
        return get();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(@Nullable Object obj) {
        return value.equals(obj);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
