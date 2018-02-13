package com.koitt.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.koitt.dao.PetMapper;
import com.koitt.model.Owner;
import com.koitt.model.Pet;

public class TestDrive {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext("/com/koitt/config/config.xml");
		SqlSession session = context.getBean(SqlSession.class);
		
		// 1.
		Pet pet = session.selectOne("com.koitt.dao.PetMapper.selectPet", 1);
		System.out.println(pet);
		
		// 2.
		pet = session.selectOne("com.koitt.dao.PetMapper.selectPet2", 1);
		System.out.println(pet);
		
		// 3.
		List<Pet> petList = session.selectList("com.koitt.dao.PetMapper.selectPetAll");
		System.out.println(petList);
		
		// 4.
		Pet pet01 = new Pet();
		pet01.setPetName("NABI");
		
		Owner owner01 = new Owner();
		owner01.setOwnerName("PARK");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pet", pet01);
		map.put("owner", owner01);
		Pet resultPet = session.selectOne("com.koitt.dao.PetMapper.selectPetByNames", map);
		System.out.println(resultPet);
		
		// 5.
		Owner owner = session.selectOne("com.koitt.dao.PetMapper.selectOwner", "HONG");
		System.out.println("5: " + owner);
		
		// 6.
		pet = new Pet();
		pet.setOwnerName("HONG");
		pet.setPetName("JJONG");
		petList = session.selectList("com.koitt.dao.PetMapper.findPet", pet);
		System.out.println("6: " + petList);
		
		// 7.
		pet = new Pet(null, "나비", "HONG", 1000, new Date());
		int count = session.insert("com.koitt.dao.PetMapper.insertPet", pet);
		System.out.println(count + "개의 행이 추가되었습니다.");
		
		// 8. VO 객체를 사용하지 않고, Map을 이용해서 SQL문에 값을 전달
		map = new HashMap<String, Object>();
		map.put("petName", "벌이");
		map.put("ownerName", "AHN");
		map.put("price", 1500);
		map.put("birthDate", new Date());
		count = session.insert("com.koitt.dao.PetMapper.insertPet2", map);
		System.out.println(count + "개의 행이 추가되었습니다.");
		
		/*
		 *  9. 아래의 문장과 같은 결과가 나온다.
		 *  Pet pet = session.selectOne("com.koitt.dao.PetMapper.selectPet", 1); 
		 */
		PetMapper mapper = session.getMapper(PetMapper.class);
		pet = mapper.selectPet(1);
		System.out.println(pet);
		
		// 10.
		owner = mapper.selectHong("HONG");
		System.out.println("hong: " + owner);
	}
}












