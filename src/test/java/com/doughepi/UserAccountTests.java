package com.doughepi;

import com.doughepi.models.UserModel;
import com.doughepi.repositories.UserRepository;
import com.doughepi.services.UserService;
import com.doughepi.validation.RegistrationValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by dough on 2017-02-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAccountTests
{

    static final UUID TEST_ACCOUNT_ID = UUID.fromString("b6125374-4ec2-4960-9e0a-bdf50238c039");
    private static final String TEST_ACCOUNT_USERNAME = "doughepi";
    private static final String TEST_ACCOUNT_EMAIL = "doughertypiper@gmail.com";
    private static final String TEST_ACCOUNT_PASSWORD = "password";
    private static final String TEST_ACCOUNT_FIRST_NAME = "Piper";
    private static final String TEST_ACCOUNT_MIDDLE_INITIAL = "J";
    private static final String TEST_ACCOUNT_LAST_NAME = "Dougherty";

    @Autowired
    RegistrationValidator registrationValidator;
    @Autowired
    private
    UserService userService;
    @Autowired
    private
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception
    {
        UserModel testUser = userRepository.findOne(TEST_ACCOUNT_ID);
        if (testUser == null)
        {
            userService.createTestUser(TEST_ACCOUNT_ID,
                                       TEST_ACCOUNT_USERNAME,
                                       TEST_ACCOUNT_PASSWORD,
                                       TEST_ACCOUNT_EMAIL,
                                       TEST_ACCOUNT_FIRST_NAME,
                                       TEST_ACCOUNT_MIDDLE_INITIAL,
                                       TEST_ACCOUNT_LAST_NAME);
        }
    }

    @After
    public void tearDown() throws Exception
    {


    }

    @Test
    public void testUserSaved() throws Exception
    {
        /*
        * 1. Find the account in the database.
        * 2. Change the username to something random.
        * 3. Save the edited user account.
        * 4. Find teh account in the database again.
        * 5. Check that the username matches our something random.
        * If it did, then the account was successfully edited and retrieved.
        */
        UserModel userModel = userRepository.findOne(TEST_ACCOUNT_ID);

        UUID randomUUID = UUID.randomUUID();
        userModel.setOther(randomUUID);
        userRepository.save(userModel);

        UUID foundOther = userRepository.findOne(TEST_ACCOUNT_ID).getOther();
        Assert.assertEquals(randomUUID, foundOther);
    }

    @Test
    public void testFindByUsername() throws Exception
    {
        UserModel userModel = userRepository.findOne(TEST_ACCOUNT_ID);
        UserModel byUsername = userService.findByUsername(TEST_ACCOUNT_USERNAME);
        Assert.assertEquals(userModel, byUsername);
    }

    @Test
    public void testFindByEmail() throws Exception
    {
        UserModel userModel = userRepository.findOne(TEST_ACCOUNT_ID);
        UserModel byEmail = userService.findByEmail(TEST_ACCOUNT_EMAIL);
        Assert.assertEquals(userModel, byEmail);
    }

    @Test
    public void testNotExist() throws Exception
    {
        //Honestly, there is a chance this could fail. Like, a very, very
        //very small chance.
        UUID uuid = UUID.randomUUID();
        Assert.assertNull(userRepository.findOne(uuid));
    }

    @Test
    public void testNotExistEmail() throws Exception
    {
        UserModel byEmail = userService.findByEmail("emailthatdoesntexist@yahoo.com");
        Assert.assertNull(byEmail);
    }

    @Test
    public void testNotExistUsername() throws Exception
    {
        UserModel byUsername = userService.findByUsername("usernamethatdoesntexist");
        Assert.assertNull(byUsername);
    }
}
