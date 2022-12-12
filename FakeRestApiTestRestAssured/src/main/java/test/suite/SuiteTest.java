package test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    APITestActivities.class,
    APITestAuthors.class,
    APITestBooks.class,
    APITestCoverPhotos.class,
    APITestUsers.class
})
public class SuiteTest {

}
