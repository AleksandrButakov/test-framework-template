package cloud.autotests.helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.sleep;

public class AllureAttachments {
    public static final Logger LOGGER = LoggerFactory.getLogger(AllureAttachments.class);

    @Attachment(value = "{attachName}", type = "text/plain")
    private static String addMessage(String attachName, String text) {
        return text;
    }

    public static void addBrowserConsoleLogs() {
        addMessage("Browser console logs", DriverUtils.getConsoleLogs());
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] addScreenshotAs(String attachName) {
        return DriverUtils.getScreenshotAsBytes();
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] addPageSource() {
        return DriverUtils.getPageSourceAsBytes();
    }

    public static void addVideo(String sessionId) {
        URL videoUrl = DriverUtils.getVideoUrl(sessionId);
        if (videoUrl != null) {
            InputStream videoInputStream = null;
            sleep(1000);

            for (int i = 0; i < 10; i++) {
                try {
                    videoInputStream = videoUrl.openStream();
                    break;
                } catch (FileNotFoundException e) {
                    sleep(1000);
                } catch (IOException e) {
                    LOGGER.warn("[ALLURE VIDEO ATTACHMENT ERROR] Cant attach allure video, {}", videoUrl);
                    e.printStackTrace();
                }
            }
            Allure.addAttachment("Video", "video/mp4", videoInputStream, "mp4");
        }
    }




//    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
//    public static String attachVideo(String sessionId) {
//        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
//                + getVideoUrl(sessionId)
//                + "' type='video/mp4'></video></body></html>";
//    }
//
//    public static String getVideoUrl(String sessionId) {
//        return getWebVideoUrl(sessionId);
//    }
//
//    public static String getWebVideoUrl(String sessionId) {
//        try {
//            return String.valueOf(new URL("http://62.113.108.218:8080/video/" + sessionId + ".mp4"));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



}
