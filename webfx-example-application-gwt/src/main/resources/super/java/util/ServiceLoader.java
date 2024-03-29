// File managed by WebFX (DO NOT EDIT MANUALLY)
package java.util;

import java.util.logging.Logger;

import dev.webfx.kit.launcher.spi.impl.gwt.GwtWebFxKitLauncherProvider;
import dev.webfx.kit.mapper.spi.impl.gwt.GwtWebFxKitHtmlMapperProvider;
import dev.webfx.platform.boot.spi.impl.gwt.GwtApplicationBooterProvider;
import dev.webfx.platform.console.spi.impl.gwt.GwtConsoleProvider;
import dev.webfx.platform.shutdown.spi.impl.gwt.GwtShutdownProvider;
import dev.webfx.platform.uischeduler.spi.impl.gwt.GwtUiSchedulerProvider;
import dev.webfx.platform.resource.spi.impl.gwt.GwtResourceModuleBooter;
import dev.webfx.platform.resource.spi.impl.gwt.GwtResourceProvider;
import dev.webfx.platform.util.function.Factory;

public class ServiceLoader<S> implements Iterable<S> {

    public static <S> ServiceLoader<S> load(Class<S> serviceClass) {
        switch (serviceClass.getName()) {
            case "dev.webfx.kit.launcher.spi.WebFxKitLauncherProvider": return new ServiceLoader<S>(GwtWebFxKitLauncherProvider::new);
            case "dev.webfx.kit.mapper.spi.WebFxKitMapperProvider": return new ServiceLoader<S>(GwtWebFxKitHtmlMapperProvider::new);
            case "dev.webfx.platform.uischeduler.spi.UiSchedulerProvider": return new ServiceLoader<S>(GwtUiSchedulerProvider::new);
            case "dev.webfx.platform.resource.spi.impl.gwt.GwtResourceBundle": return new ServiceLoader<S>();
            case "dev.webfx.platform.boot.spi.ApplicationBooterProvider": return new ServiceLoader<S>(GwtApplicationBooterProvider::new);
            case "dev.webfx.platform.boot.spi.ApplicationJob": return new ServiceLoader<S>();
            case "dev.webfx.platform.boot.spi.ApplicationModuleBooter": return new ServiceLoader<S>(dev.webfx.kit.launcher.WebFxKitLauncherModuleBooter::new, GwtResourceModuleBooter::new, dev.webfx.platform.boot.spi.impl.ApplicationJobsBooter::new);
            case "dev.webfx.platform.log.spi.LoggerProvider": return new ServiceLoader<S>(GwtConsoleProvider::new);
            case "dev.webfx.platform.resource.spi.ResourceServiceProvider": return new ServiceLoader<S>(GwtResourceProvider::new);
            case "dev.webfx.platform.scheduler.spi.SchedulerProvider": return new ServiceLoader<S>(GwtUiSchedulerProvider::new);
            case "dev.webfx.platform.shutdown.spi.ShutdownProvider": return new ServiceLoader<S>(GwtShutdownProvider::new);
            case "javafx.application.Application": return new ServiceLoader<S>(org.example.webfxexample.WebFxExampleApplication::new);

            // UNKNOWN SPI
            default:
                Logger.getLogger(ServiceLoader.class.getName()).warning("Unknown " + serviceClass + " SPI - returning no provider");
                return new ServiceLoader<S>();
        }
    }

    private final Factory[] factories;

    public ServiceLoader(Factory... factories) {
        this.factories = factories;
    }

    public Iterator<S> iterator() {
        return new Iterator<S>() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return index < factories.length;
            }

            @Override
            public S next() {
                return (S) factories[index++].create();
            }
        };
    }
}