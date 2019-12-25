package william.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description 自定义Condition
 */
public class LifeCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getBeanFactory().containsBean("work") && conditionContext.getBeanFactory()
                .containsBean("relax");
    }
}
