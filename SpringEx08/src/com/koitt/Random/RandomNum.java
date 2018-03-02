package com.koitt.Random;


import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class RandomNum {

	public static void main(String[] args) {
		Random ran = new Random();
		//TreeSet 특징: 숫자가 오름차순으로 정렬된다.
		Set<Integer> setNum = new TreeSet();

		while (setNum.size() < 6) {
			int num = ran.nextInt(46);
			if (num > 0) {
				setNum.add(num);
			}
		}

		System.out.println(setNum);
	}
}
