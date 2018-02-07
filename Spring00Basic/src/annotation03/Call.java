package annotation03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)				//애노테이션을 메소드에 사용하기 위해 작성
@Retention(RetentionPolicy.RUNTIME)		//애노테이션을 실행중에 사용
public @interface Call {
	
	int seq() default 0;				//순서를 뜻하는 변수

}
