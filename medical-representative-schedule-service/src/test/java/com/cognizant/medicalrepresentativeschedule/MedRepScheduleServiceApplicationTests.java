package com.cognizant.medicalrepresentativeschedule;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.medicalrepresentativeschedule.controller.MedRepScheduleController;
import com.cognizant.medicalrepresentativeschedule.dao.MedRepRepository;
import com.cognizant.medicalrepresentativeschedule.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentativeschedule.service.MedRepScheduleService;



@SpringBootTest(classes = MedRepScheduleServiceApplication.class)
public class MedRepScheduleServiceApplicationTests {

	
	@Test
	public void main() {
		MedRepScheduleServiceApplication.main(new String[]  {});
	}

}


//@RunWith(SpringRunner.class)
//@SpringBootTest

//public class MedRepScheduleServiceApplicationTests {
//    
//	private MockMvc mockMvc;
//	
//	@Autowired
//	private MedRepScheduleService scheduleService;
//	
//	@MockBean
//	private MedRepRepository medicalRepRepository;
//	
//	@InjectMocks
//	private MedRepScheduleController medRepScheduleController;
//	
//	@Before
//	    public void init(){
//	        MockitoAnnotations.initMocks(this);
//	        mockMvc = MockMvcBuilders
//	                .standaloneSetup(medRepScheduleController).build();
//	}
//	
//	
//	@Test
//	public void testGetRepSchedule() throws TokenValidationFailedException {
//		
//		
//	}
	



