package cloud.autotests.config;

import org.aeonbits.owner.Config;
import org.graalvm.compiler.lir.LIRInstruction;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})
public interface ProjectConfig extends Config {

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("112.0")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();

    String browserMobileView();

    @DefaultValue("http://62.113.108.218:4444/wd/hub")
    String remoteDriverUrl();

    @DefaultValue("http://62.113.108.218:8080/video/")
    String videoStorage();

}
