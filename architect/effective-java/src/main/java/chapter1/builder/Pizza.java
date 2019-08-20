package chapter1.builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author zhangshenao
 * Created on 2019-08-20
 */
public abstract class Pizza {
    public enum Topping {
        HAM,
        MUSHROOM,
        ONION,
        PEPPER,
        SAUSAGE;
    }

    private final Set<Topping> toppings;

    public Pizza(Builder<?> builder) {
        this.toppings = builder.toppings.clone();
    }

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        // Subclasses must override this method to return "this"
        protected abstract T self();

        abstract Pizza build();
    }

}
