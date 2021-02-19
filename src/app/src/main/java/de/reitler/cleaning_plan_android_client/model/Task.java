package de.reitler.cleaning_plan_android_client.model;

import java.util.Date;
import java.util.List;

public abstract class Task {
    private String title;
    private boolean isDone;
    private List<Roommate> roommates;
}
