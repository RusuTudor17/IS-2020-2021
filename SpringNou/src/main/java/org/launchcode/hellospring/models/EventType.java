package org.launchcode.hellospring.models;

public enum EventType {



    CONFERENCE("Conference"),
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social");



    EventType(String displayName) {
        this.displayName = displayName;
    }




    private final String displayName;



    public String getDisplayName() {
        return displayName;
    }

}
