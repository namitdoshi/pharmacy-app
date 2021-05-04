package com.cognizant.medicalrepresentativeschedule.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.medicalrepresentativeschedule.controller.MedRepScheduleController;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class MedRepScheduleServiceImplTest {

	@Mock
	private AuthenticationFeignClient authenticationFeignClient;

	@InjectMocks
	private MedRepScheduleController medicalRepresenativeScheduleController;

	@Mock
	private RepSchedule repSchedule;

	@MockBean
	private MedRepScheduleServiceImpl medicalRepresentativeScheduleService;

	@Test
	public void testGetRepSchedule() throws TokenValidationFailedException {
		log.info("Start");

		when(medicalRepresentativeScheduleService.isValidSession("token")).thenReturn(true);

		List<RepSchedule> repSchedule = medicalRepresentativeScheduleService.getRepSchedule("token",
				LocalDate.of(2021, 05, 15));
		assertNotNull(repSchedule);

		log.info("End");
	}

}
