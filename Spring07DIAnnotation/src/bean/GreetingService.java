package bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("greeting")			//greeting이름의 bean객체를 만든것과 같다
@Scope("singleton")
public class GreetingService {
	@Autowired					//config.xml bean객체의 autowired 속성과 같다
	@Qualifier("engDao")		//autowired의 byName 속성값과 같다(지정하지 않으면 byType속성값과 같다)
	private MessageDao dao;

	public GreetingService() {
	}
	
	public GreetingService(MessageDao dao) {
		this.dao = dao;
	}

	public MessageDao getDao() {
		return dao;
	}

	public void setDao(MessageDao dao) {
		this.dao = dao;
	}
	
	public void sayHello() {
		System.out.println(dao.getMessage());
	}
	
	
	
	
}
