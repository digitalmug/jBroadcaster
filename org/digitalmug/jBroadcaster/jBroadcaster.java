package org.digitalmug.jBroadcaster;

/*
 * jBroadcaster
 * @author Enrique Sabalza
 *
 * https://github.com/digitalmug/jBroadcaster
 */

import java.util.ArrayList;
import java.util.HashMap;

public class jBroadcaster {

    private static HashMap<String, HashMap<Integer, Handler>> _peers;
    private static HashMap<String, ArrayList<Integer>> _keys;

    /**
     * Constructor for Broadcaster
     */
    public jBroadcaster(){

        _peers = new HashMap<String, HashMap<Integer, Handler>>();
        _keys = new HashMap<String, ArrayList<Integer>>();
    }

    /**
     * Dispatches a call to the subscribed Handlers
     *
     * @param call
     */
    public static Boolean dispatch(String call, Object data){

        if(!_peers.containsKey(call)) return false;
        if(!_keys.containsKey(call)) return false;

        HashMap<Integer, Handler> handlersPair = _peers.get(call);
        ArrayList <Integer> _callKeys = _keys.get(call);

        for(int i = 0; i < _callKeys.size(); i++){
            handlersPair.get( _callKeys.get(i) ).handle( data );
        }

        return true;
    }

    /**
     * Subscribe a Handler to a call
     *
     * @param call
     * @param handler
     *
     * @return key
     */
    public static int addEventListener(String call, Handler handler){

        HashMap<Integer, Handler> handlers;

        // Generate a random key
        int _foreignKey = (int)(9999 + (Math.random() * 9999));

        if(_peers.containsKey(call)){

            handlers = _peers.get(call);
            handlers.put(_foreignKey, handler);

            _peers.remove(call);

        }else{

            handlers = new HashMap<Integer, Handler>();
            handlers.put(_foreignKey, handler);

        }

        _peers.put(call, handlers);


        // Store foreign key
        ArrayList<Integer> _keyChain;

        if(_keys.containsKey(call)){

            _keyChain = _keys.get(call);
            _keyChain.add( _foreignKey );

            _keys.remove(call);

        }else{
            _keyChain = new ArrayList<Integer>();
            _keyChain.add( _foreignKey );
        }

        _keys.put(call, _keyChain);

        return _foreignKey;
    }

    /**
     * Un-subscribe a Handler from a call
     *
     * @param call
     * @param key
     *
     * @return Boolean
     */

    public static Boolean removeEventListener(String call, int key){

        // Peers has key
        if(!_peers.containsKey(call)) return false;

        // Key exists in keychain
        if(!_keys.containsKey(call)) return false;

        // Get handlers by Peers key
        HashMap<Integer, Handler> handlers = _peers.get(call);
        ArrayList <Integer> _keyChain = _keys.get(call);

        for (int i = 0; i < _keyChain.size(); i++){
            if(_keyChain.get(i) == key) {
                handlers.remove(_keyChain.get(i));
                _keyChain.remove(i);
            }
        }

       _peers.put(call, handlers);
       _keys.put(call, _keyChain);

        return true;
    }
}
