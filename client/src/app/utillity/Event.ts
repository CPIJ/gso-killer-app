export default class Event {

    private static listeners: Set<EventHandler>;

    static listen(name, callback) {
        if (Event.listeners === undefined) {
            Event.listeners = new Set<EventHandler>();
        }
        
        Event.listeners.add({ name, callback });
    }


    static emit(name, ...data) {
        if (Event.listeners === undefined) {
            Event.listeners = new Set<EventHandler>();
        }

        Event.listeners.forEach(listener => {
            if (listener.name === name) {
                listener.callback(data);
            }
        })
    }
}

class EventHandler {
    name: string
    callback: Function;
}