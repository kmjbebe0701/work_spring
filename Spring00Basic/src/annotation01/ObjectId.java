package annotation01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)				//애노테이션을 붙일 수 있는 위치(필드로 지정)
@Retention(RetentionPolicy.RUNTIME)		//어느 시점에 사용할 것인지 정의(실행중에 사용)
public @interface ObjectId {

}
