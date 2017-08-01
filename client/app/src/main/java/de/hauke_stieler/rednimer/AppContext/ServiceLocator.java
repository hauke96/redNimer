package de.hauke_stieler.rednimer.AppContext;

import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import de.hauke_stieler.rednimer.Common.ServiceInterface.INotificationService;
import de.hauke_stieler.rednimer.Technical.DummyServices.DummyDataFactory;
import de.hauke_stieler.rednimer.Technical.DummyServices.DummyNotificationService;
import de.hauke_stieler.rednimer.Technical.DummyServices.DummyReminderService;
import juard.injection.Locator;

/**
 * Created by hauke on 30.05.17.
 */
public class ServiceLocator {
    public static void registerAll() {
        Locator.register(IReminderService.class, () -> new DummyReminderService(Locator.get(INotificationService.class)));
        Locator.register(INotificationService.class, () -> new DummyNotificationService());

        new DummyDataFactory(Locator.get(IReminderService.class));
    }
}
