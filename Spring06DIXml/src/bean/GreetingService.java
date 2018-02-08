package bean;

public class GreetingService {
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
