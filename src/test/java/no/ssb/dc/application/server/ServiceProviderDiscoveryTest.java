package no.ssb.dc.application.server;

import no.ssb.dc.application.spi.Component;
import no.ssb.dc.application.spi.Controller;
import no.ssb.dc.application.spi.Service;
import org.junit.jupiter.api.Test;

public class ServiceProviderDiscoveryTest {

    @Test
    public void testController() {
        Iterable<Class<Controller>> discovery = ServiceProviderDiscovery.discover(Controller.class);
        discovery.forEach(a -> System.out.println(a.getName()));
    }

    @Test
    public void testService() {
        Iterable<Class<Service>> discovery = ServiceProviderDiscovery.discover(Service.class);
        discovery.forEach(a -> System.out.println(a.getName()));
    }

    @Test
    public void testComponent() {
        Iterable<Class<Component>> discovery = ServiceProviderDiscovery.discover(Component.class);
        discovery.forEach(a -> System.out.println(a.getName()));
    }
}
