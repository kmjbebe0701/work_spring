package com.koitt.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Spring3AjaxRestController {
	
	@ResponseBody			//Ajax 요청에 의한 응답을 위한 메소드라는 표시
	@RequestMapping(value="/car", method=RequestMethod.GET)
	public List<Car> getCars() {
		
		//클라이언트(웹브라우저)로 전달할 데이터를 리스트에 담는다
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("BMW", "20너1234"));
		cars.add(new Car("모닝", "11가1111"));
		cars.add(new Car("Audi", "22나2222"));
		
		//전달할 데이터를 리턴(이전에는 페이지였지만 지금은 데이터를 리턴)
		return cars;
		
	}
	
	//자동차 한대 정보를 응답하는 메소드 구현
	@ResponseBody
	@RequestMapping(value="/car/{name}", method=RequestMethod.GET)
	public Car getCar (@PathVariable("name") String name) {
		
		//아래와 같이 차량 정보가 저장되어 있다고 가정(데이터베이스 대신)
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("BMW", "20너1234"));
		cars.add(new Car("모닝", "11가1111"));
		cars.add(new Car("Audi", "22나2222"));
		
		//클라이언트로부터 전달받은 name 정보를 이용하여 검색
		for (int i = 0; i<cars.size(); i++) {
			//리스트 중에서 name 변수와 일치하는 차량이 존재하는 경우
			if (name.equalsIgnoreCase(cars.get(i).getName())) {
				
				//해당 차량을 리턴하여 클라이언트로 전달
				return cars.get(i);
			}
		}
		//name과 일치하는 차량이 없을 경우 null값을 리턴
		return null;
	}
	
	//자동차를 추가하는 메소드
	@ResponseBody
	@RequestMapping(value="/car", method=RequestMethod.POST)
	public void addCar (@RequestBody Car car) {
		
		System.out.println("입력받은 차량: " + car.getName()+ "/" + car.getNumber());
		
		/*
		 * 입력받은 차량을 데이터베이스에 저장하면 되지만 현재 데이터베이스가 없으므로 출력까지 구현
		 */
		
	}
	
	//자동차를 수정하는 메소드
		@ResponseBody
		@RequestMapping(value="/car", method=RequestMethod.PUT)
		public Map<String, Object> modifyCar (@RequestBody Car car) {
			
			System.out.println("클라이언트로부터 전달받은 차량: " + car.getName()+ "/" + car.getNumber());
			
			/*
			 * 입력받은 차량을 데이터베이스에 저장하면 되지만 현재 데이터베이스가 없으므로 출력까지 구현
			 */
			
			//데이터베이스에 BMW 차량만 저장되어 있다고 가정
			List<Car> cars = new ArrayList<>();
			cars.add(new Car("BMW", "20너1234"));
			
			Map<String, Object> resultMap = new HashMap<>();
			//리스트 검색하여 클라이언트로부터 입력받은 name과 동일한 차량이 있는지 검섹
			for(int i =0; i< cars.size(); i++) {
				if (car.getName().equals(cars.get(i).getName())) {
					cars.get(i).setNumber(car.getNumber());
					
					resultMap.put("message", "차량 수정 완료");
					return resultMap;
				}
			}
			resultMap.put("message", "차량 수정 실패");
			return resultMap;
		}
		
		//차량 한대 삭제하는 메소드
		@ResponseBody
		@RequestMapping(value="/car", method=RequestMethod.DELETE)
		public Map<String, Object> removeCar (@PathVariable("name") String name) {
			
			//아래와 같이 차량 정보가 저장되어 있다고 가정(데이터베이스 대신)
			List<Car> cars = new ArrayList<>();
			cars.add(new Car("BMW", "20너1234"));
			cars.add(new Car("모닝", "11가1111"));
			cars.add(new Car("Audi", "22나2222"));
			
			Map<String, Object> resultMap = new HashMap<>();
			//리스트 검색하여 클라이언트로부터 입력받은 name과 동일한 차량이 있는지 검섹
			for(int i =0; i< cars.size(); i++) {
				if (name.equals(cars.get(i).getName())) {
					cars.remove(i);		//리스트에서 차량 삭제
					resultMap.put("message", "차량 삭제 완료");
					return resultMap;
				}
			}
			resultMap.put("message", "차량 삭제 실패");
			return resultMap;
		}
}
