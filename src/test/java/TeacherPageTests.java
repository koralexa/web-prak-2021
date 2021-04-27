import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TeacherPageTests {
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
    public void findTeachers() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Преподаватели")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
        assert (elements.size() > 1);
    }

    @Test
    public void addAndDeleteTeacher() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Преподаватели")).click();
        long size1 = driver.findElements(By.cssSelector("tr")).size();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("full_name")).sendKeys("Тестов Тест Тестович");
        driver.findElement(By.id("submit")).click();
        long size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1 + 1);

        driver.findElement(By.linkText("Тестов Тест Тестович")).click();
        assert(driver.findElement(By.id("full_name")).getText().equals("Тестов Тест Тестович"));
        driver.findElement(By.id("delete")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("full_name")).sendKeys("");
        driver.findElement(By.id("submit")).click();
        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка добавления преподавателя"));

        driver.findElement(By.id("back")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);
    }

    @Test
    public void editTeacher() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Преподаватели")).click();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("full_name")).sendKeys("Тестов Тест Тестович");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.linkText("Тестов Тест Тестович")).click();

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("full_name")).clear();
        driver.findElement(By.id("full_name")).sendKeys("Тестова Проверка Тестовна");
        driver.findElement(By.id("submit")).click();
        assert(driver.findElement(By.id("full_name")).getText().equals("Тестова Проверка Тестовна"));

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("full_name")).clear();
        driver.findElement(By.id("full_name")).sendKeys("");
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка редактирования преподавателя"));
        driver.findElement(By.id("back")).click();

        assert(driver.findElement(By.id("full_name")).getText().equals("Тестова Проверка Тестовна"));
        driver.findElement(By.id("delete")).click();
    }
}
