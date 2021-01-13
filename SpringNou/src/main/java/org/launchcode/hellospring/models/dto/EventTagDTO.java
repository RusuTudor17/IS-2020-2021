package org.launchcode.hellospring.models.dto;



import org.launchcode.hellospring.models.Event;
import org.launchcode.hellospring.models.Tag;

import javax.validation.constraints.NotNull;

//data transfer object
//pass around a tag and an event

public class EventTagDTO {


    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public EventTagDTO(){
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
