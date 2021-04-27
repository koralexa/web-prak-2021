import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CoursePageTests {
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
    public void findCourses() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Курсы")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() > 1);
        }
        Select teacher = new Select(driver.findElement(By.id("teacher")));
        teacher.selectByIndex(2);
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() == 4);
        }

        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() > 1);
        }
    }

    @Test
    public void addAndDeleteCourse() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Курсы")).click();
        long size1 = driver.findElements(By.cssSelector("tr")).size();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("course_name")).sendKeys("Тестирование");
        Select coverage = new Select(driver.findElement(By.id("coverage")));
        coverage.selectByIndex(2);
        driver.findElement(By.id("intensity")).sendKeys("2");
        driver.findElement(By.id("study_year")).sendKeys("1");
        Select teacher = new Select(driver.findElement(By.id("teacher")));
        teacher.selectByVisibleText("Белов Леонид Евгеньевич");
        driver.findElement(By.id("submit")).click();
        long size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1 + 1);

        driver.findElement(By.linkText("Тестирование")).click();
        assert(driver.findElement(By.id("course_name")).getText().equals("Тестирование"));
        assert(driver.findElement(By.id("coverage")).getText().equals("Групповой"));
        assert(driver.findElement(By.id("intensity")).getText().equals("2"));
        assert(driver.findElement(By.id("study_year")).getText().equals("1"));
        assert(driver.findElement(By.id("teacher")).getText().equals("Белов Леонид Евгеньевич"));
        driver.findElement(By.id("delete")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("course_name")).sendKeys("");
        coverage = new Select(driver.findElement(By.id("coverage")));
        coverage.selectByIndex(1);
        driver.findElement(By.id("intensity")).sendKeys("-2");
        driver.findElement(By.id("study_year")).sendKeys("10");
        driver.findElement(By.id("submit")).click();
        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка добавления курса"));

        driver.findElement(By.id("back")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);
    }

    @Test
    public void editCourse() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Курсы")).click();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("course_name")).sendKeys("Тестирование");
        Select coverage = new Select(driver.findElement(By.id("coverage")));
        coverage.selectByIndex(2);
        driver.findElement(By.id("intensity")).sendKeys("2");
        driver.findElement(By.id("study_year")).sendKeys("1");
        Select teacher = new Select(driver.findElement(By.id("teacher")));
        teacher.selectByVisibleText("Белов Леонид Евгеньевич");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.linkText("Тестирование")).click();

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("course_name")).clear();
        driver.findElement(By.id("intensity")).clear();
        driver.findElement(By.id("study_year")).clear();
        driver.findElement(By.id("course_name")).sendKeys("Проверка");
        coverage = new Select(driver.findElement(By.id("coverage")));
        coverage.selectByVisibleText("Потоковый");
        driver.findElement(By.id("intensity")).sendKeys("1");
        driver.findElement(By.id("study_year")).sendKeys("2");
        teacher = new Select(driver.findElement(By.id("teacher")));
        teacher.selectByVisibleText("Григорьев Илья Русланович");
        driver.findElement(By.id("submit")).click();
        assert(driver.findElement(By.id("course_name")).getText().equals("Проверка"));
        assert(driver.findElement(By.id("coverage")).getText().equals("Потоковый"));
        assert(driver.findElement(By.id("intensity")).getText().equals("1"));
        assert(driver.findElement(By.id("study_year")).getText().equals("2"));
        assert(driver.findElement(By.id("teacher")).getText().equals("Григорьев Илья Русланович"));

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("course_name")).clear();
        driver.findElement(By.id("intensity")).clear();
        driver.findElement(By.id("study_year")).clear();
        driver.findElement(By.id("course_name")).sendKeys("Тестирование");
        coverage = new Select(driver.findElement(By.id("coverage")));
        coverage.selectByVisibleText("Спецкурс");
        driver.findElement(By.id("intensity")).sendKeys("-8");
        driver.findElement(By.id("study_year")).sendKeys("");
        teacher = new Select(driver.findElement(By.id("teacher")));
        teacher.selectByVisibleText("Белов Леонид Евгеньевич");
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка редактирования курса"));
        driver.findElement(By.id("back")).click();

        assert(driver.findElement(By.id("course_name")).getText().equals("Проверка"));
        assert(driver.findElement(By.id("coverage")).getText().equals("Потоковый"));
        assert(driver.findElement(By.id("intensity")).getText().equals("1"));
        assert(driver.findElement(By.id("study_year")).getText().equals("2"));
        assert(driver.findElement(By.id("teacher")).getText().equals("Григорьев Илья Русланович"));
        driver.findElement(By.id("delete")).click();
    }

    @Test
    public void addAndDeleteListeners() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Курсы")).click();

        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("course_name")).sendKeys("Тестирование");
        Select coverage = new Select(driver.findElement(By.id("coverage")));
        coverage.selectByIndex(2);
        driver.findElement(By.id("intensity")).sendKeys("2");
        driver.findElement(By.id("study_year")).sendKeys("1");
        Select teacher = new Select(driver.findElement(By.id("teacher")));
        teacher.selectByVisibleText("Белов Леонид Евгеньевич");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.linkText("Тестирование")).click();
        long size1 = driver.findElements(By.cssSelector("tr")).size();
        assert(size1 == 1);

        driver.findElement(By.id("add_listener")).click();
        Select listener = new Select(driver.findElement(By.id("listener")));
        listener.selectByVisibleText("101");
        driver.findElement(By.id("submit")).click();

        long size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size1 + 1 == size2);

        driver.findElement(By.id("add_listener")).click();
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка добавления слушателя"));
        driver.findElement(By.id("back")).click();

        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size1 + 1 == size2);

        driver.findElement(By.id("delete_listener")).click();

        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size1 == size2);

        driver.findElement(By.id("delete")).click();
    }
}
