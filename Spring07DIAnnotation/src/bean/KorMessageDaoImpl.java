package bean;

import org.springframework.stereotype.Component;

@Component("korDao")			//bean이름을 "korDao"라고 정의한 것과 같다
public class KorMessageDaoImpl implements MessageDao{

	@Override
	public String getMessage() {
		return "안녕하세요? 반갑습니다.";
	}

}
