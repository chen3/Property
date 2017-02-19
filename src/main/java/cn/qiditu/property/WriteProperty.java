package cn.qiditu.property;

import cn.qiditu.signalslot.slots.Slot1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

public class WriteProperty<T> {

    Property<T> read;

    public WriteProperty() {
    }

    private Slot1<T> slotSet = this::set;
    @SuppressWarnings("unused")
    public void write(@Nullable T value) {
        set(value);
    }
    @SuppressWarnings("WeakerAccess")
    public void set(@Nullable T value) {
        if(read == null) {
            throw new NullPointerException("not bind a property");
        }
        if(value == read.value ||
                (value != null && value.equals(read.value))) {
            return;
        }
        read.value = value;
        read.changed.emit(value);
    }

    private WeakReference<Property<T>> bindTarget = new WeakReference<>(null);
    @SuppressWarnings("unused")
    public void bind(@Nullable Property<T> property) {
        if(read == null) {
            throw new NullPointerException("not bind a property");
        }
        Property<T> bindTargetObject = bindTarget.get();
        if(bindTargetObject != null) {
            unBind(bindTargetObject);
        }
        bindTarget = new WeakReference<>(property);
        if(property != null) {
            set(property.get());
            property.changed.connect(slotSet);
        }
    }
    @SuppressWarnings("WeakerAccess")
    public void unBind(@NotNull Property<T> property) {
        if(read == null) {
            throw new NullPointerException("not bind a property");
        }
        property.changed.disconnect(slotSet);
        bindTarget = new WeakReference<>(null);
    }

}
