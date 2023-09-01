package com.giovannicarmo.springbatchtutorial.ultils;

public class WrappedItem<T, S> {
    private T originalItem;
    private S marshaledString;

    public WrappedItem(T originalItem, S marshaledString) {
        this.originalItem = originalItem;
        this.marshaledString = marshaledString;
    }

    public T getOriginalItem() {
        return originalItem;
    }

    public S getMarshaledString() {
        return marshaledString;
    }
}
