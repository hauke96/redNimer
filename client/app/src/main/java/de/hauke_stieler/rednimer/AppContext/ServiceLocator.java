package de.hauke_stieler.rednimer.AppContext;

import android.content.Context;

import de.hauke_stieler.rednimer.Common.ServiceInterface.AbstractReminderService;
import de.hauke_stieler.rednimer.Common.ServiceInterface.INotificationService;
import de.hauke_stieler.rednimer.Technical.Service.DummyNotificationService;
import de.hauke_stieler.rednimer.Technical.Service.DummyReminderService;
import juard.injection.Locator;

/**
 * Created by hauke on 30.05.17.
 */
public class ServiceLocator {
    public static void registerAll() {
        Locator.register(AbstractReminderService.class, () -> new DummyReminderService(Locator.get(INotificationService.class)));
        Locator.register(INotificationService.class, () -> new DummyNotificationService());
    }
}
