package com.koitt.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule {
	
	// Cron: 소프트웨어 유틸리티. UNIX 계열 컴퓨터 운영체제의 시간 기반 잡 스케줄러이다.
    // Job을 고정된 시간, 날짜, 간격에 주기적으로 실행할 수 있도록 스케줄링하기 위해 Cron을 사용한다.
    // 7개 필드: 초, 분, 시간, 월의 몇번째 날, 월, 요일, 년 순으로 구성
    // 스프링에서는 년에 해당하는 부분을 제외하고 6개의 필드만 사용한다.
    // * 표시는 every(매초, 매분, 매시간 ... 이라는 뜻)
    // "0 0 18 * * MON-FRI" 뜻: 주중 오후 6시 0분 0초에 시작
    // "0 0 */1 * * *" 뜻: 1시간 마다 0분 0초에 시작
    // "0 0 */8 * * *" 뜻: 8시간 마다 0분 0초에 시작
    // "0 0 12 1 * *" 뜻: 매월 1일 오후 12시 0분 0초에 시작 
    
    // 참고자료: http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
    // Cron 생성기: https://www.freeformatter.com/cron-expression-generator-quartz.html
	
	@Scheduled(cron="0/5 * * * * * ")			//5초마다 실행
	public void printTest() {
		System.out.println("5초마다 출력되는 메시지");
	}
	
	@Scheduled(cron="0 0/2 * * * * ")			//2분마다 실행
	public void printTest2() {
		System.out.println("2분마다 출력되는 메세지");
	}
	
}
