package chapter1.builder;

/**
 * @author zhangshenao
 * Created on 2019-08-20
 * 使用Builder模式创建对象
 */
public class NutritionFacts {
    //required
    private final int servingSize;
    private final int servings;

    //optional
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    //private constructor
    private NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    //builder method
    public static Builder newBuilder(int servingSize, int servings) {
        return new Builder(servingSize, servings);
    }

    //Builder
    public static class Builder {
        //required params
        private final int servingSize;
        private final int servings;

        //optional params with default value
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

}
