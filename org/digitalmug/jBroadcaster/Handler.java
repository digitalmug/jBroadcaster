package org.digitalmug.jBroadcaster;

public interface Handler {
    /**
     * Event handling interface
     * @param data - Data passed to the handler
     */

    public void handle( Object data );
}
