package chapter1.builder;

import java.util.Objects;

/**
 * @author zhangshenao
 * Created on 2019-08-20
 */
public class NyPizza extends Pizza {
    public enum Size {
        SMALL,
        MEDIUM,
        LARGE;
    }

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }
    }

    public NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }
}
