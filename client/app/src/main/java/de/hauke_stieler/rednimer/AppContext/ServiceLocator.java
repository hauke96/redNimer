package de.hauke_stieler.rednimer.AppContext;

import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import de.hauke_stieler.rednimer.DayOverview.Service.DummyReminderService;
import juard.injection.Locator;

/**
 * Created by hauke on 30.05.17.
 */
public class ServiceLocator {
    public static void registerAll(){
        Locator.register(IReminderService.class, () -> new DummyReminderService());
    }
}