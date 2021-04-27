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

public class ClassroomPageTests {
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
    public void findClassrooms() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Аудитории")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() > 1);
        }
        driver.findElement(By.id("min")).sendKeys("20");
        driver.findElement(By.id("max")).sendKeys("70");
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() == 7);
        }
        driver.findElement(By.id("min")).sendKeys("170");
        driver.findElement(By.id("max")).sendKeys("200");
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() == 1);
        }
        driver.findElement(By.id("min")).sendKeys("");
        driver.findElement(By.id("max")).sendKeys("");
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() > 1);
        }
        driver.findElement(By.id("min")).sendKeys("");
        driver.findElement(By.id("max")).sendKeys("abc");
        driver.findElement(By.id("submit")).click();
        {
            WebElement error = driver.findElement(By.cssSelector("h2"));
            assert(error.getText().equals("Некорректные значения фильтров"));
        }
    }

    @Test
    public void addAndDeleteClassroom() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Аудитории")).click();
        long size1 = driver.findElements(By.cssSelector("tr")).size();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("classroom_number")).sendKeys("305");
        driver.findElement(By.id("capacity")).sendKeys("10");
        driver.findElement(By.id("submit")).click();
        long size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1 + 1);

        driver.findElement(By.linkText("305")).click();
        assert(driver.findElement(By.id("classroom_number")).getText().equals("305"));
        assert(driver.findElement(By.id("capacity")).getText().equals("10"));
        driver.findElement(By.id("delete")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("classroom_number")).sendKeys("");
        driver.findElement(By.id("capacity")).sendKeys("-20");
        driver.findElement(By.id("submit")).click();
        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка добавления аудитории"));

        driver.findElement(By.id("back")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);
    }

    @Test
    public void editClassroom() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Аудитории")).click();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("classroom_number")).sendKeys("305");
        driver.findElement(By.id("capacity")).sendKeys("10");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.linkText("305")).click();

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("capacity")).clear();
        driver.findElement(By.id("capacity")).sendKeys("150");
        driver.findElement(By.id("submit")).click();
        assert(driver.findElement(By.id("capacity")).getText().equals("150"));

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("capacity")).clear();
        driver.findElement(By.id("capacity")).sendKeys("0");
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка редактирования аудитории"));
        driver.findElement(By.id("back")).click();

        assert(driver.findElement(By.id("capacity")).getText().equals("150"));
        driver.findElement(By.id("delete")).click();
    }
}
