package org.bglogin.model.dao.test;


import org.bglogin.model.config.test.TestModelConfig;
import org.bglogin.model.dao.IActivityDao;
import org.bglogin.model.entity.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JUnit Test of DAO Service for entity User
 * 
 * @author Giuseppe Vincenzi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestModelConfig.class)
@ActiveProfiles("test")
public class ActivityDaoTest {
	@Autowired
    private IActivityDao activityDao;
	
//    @Test
//    public void testGetActivities() {
//        
//    	Activity activity = new Activity();
//
//    	activity = activityDao.get;
//	    Assert.assertEquals(activity.getName(),"act1");;
//	    System.out.println(activity.getName());
//        }
    
}
