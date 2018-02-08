package bean;

import org.springframework.stereotype.Component;

@Component("engDao")		//bean이름을 "engDao"라고 정의한 것과 같다
public class EngMessageDaoImpl implements MessageDao{

	@Override
	public String getMessage() {
		return "Hello. Nice to meet you.";
	}

}
