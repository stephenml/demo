package ml.stephen.core.mybatis.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by Stephen on 16/9/7.
 * 标识MyBatis的DAO,方便{@link org.mybatis.spring.mapper.MapperScannerConfigurer}的扫描。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisMapper {
	String value() default "";
}
