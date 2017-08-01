package de.hauke_stieler.rednimer.AppContext;

import de.hauke_stieler.rednimer.Common.Dao.IReminderDao;
import de.hauke_stieler.rednimer.Common.Dao.ReminderDao;
import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import de.hauke_stieler.rednimer.Common.ServiceInterface.INotificationService;
import de.hauke_stieler.rednimer.Technical.DummyService.DummyDataFactory;
import de.hauke_stieler.rednimer.Technical.DummyService.DummyNotificationService;
import de.hauke_stieler.rednimer.Technical.DummyService.DummyReminderService;
import de.hauke_stieler.rednimer.Technical.Service.ReminderServiceImpl;
import juard.injection.Locator;

/**
 * Created by hauke on 30.05.17.
 */
public class ServiceLocator {
    public static boolean DEBUG_MODE = false;

    public static void registerAll() {
        if(DEBUG_MODE) {
            Locator.register(IReminderService.class, () -> new DummyReminderService(Locator.get(INotificationService.class)));
            Locator.register(INotificationService.class, () -> new DummyNotificationService());
        }
        else{
            Locator.register(INotificationService.class, () -> new DummyNotificationService());
            Locator.register(IReminderDao.class, () -> new ReminderDao());
            Locator.register(IReminderService.class, () -> new ReminderServiceImpl(Locator.get(IReminderDao.class)));
        }
    }
}
