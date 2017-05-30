package de.hauke_stieler.rednimer.Common;

/**
 * Created by hauke on 30.05.17.
 */
public class Reminder {
    private String _title;
    private String _remindingDescription;

    public Reminder(){
        _title = "A great reminder";
        _remindingDescription = "18:00 - 23:00 every 3h";
    }

    public String getTitle() {
        return _title;
    }

    public String getRemindingDescription() {
        return _remindingDescription;
    }
}
