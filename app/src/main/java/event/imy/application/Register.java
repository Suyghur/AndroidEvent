package event.imy.application;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 4399-蒋明伟 on 2017/8/3.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Target({ElementType.METHOD}) //只使用在方法上
public @interface Register {

}
