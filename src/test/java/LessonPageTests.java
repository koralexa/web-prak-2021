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

public class LessonPageTests {
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
    public void findLessons() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Расписание")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() > 1);
        }
        Select teacher = new Select(driver.findElement(By.id("teacher")));
        teacher.selectByIndex(1);
        Select student = new Select(driver.findElement(By.id("student")));
        student.selectByIndex(4);
        Select classroom = new Select(driver.findElement(By.id("classroom")));
        classroom.selectByIndex(4);
        Select day = new Select(driver.findElement(By.id("day")));
        day.selectByIndex(6);
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() == 2);
        }
        teacher = new Select(driver.findElement(By.id("teacher")));
        teacher.selectByIndex(1);
        student = new Select(driver.findElement(By.id("student")));
        student.selectByIndex(2);
        classroom = new Select(driver.findElement(By.id("classroom")));
        classroom.selectByIndex(3);
        day = new Select(driver.findElement(By.id("day")));
        day.selectByIndex(4);
        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() == 1);
        }

        driver.findElement(By.id("submit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
            assert(elements.size() > 1);
        }
    }

    @Test
    public void addAndDeleteLesson() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Расписание")).click();
        long size1 = driver.findElements(By.cssSelector("tr")).size();

        driver.findElement(By.id("add")).click();
        Select course = new Select(driver.findElement(By.id("course_id")));
        course.selectByVisibleText("Архитектура ЭВМ");
        Select day = new Select(driver.findElement(By.id("day")));
        day.selectByIndex(6);
        Select classroom = new Select(driver.findElement(By.id("classroom_id")));
        classroom.selectByIndex(3);
        driver.findElement(By.id("time")).sendKeys("14:59");
        driver.findElement(By.id("submit")).click();
        long size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1 + 1);

        driver.findElement(By.linkText("14:59")).click();
        assert(driver.findElement(By.id("course")).getText().equals("Архитектура ЭВМ"));
        assert(driver.findElement(By.id("classroom")).getText().equals("3"));
        assert(driver.findElement(By.id("day")).getText().equals("Суббота"));
        assert(driver.findElement(By.id("time")).getText().equals("14:59"));
        driver.findElement(By.id("delete")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);

        driver.findElement(By.id("add")).click();
        day = new Select(driver.findElement(By.id("day")));
        day.selectByIndex(2);
        classroom = new Select(driver.findElement(By.id("classroom_id")));
        classroom.selectByIndex(5);
        driver.findElement(By.id("time")).sendKeys("");
        driver.findElement(By.id("submit")).click();
        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка добавления занятия"));

        driver.findElement(By.id("back")).click();
        size2 = driver.findElements(By.cssSelector("tr")).size();
        assert(size2 == size1);
    }

    @Test
    public void editLesson() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Расписание")).click();

        driver.findElement(By.id("add")).click();
        Select course = new Select(driver.findElement(By.id("course_id")));
        course.selectByVisibleText("Архитектура ЭВМ");
        Select day = new Select(driver.findElement(By.id("day")));
        day.selectByIndex(6);
        Select classroom = new Select(driver.findElement(By.id("classroom_id")));
        classroom.selectByIndex(3);
        driver.findElement(By.id("time")).sendKeys("14:59");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.linkText("14:59")).click();

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("time")).clear();
        course = new Select(driver.findElement(By.id("course_id")));
        course.selectByVisibleText("Основы кибернетики");
        day = new Select(driver.findElement(By.id("day")));
        day.selectByIndex(1);
        classroom = new Select(driver.findElement(By.id("classroom_id")));
        classroom.selectByIndex(6);
        driver.findElement(By.id("time")).sendKeys("13:59");
        driver.findElement(By.id("submit")).click();
        assert(driver.findElement(By.id("course")).getText().equals("Основы кибернетики"));
        assert(driver.findElement(By.id("classroom")).getText().equals("7"));
        assert(driver.findElement(By.id("day")).getText().equals("Вторник"));
        assert(driver.findElement(By.id("time")).getText().equals("13:59"));

        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("time")).clear();
        course = new Select(driver.findElement(By.id("course_id")));
        course.selectByVisibleText("Основы кибернетики");
        day = new Select(driver.findElement(By.id("day")));
        day.selectByIndex(1);
        classroom = new Select(driver.findElement(By.id("classroom_id")));
        classroom.selectByIndex(6);
        driver.findElement(By.id("time")).sendKeys("");
        driver.findElement(By.id("submit")).click();

        WebElement error = driver.findElement(By.cssSelector("h2"));
        assert(error.getText().equals("Ошибка редактирования занятия"));
        driver.findElement(By.id("back")).click();

        assert(driver.findElement(By.id("course")).getText().equals("Основы кибернетики"));
        assert(driver.findElement(By.id("classroom")).getText().equals("7"));
        assert(driver.findElement(By.id("day")).getText().equals("Вторник"));
        assert(driver.findElement(By.id("time")).getText().equals("13:59"));
        driver.findElement(By.id("delete")).click();
    }
}
