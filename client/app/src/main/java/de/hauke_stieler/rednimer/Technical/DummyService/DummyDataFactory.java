package de.hauke_stieler.rednimer.Technical.DummyService;

import java.util.Calendar;
import java.util.GregorianCalendar;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import juard.contract.Contract;

/**
 * Created by hauke on 01.08.17.
 */

public class DummyDataFactory {
    public DummyDataFactory(IReminderService reminderService){
        Contract.NotNull(reminderService);

        GregorianCalendar calendar = new GregorianCalendar();

        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 00);
        reminderService.add(new Reminder("Einkaufen", "* Nudeln\n* Wasser\n* Bier", calendar));

        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 30);
        reminderService.add(new Reminder("Lernen", "Für Mathe lernen", calendar));

        // go to next day
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 00);
        reminderService.add(new Reminder("Arzt", "Um 13:15 Zahnarzt", calendar));

        // go to next day
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 30);
        reminderService.add(new Reminder("Freunde", "Freunde in der Kneipe treffen", calendar));
    }
}
