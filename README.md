jBroadcaster
============

Event broadcasting for the Java platform (Android too)



Initializing jBroadcaster
=========================

To initialize the broadcaster, just create an anonymous instance of the jBroadcaster class


```java

// There should be only one instance of jBroadcaster
// In your entire Java or Android application

new jBroadcaster();

```

Handling events using jBroadcaster
==================================

To listen to an event simply subscribe to the event passing the addEventListener method
a new Handler type callback

```java

// The addEventListener method returns a foreign key (a unique event identifier)
// Which you might want to save in order to unsubscribe the event

int event_id = jBroadcaster.addEventListener("DONE", new Handler() {

    @Override
    public void handle(Object data) {
        updateUI(data); // Do stuff
    }

});

```

You're free to subscribe an unlimited (limited by memory restrictions) number of listeners
to a certain event

```java

jBroadcaster.addEventListener("DONE", new Handler() {

    @Override
    public void handle(Object data) {
        drawList(data); // Do stuff
    }

});

// Notice you're subscribing to the same event

jBroadcaster.addEventListener("DONE", new Handler() {

    @Override
    public void handle(Object data) {
        drawList(data); // Do stuff
    }

});

```


Dispatching the event
==================================

To dispatch an event at any time of the execution time, simply call the dispatch method



```java

// Data is the Object you want to pass to all of
// Your handlers subscribed to the event

jBroadcaster.dispatch("DONE", data);

```


Unsubscribing from the event
==================================

To unsubscribe a Handler, simply pass the **event_id** returned by the
addEventListener method and the event name

```java

// Remember to store your foreign key somewhere
int event_id = jBroadcaster.addEventListener("DONE", new Handler() {

    @Override
    public void handle(Object data) {
        updateUI(data); // Do stuff
    }

});

// Unsubscribe
jBroadcaster.removeEventListener("DONE", event_id);

```

###### About

Developer site: http://digitalmug.bl.ee/
Developer mail: enrique@emdiem.com


Licensed under the GNU General Public License version 2 (GPLv2) **with Classpath exception.**
Please refer to LICENSE.txt or http://www.gnu.org/software/classpath/license.html for details.








