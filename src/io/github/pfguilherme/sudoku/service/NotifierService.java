package io.github.pfguilherme.sudoku.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifierService
{
    private Map<EventType, List<EventListener>> listeners = new HashMap<>(){{
        put(EventType.CLEAR_CELL, new ArrayList<>());
    }};

    public void subscribe(final EventType eventType, EventListener listener)
    {
        var list = listeners.get(eventType);
        list.add(listener);
    }

    public void notify(final EventType eventType)
    {
        listeners.get(eventType).forEach(listener -> listener.update(eventType));
    }
}
