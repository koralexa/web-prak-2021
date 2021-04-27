import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StudentPageTests {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        String fireFoxDriver = "src/test/geckodriver";
        System.setProperty("webdriver.gecko.driver", fireFoxDriver);
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile testprofile = profile.getProfile("web-prak");
        FirefoxOptions opt = new FirefoxOptions();
        opt.setProfile(testprofile);
        driver =  new FirefoxDriver(opt);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void findStudents() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Студенты")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() > 1);
        }
        driver.findElement(By.id("group_number")).sendKeys("201");
        driver.findElement(By.id("stream_number")).sendKeys("1");
        driver.findElement(By.id("study_year")).sendKeys("2");
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() == 3);
        }
        driver.findElement(By.id("group_number")).sendKeys("201");
        driver.findElement(By.id("stream_number")).sendKeys("1");
        driver.findElement(By.id("study_year")).sendKeys("3");
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() == 1);
        }
        driver.findElement(By.id("group_number")).sendKeys("");
        driver.findElement(By.id("stream_number")).sendKeys("");
        driver.findElement(By.id("study_year")).sendKeys("");
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() > 1);
        }
        driver.findElement(By.id("group_number")).sendKeys("");
        driver.findElement(By.id("stream_number")).sendKeys("");
        driver.findElement(By.id("study_year")).sendKeys("abc");
        driver.findElement(By.id("submit")).click();
        {
            WebElement error = driver.findElement(By.cssSelector("h2"));
            assert(error.getText().equals("Некорректные значения фильтров"));
        }
    }

    @Test
    public void addAndDeleteStudent() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Студенты")).click();
        long size1 = driver.findElements(By.cssSelector("tr")).size();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("group_number")).sendKeys("101");
        driver.findElement(By.id("full_name")).sendKeys("Тестов Тест Тестович");
        driver.findElement(By.id("study_year")).sendKeys("1");
        driver.findElement(By.id("submit")).click();
        long size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1 + 1);

        driver.findElement(By.linkText("Тестов Тест Тестович")).click();
        assert(driver.findElement(By.id("full_name")).getText().equals("Тестов Тест Тестович"));
        assert(driver.findElement(By.id("group")).getText().equals("101"));
        assert(driver.findElement(By.id("study_year")).getText().equals("1"));
        driver.findElement(By.id("delete")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("group_number")).sendKeys("");
        driver.findElement(By.id("full_name")).sendKeys("Тестов Тест Тестович");
        driver.findElement(By.id("study_year")).sendKeys("-1");
        driver.findElement(By.id("submit")).click();
        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка добавления студента"));

        driver.findElement(By.id("back")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);
    }

    @Test
    public void editStudent() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Студенты")).click();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("group_number")).sendKeys("101");
        driver.findElement(By.id("full_name")).sendKeys("Тестов Тест Тестович");
        driver.findElement(By.id("study_year")).sendKeys("1");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.linkText("Тестов Тест Тестович")).click();

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("group_number")).clear();
        driver.findElement(By.id("full_name")).clear();
        driver.findElement(By.id("study_year")).clear();
        driver.findElement(By.id("group_number")).sendKeys("202");
        driver.findElement(By.id("full_name")).sendKeys("Тестова Проверка Тестовна");
        driver.findElement(By.id("study_year")).sendKeys("2");
        driver.findElement(By.id("submit")).click();
        assert(driver.findElement(By.id("full_name")).getText().equals("Тестова Проверка Тестовна"));
        assert(driver.findElement(By.id("group")).getText().equals("202"));
        assert(driver.findElement(By.id("study_year")).getText().equals("2"));

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("group_number")).clear();
        driver.findElement(By.id("full_name")).clear();
        driver.findElement(By.id("study_year")).clear();
        driver.findElement(By.id("group_number")).sendKeys("300");
        driver.findElement(By.id("full_name")).sendKeys("Тестов Тест Тестович");
        driver.findElement(By.id("study_year")).sendKeys("");
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка редактирования студента"));
        driver.findElement(By.id("back")).click();

        assert(driver.findElement(By.id("full_name")).getText().equals("Тестова Проверка Тестовна"));
        assert(driver.findElement(By.id("group")).getText().equals("202"));
        assert(driver.findElement(By.id("study_year")).getText().equals("2"));
        driver.findElement(By.id("delete")).click();
    }

    @Test
    public void addAndDeletePassedCourse() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Студенты")).click();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("group_number")).sendKeys("101");
        driver.findElement(By.id("full_name")).sendKeys("Тестов Тест Тестович");
        driver.findElement(By.id("study_year")).sendKeys("1");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.linkText("Тестов Тест Тестович")).click();
        long size1 = driver.findElements(By.cssSelector("tr")).size();
        assert(size1 == 1);

        driver.findElement(By.id("add_course")).click();
        Select course = new Select(driver.findElement(By.id("course")));
        course.selectByIndex(1);
        driver.findElement(By.id("study_year")).sendKeys("1");
        driver.findElement(By.id("submit")).click();

        long size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size1 + 1 == size2);

        driver.findElement(By.id("add_course")).click();
        driver.findElement(By.id("study_year")).sendKeys("-1");
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка добавления пройденного курса"));
        driver.findElement(By.id("back")).click();

        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size1 + 1 == size2);

        driver.findElement(By.id("delete_course")).click();

        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size1 == size2);

        driver.findElement(By.id("delete")).click();
    }
}
