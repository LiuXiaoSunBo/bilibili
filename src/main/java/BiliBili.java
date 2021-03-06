import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BiliBili {
   public static Thread thread;
    public static void open(String cookie, String data, String url) throws InterruptedException {
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.addArguments("-headless");
//        chromeOptions.addArguments("disable_gpu");
//        chromeOptions.addArguments("no-sandbox");//禁用沙盒
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.bilibili.com/");
        setCookie(driver, cookie);
        String js = "window.scrollBy(0, 300)";
        driver.get(url);
        driver.manage().window().maximize();
         thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    WebElement element = driver.findElements(By.className("card")).get(0);
                    String text = element.findElement(By.className("detail-link")).getText();
                    System.out.println(text);
                    if (text.equals("刚刚")||(getNumber(text)<50&& text.contains("秒"))){
                        JavascriptExecutor jS = (JavascriptExecutor)driver;
                        jS.executeScript(js);
                        element.findElements(By.className("text-offset")).get(1).click();
                        TimeUnit.SECONDS.sleep(1);
                        WebElement element1 = element.findElement(By.tagName("textarea"));
                        System.out.println(element1);
                        element1.clear();
                        element1.sendKeys(data);
                        TimeUnit.SECONDS.sleep(2);
                        element.findElement(By.tagName("button")).click();
                    }
                    driver.navigate().refresh();
                }
                catch (InterruptedException e){
                    break;
                }
                catch (Exception e) {
                    System.out.println("出错");
                    System.out.println(e.getMessage());
                    driver.navigate().refresh();
                    e.printStackTrace();
                    continue;
                }
            }

        });
        thread.start();
    }

    private static void setCookie(WebDriver webDriver, String cookie) {
        String[] realCookies = cookie.trim().split(";");
        for (String nameAndValue : realCookies) {
            String[] data = nameAndValue.trim().split("=");
            webDriver.manage().addCookie(new Cookie(data[0], data[1], "bilibili.com", "/", new Date(new Date().getYear() + 1, 10, 1)));
        }

    }

    private static int getNumber(String data) {
        String str = data;
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
    return Integer.valueOf(str2);
    }
}