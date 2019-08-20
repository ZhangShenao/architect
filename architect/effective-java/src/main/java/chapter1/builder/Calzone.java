package chapter1.builder;

/**
 * @author zhangshenao
 * Created on 2019-08-20
 */
public class Calzone extends Pizza {
    private final boolean saucelnside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false; // Default

        public Builder saucelnside() {
            sauceInside = true;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        Calzone build() {
            return new Calzone(this);
        }
    }


    public Calzone(Builder builder) {
        super(builder);
        this.saucelnside = builder.sauceInside;
    }


}
