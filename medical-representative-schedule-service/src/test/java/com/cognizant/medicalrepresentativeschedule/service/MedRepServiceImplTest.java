package com.cognizant.medicalrepresentativeschedule.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.medicalrepresentativeschedule.controller.MedRepScheduleController;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentativeschedule.model.MedicalRepresentative;
import com.cognizant.medicalrepresentativeschedule.model.RepSchedule;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedRepServiceImpl.class)
@AutoConfigureMockMvc

class MedRepServiceImplTest {

	@Mock
	private AuthenticationFeignClient authenticationFeignClient;

	@InjectMocks
	private MedRepScheduleController medicalRepresenativeScheduleController;

	@Mock
	private RepSchedule repSchedule;

	@Mock
	private List<RepSchedule> medicineStockList;

	@MockBean
	private MedRepScheduleServiceImpl medicalRepresentativeScheduleService;

	@MockBean
	private MedRepServiceImpl medicalRepresentativeService;

	@Test
	public void testGetMedicalRepresentatives() throws TokenValidationFailedException {

		when(medicalRepresentativeService.isValidSession("token")).thenReturn(true);

		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeService
				.getMedicalRepresentatives("token");
		assertNotNull(medicalRepresentatives);

	}

}
