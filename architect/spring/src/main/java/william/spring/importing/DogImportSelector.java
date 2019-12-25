package william.spring.importing;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author zhangshenao
 * @Date 2019-12-25
 * @Description 自定义ImportSelector, 导入指定组件
 */
public class DogImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //这里使用全限定类名
        return new String[] {"william.spring.importing.Dog"};
    }
}
